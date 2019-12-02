package main.game;

import main.hero.DamageOverTime;
import main.hero.Hero;
import main.hero.HeroFactory;
import main.hero.Location;
import main.map.MapoFGame;

import java.util.ArrayList;

public final class GameEngine {
  private static MapoFGame mapOfGame = MapoFGame.getInstance();
  private static ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
  private static ArrayList<Hero> heroes = new ArrayList<Hero>();

  private GameEngine() { }

  /**
   * It separates the read input data into heros, map and moves.
   * @param gameInput
   */
  public static void getInitialData(final GameInput gameInput) {
    ArrayList<ArrayList<Character>> heroesDetails = gameInput.getHeroes();
    for (ArrayList<Character> heroDetails : heroesDetails) {
      heroes.add(HeroFactory.createHero(heroDetails.get(0)));
    }
    for (int i = 0; i < heroesDetails.size(); i++) {
      Location location = new Location();
      char name = heroesDetails.get(i).get(0);
      int row = heroesDetails.get(i).get(1) - '0';
      int col = heroesDetails.get(i).get(2) - '0';
      location.initLocation(row, col);
      heroes.get(i).setHero(location, i, name);
    }
    mapOfGame.makeMap(gameInput.getMap());
    moves = gameInput.getMoves();
  }

  /**
   * Checks if there was any damage over time applied over that hero.
   * @param hero
   */
  private static void checkDamageOverTime(final Hero hero) {
    if (hero.getDot().getNumRounds() != 0) {
      int currRound = hero.getDot().getCurrRound();
      if (currRound <= hero.getDot().getNumRounds()) {
        hero.getDot().setCurrRound(currRound + 1);
      } else {
        DamageOverTime dot = new DamageOverTime();
        hero.setDot(dot);
      }
    }
  }

  /**
   * Applies the damage over time gained by the hero.
   * @param hero
   */
   private static void applyDamageOverTime(final Hero hero) {
     int dmgOverTime = hero.getDot().getPerRoundDMG();
     hero.modifyHP(dmgOverTime);
     if (hero.getCurrHp() <= 0) {
       hero.setState("dead");
     }
   }

  /**
   * Checks if their level has increased after the battle.
   * @param hero1
   * @param hero2
   */
   private static void checkLevelUp(final Hero hero1, final Hero hero2) {
     if (hero2.getCurrHp() <= 0) {
       hero2.setState("dead");
       hero1.setXpWinner(hero2.getLevel());
       int level = hero1.getLevel();
       int newLevel = (hero1.calculateLevelUp(hero1.getXp()));
       if (newLevel > level) {
         hero1.setLevel(newLevel);
         hero1.setCurrHp(hero1.getBonusLevel() * hero1.getLevel() + hero1.getHpMaximum());
       }
     }
   }

  /**
   * Describes a battle between two heroes, modifying their HIT points and XP points.
   * @param hero1
   * @param hero2
   */
   private static void battle(final Hero hero1, final Hero hero2) {
     checkDamageOverTime(hero1);
     checkDamageOverTime(hero2);
     applyDamageOverTime(hero1);
     applyDamageOverTime(hero2);

     if (hero1.getState() == "alive" && hero2.getState() == "alive") {
       if (hero1.getName() == 'W') {
         hero1.isAttackedBy(hero2, mapOfGame);
         hero2.isAttackedBy(hero1, mapOfGame);
       } else {
         hero2.isAttackedBy(hero1, mapOfGame);
         hero1.isAttackedBy(hero2, mapOfGame);
       }
       hero1.modifyHP(hero2.getDmgwithmodifier1() + hero2.getDmgwithmodifier2());
       hero2.modifyHP(hero1.getDmgwithmodifier1() + hero1.getDmgwithmodifier2());
       checkLevelUp(hero1, hero2);
       checkLevelUp(hero2, hero1);
     }
   }
  /**
   * Shows the flow of the game, each hero plays in its own way against another.
   * @param gameInput
   */
   public static void startGame(final GameInput gameInput) {
      int noRounds = gameInput.getRounds();
      for (int i = 0; i < noRounds; i++) {
        for (Hero hero : heroes) {
          if (hero.getState().equals("alive")) {
            if (hero.getDot().getNumRoundsParalysis() != 0) {
              if (hero.getDot().getCurrRound() < hero.getDot().getNumRoundsParalysis()) {
                hero.getLocation().setMoveNoWhere();
              }
            } else {
              hero.getLocation().executeMove(moves.get(i).get(hero.getId()));
            }
          }
        }
        for (Hero hero1 : heroes) {
          if (hero1.getState().equals("alive")) {
            for (Hero hero2 : heroes) {
              if (hero1.getLocation().equals(hero2.getLocation()) && (hero1.getId()
                  != hero2.getId()) && !hero2.isVerified() && hero2.getState().equals("alive")) {
                hero1.setVerified(true);
                hero2.setVerified(true);
                battle(hero1, hero2);
              }
            }
          }
        }
        for (Hero hero : heroes) {
          hero.setVerified(false);
        }
      }
   }

   public static ArrayList<Hero> getHeroes() {
    return heroes;
  }
}

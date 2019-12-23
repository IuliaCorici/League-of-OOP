package main.game;

import main.angel.Angel;
import main.angel.AngelFactory;
import main.angel.AngelVisitor;
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
  private static ArrayList<ArrayList<Angel>> angels = new ArrayList<ArrayList<Angel>>();

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
      row = row * 10 + (heroesDetails.get(i).get(2) - '0');
      int col = heroesDetails.get(i).get(3) - '0';
      col = col * 10 + (heroesDetails.get(i).get(4) - '0');
      location.initLocation(row, col);
      heroes.get(i).setHero(location, i, name);
    }
    mapOfGame.makeMap(gameInput.getMap());
    moves = gameInput.getMoves();
    ArrayList<ArrayList<String>> angelsDetails = gameInput.getAngels();
    for (ArrayList<String> angelDetails : angelsDetails) {
      ArrayList<Angel> angelsPerRound = new ArrayList<Angel>();
      for (String angel : angelDetails) {
        String[] angelDetails1 = angel.split(",");
        angelsPerRound.add(AngelFactory.createAngel(angelDetails1[0]));
      }
      angels.add(angelsPerRound);
    }
    for (int i = 0; i < angelsDetails.size(); i++) {
      for (int j = 0; j < angelsDetails.get(i).size(); j++) {
        Location location = new Location();
        String[] angelDetails1 = angelsDetails.get(i).get(j).split(",");
        String name = angelDetails1[0];
        int row;
        if (angelDetails1[1].length() == 1) {
          row = angelDetails1[1].charAt(0) - '0';
        } else {
          row = (angelDetails1[1].charAt(0) - '0') * 10 + (angelDetails1[1].charAt(1) - '0');
        }
        int col;
        if (angelDetails1[2].length() == 1) {
          col = angelDetails1[2].charAt(0) - '0';
        } else {
          col = (angelDetails1[2].charAt(0) - '0') * 10 + (angelDetails1[2].charAt(1) - '0');
        }
        location.initLocation(row, col);
        angels.get(i).get(j).setAngel(location, name);
      }
    }
//    for (int i = 0; i < angels.size(); i++) {
//      System.out.println(angels.get(i).get(1).getType().toString());
//    }
  }

  /**
   * Checks if there was any damage over time applied over that hero.
   * @param hero
   */
  private static void checkDamageOverTime(final Hero hero) {
    if (hero.getDot().getNumRounds() != 0) {
      int currRound = hero.getDot().getCurrRound();
      if (currRound < hero.getDot().getNumRounds()) {
        hero.getDot().setCurrRound(currRound + 1);
      } else {
        DamageOverTime dot = new DamageOverTime();
        hero.setDot(dot);
//        System.out.println(hero.getDot().getNumRounds() + " " + hero.getDot().getCurrRound()
//            + " runde ramase pentru jucatorul  " + hero.getName() + hero.getId() + " cu dmg " + hero.getDot().getPerRoundDMG());
      }
    }
  }

  /**
   * Applies the damage over time gained by the hero.
   * @param hero
   */
   private static void applyDamageOverTime(final Hero hero) {
     int dmgOverTime = hero.getDot().getPerRoundDMG();
     System.out.println(hero.getDot().getNumRounds() + " " + hero.getDot().getCurrRound()
         + " runde ramase pentru jucatorul  " + hero.getName() + hero.getId() + " cu dmg " + hero.getDot().getPerRoundDMG());
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
     //checkDamageOverTime(hero1);
     //checkDamageOverTime(hero2);
//     applyDamageOverTime(hero1);
//     applyDamageOverTime(hero2);

     if (hero1.getState().equals("alive") && hero2.getState().equals("alive")) {
       if (hero1.getName() == 'W') {
         hero1.isAttackedBy(hero2, mapOfGame);
         hero2.isAttackedBy(hero1, mapOfGame);
       } else {
         hero2.isAttackedBy(hero1, mapOfGame);
         hero1.isAttackedBy(hero2, mapOfGame);
       }
       int dmg1 = hero2.getDmgwithmodifier1() + hero2.getDmgwithmodifier2();
       int dmg2 = hero1.getDmgwithmodifier1() + hero1.getDmgwithmodifier2();
       hero1.modifyHP(dmg1);
       hero2.modifyHP(dmg2);
       System.out.println(hero1.getCurrHp() + " " + hero2.getCurrHp());
       if (hero1.getCurrHp() < 0 && hero2.getCurrHp() < 0) {
         hero1.setState("dead");
         hero2.setState("dead");
       } else {
         checkLevelUp(hero2, hero1);
         checkLevelUp(hero1, hero2);
       }
     }
   }
  /**
   * Shows the flow of the game, each hero plays in its own way against another.
   * @param gameInput
   */
   public static void startGame(final GameInput gameInput) {
     int noRounds = gameInput.getRounds();
     for (int i = 0; i < noRounds; i++) {
       System.out.println("runda " + i);
       for (Hero hero : heroes) {
          if (hero.getState().equals("alive")) {
            checkDamageOverTime(hero);
            applyDamageOverTime(hero);
            hero.chooseStrategy();
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
        for (Hero hero : heroes) {
          if (hero.getState().equals("alive")) {
            System.out.println(hero.getName() + " " + hero.getLevel() + " "
                + hero.getXp() + " " + hero.getCurrHp() + " " + hero.getLocation().getRow() + " " + hero.getLocation().getCol());
          } else {
            System.out.println(hero.getName() + " " + hero.getState());
          }
          for (AngelVisitor v : angels.get(i)) {
            hero.accept(v);
            int level = hero.getLevel();
            int newLevel = (hero.calculateLevelUp(hero.getXp()));
            if (newLevel > level) {
              hero.setLevel(newLevel);
              hero.setCurrHp(hero.getBonusLevel() * hero.getLevel() + hero.getHpMaximum());
            }
            if (hero.getState().equals("alive")) {
              System.out.println(hero.getName() + " " + hero.getLevel() + " "
                  + hero.getXp() + " " + hero.getCurrHp() + " " + hero.getLocation().getRow() + " " + hero.getLocation().getCol());
            } else {
              System.out.println(hero.getName() + " " + hero.getState());
            }
          }
        }

      }
   }

   public static ArrayList<Hero> getHeroes() {
    return heroes;
  }
}

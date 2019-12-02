package main.game;

import main.hero.*;
import main.map.*;

import java.util.ArrayList;

public class GameEngine {
  private static MapoFGame mapOfGame = MapoFGame.getInstance();
  private static ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
  private static ArrayList<Hero> heroes = new ArrayList<Hero>();

  public GameEngine() { }

  public static ArrayList<Hero> getHeroes() {
    return heroes;
  }

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
    for (Hero hero : heroes) {
     System.out.println(hero.getName() + " " + hero.getId() + " " + hero.getLocation().getRow() +", " + hero.getLocation().getCol());
    }
    System.out.println(moves);
    //System.out.println(mapOfGame.getPieceOfMap(0,0).);
  }


   private static void battle(Hero hero1, Hero hero2) {
     System.out.println(hero1.getId() + " " + hero1.getDot().getNumRoundsParalysis());
     if (hero1.getDot().getNumRounds() != 0) {
       int currRound = hero1.getDot().getCurrRound();
       if (currRound < hero1.getDot().getNumRounds()) {
         hero1.getDot().setCurrRound(currRound + 1);
       } else {
         DamageOverTime dot = new DamageOverTime();
         hero1.setDot(dot);
       }
     }
     if (hero2.getDot().getNumRounds() != 0) {
       int currRound = hero2.getDot().getCurrRound();
       if (currRound < hero2.getDot().getNumRounds()) {
         hero2.getDot().setCurrRound(currRound + 1);
       } else {
         DamageOverTime dot = new DamageOverTime();
         hero2.setDot(dot);
       }
     }
     int dmgOverTime = hero1.getDot().getPerRoundDMG();
     hero1.modifyHP(dmgOverTime);
     if (hero1.getCURR_HP() <= 0) {
       hero1.setState("dead");
     }
     System.out.println("a i " + hero1.getCURR_HP());
       dmgOverTime = hero2.getDot().getPerRoundDMG();
       hero2.modifyHP(dmgOverTime);
       if (hero2.getCURR_HP() <= 0) {
         hero2.setState("dead");
       }
       if (hero1.getState() == "alive" && hero2.getState() == "alive") {
         if (hero1.getName() == 'W') {
           hero1.isAttackedBy(hero2, mapOfGame);
           hero2.isAttackedBy(hero1, mapOfGame);
         } else {
           hero2.isAttackedBy(hero1, mapOfGame);
           hero1.isAttackedBy(hero2, mapOfGame);
         }
         System.out.println(hero1.getName() + " " + hero1.getDMGwithModifier1() + " " + hero1.getDMGwithModifier2() + " " + hero1.getCURR_HP());
         System.out.println(hero2.getName() + " " + hero2.getDMGwithModifier1() + " " + hero2.getDMGwithModifier2() + " " + hero2.getCURR_HP());

         hero1.modifyHP(hero2.getDMGwithModifier1() + hero2.getDMGwithModifier2());
         hero2.modifyHP(hero1.getDMGwithModifier1() + hero1.getDMGwithModifier2());
//         hero1.setDMGwithModifier1(0);
//         hero1.setDMGwithModifier2(0);
//         hero2.setDMGwithModifier1(0);
//         hero2.setDMGwithModifier2(0);
         if (hero2.getCURR_HP() <= 0) {
           hero2.setState("dead");
           hero1.setXP_WINNER(hero2.getLevel());
           //hero1.setXP(hero1.XP_LEVEL_UP());
           int level = hero1.getLevel();
           int new_level =(hero1.calculateLevelUp(hero1.getXP()));
           if (new_level > level) {
             hero1.setLevel(new_level);
             hero1.setCURR_HP(hero1.getBonusLevel() * hero1.getLevel() + hero1.getHP_MAXIMUM());
           }
         }
         if (hero1.getCURR_HP() <= 0) {
           hero1.setState("dead");
           hero2.setXP_WINNER(hero1.getLevel());
           // hero2.setXP(hero2.XP_LEVEL_UP());
           int level = hero2.getLevel();
           int new_level =(hero2.calculateLevelUp(hero1.getXP()));
           if (new_level > level) {
             hero2.setLevel(new_level);
             // hero2.setLevel(hero2.calculateLevelUp(hero2.getXP()));
             hero2.setCURR_HP(hero2.getBonusLevel() * hero2.getLevel() + hero2.getHP_MAXIMUM());
           }
         }
       }

   }

   public static void startGame(final GameInput gameInput) {
      int noRounds = gameInput.getRounds();
      for (int i = 0; i < noRounds; i++) {
        System.out.println("runda " + i);
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
          hero1.setVerified(true);
          for (Hero hero2 : heroes) {
            if (hero1.getLocation().equals(hero2.getLocation()) && (hero1.getId() != hero2.getId())
                && !hero2.isVerified()) {
              hero2.setVerified(true);
              battle(hero1, hero2);
              System.out.println(hero1.getName()+ " " + hero1.getId() + " " + hero1.getCURR_HP()
                  + " " + hero1.getState());
              System.out.println(hero2.getName()+ " " + hero2.getId() + " " + hero2.getCURR_HP()
                  + " " + hero2.getState());
            }
          }
        }
        for (Hero hero : heroes) {
          hero.setVerified(false);
        }
      }
   }

}

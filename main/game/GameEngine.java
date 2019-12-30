package main.game;

import fileio.FileSystem;
import main.angel.Angel;
import main.angel.AngelFactory;
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
  public static ArrayList<Angel> angelToBe = new ArrayList<Angel>();
  public static FileSystem fs = null;

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
//     System.out.println(hero.getDot().getNumRounds() + " " + hero.getDot().getCurrRound()
//         + " runde ramase pentru jucatorul  " + hero.getName() + hero.getId() + " cu dmg " + hero.getDot().getPerRoundDMG());
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
   public static void checkLevelUp(final Hero hero1, final Hero hero2) {
     if (hero2.getCurrHp() <= 0) {
       hero2.setState("dead");
       System.out.println(hero1.getLevel() + " " + hero2.getLevel());
       hero1.setXpWinner(hero2.getLevel());
       int level = hero1.getLevel();
       int newLevel = (hero1.calculateLevelUp(hero1.getXp()));
       System.out.println(newLevel);
       if (newLevel > level) {
         for ( int i = level + 1; i <= newLevel; i++) {
           hero1.setLevel(newLevel);
           System.out.println(hero1.getName() + " reached level " + i);
           try {
             FileSystem fs = GameEngine.fs;
             fs.writeWord(hero1.getSurname() + " " + hero1.getId() + " reached level " + i);
             fs.writeWord("\n");
             //fs.close();
           } catch (Exception e1) {
             e1.printStackTrace();
           }
         }
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
     if (hero1.getState().equals("alive") && hero2.getState().equals("alive")) {
       if (hero1.getName() == 'W') {
         hero1.isAttackedBy(hero2, mapOfGame);
         hero2.isAttackedBy(hero1, mapOfGame);
       } else {
         hero2.isAttackedBy(hero1, mapOfGame);
         hero1.isAttackedBy(hero2, mapOfGame);
       }
       System.out.println( "dmg pt " + hero1.getSurname() + " " + hero1.getDmgwithmodifier1() + " " + hero1.getDmgwithmodifier2());

       System.out.println( "dmg pt " + hero2.getSurname() + " " +hero2.getDmgwithmodifier1() + " " + hero2.getDmgwithmodifier2());
       int dmg1 = hero2.getDmgwithmodifier1() + hero2.getDmgwithmodifier2();
       int dmg2 = hero1.getDmgwithmodifier1() + hero1.getDmgwithmodifier2();
       System.out.println(hero1.getXp() + " " + hero1.getCurrHp() + " " + hero2.getXp() + " " + hero2.getCurrHp());
       hero1.modifyHP(dmg1);
       hero2.modifyHP(dmg2);
       System.out.println(hero1.getCurrHp() + " " + hero2.getCurrHp());
     }
   }
  /**
   * Shows the flow of the game, each hero plays in its own way against another.
   * @param gameInput
   */
   public static void startGame(final GameInput gameInput, final String inputPath, final String outputPath) {
     int noRounds = gameInput.getRounds();
     try {
       fs = new FileSystem(inputPath, outputPath);
     } catch (Exception e1) {
       e1.printStackTrace();
     }
     for (int i = 0; i < noRounds; i++) {
       try {
         FileSystem fs = GameEngine.fs;
         int n = i + 1;
         fs.writeWord("~~ Round " + n + " ~~");
         fs.writeWord("\n");
         //fs.close();
       } catch (Exception e1) {
         e1.printStackTrace();
       }

       System.out.println("~~ Round " + (i + 1) + " ~~");

       for (Hero hero : heroes) {
         if (hero.getState().equals("alive")) {
           checkDamageOverTime(hero);
           applyDamageOverTime(hero);
           if (hero.getDot().getNumRoundsParalysis() != 0) {
             if (hero.getDot().getCurrRound() < hero.getDot().getNumRoundsParalysis()) {
               hero.getLocation().setMoveNoWhere();
             }
           } else {
             hero.chooseStrategy();
             System.out.println(hero.getCurrHp());
             hero.getLocation().executeMove(moves.get(i).get(hero.getId()));
           }
         }
       }
       Magician magician = new Magician();
       Observers observer1 = new HeroObserver();
       magician.addObservers(observer1);
       for (Hero hero1 : heroes) {
         if (hero1.getState().equals("alive")) {
           for (Hero hero2 : heroes) {
             if (hero1.getLocation().equals(hero2.getLocation()) && (hero1.getId()
                 != hero2.getId()) && !hero2.isVerified() && hero2.getState().equals("alive")) {
               hero1.setVerified(true);
               hero2.setVerified(true);
               if (hero1.getState().equals("alive") && hero2.getState().equals("alive")) {
                 battle(hero1, hero2);
                 if (hero1.getCurrHp() < 0 && hero2.getCurrHp() < 0) {
                   hero1.setState("dead");
                   hero2.setState("dead");
                 }
                 magician.display(i, hero1, hero2);
               }
               System.out.println("se lupta " + hero1.getSurname() + hero1.getId() + " " + hero2.getSurname() + hero2.getId());
               System.out.println(hero1.getXp() + " " + hero1.getCurrHp() + " " + hero2.getXp() + " " + hero2.getCurrHp());
             }
           }
         }
       }
       for (Hero hero : heroes) {
         hero.setVerified(false);
       }
       for (Angel v : angels.get(i)) {
        // System.out.println(hero1.getXp() + " " + hero1.getCurrHp() + " " + hero2.getXp() + " " + hero2.getCurrHp());
         for (Hero hero : heroes) {
           if (v.getLocation().equals(hero.getLocation())) {
             hero.accept(v);
           }
         }
           //System.out.println(hero1.getXp() + " " + hero1.getCurrHp() + " " + hero2.getXp() + " " + hero2.getCurrHp());

           magician.removeObservers(observer1);
           Observers observer2 = new AngelObserver();
           angelToBe.add(v);
           magician.addObservers(observer2);
           magician.display(i, heroes.get(0), heroes.get(1));
           angelToBe.remove(0);
           magician.removeObservers(observer2);
       }
       try {
         FileSystem fs = GameEngine.fs;
         fs.writeWord("\n");
       } catch (Exception e1) {
         e1.printStackTrace();
       }
       for (Hero hero : heroes)
         System.out.println(hero.getName() + " " + hero.getLevel() + " "
             + hero.getXp() + " " + hero.getCurrHp() + " " + hero.getLocation().getRow() + " " + hero.getLocation().getCol());
     }
     System.out.println("~~ Results ~~");
     try {
       FileSystem fs = GameEngine.fs;
       fs.writeWord("~~ Results ~~");
       fs.writeWord("\n");
     } catch (Exception e1) {
       e1.printStackTrace();
     }

     for (Hero hero : heroes) {

       if (hero.getState().equals("alive")) {
         System.out.println(hero.getName() + " " + hero.getLevel() + " "
             + hero.getXp() + " " + hero.getCurrHp() + " " + hero.getLocation().getRow() + " " + hero.getLocation().getCol());
         try {
           FileSystem fs = GameEngine.fs;
           fs.writeWord(hero.getName() + " " + hero.getLevel() + " "
               + hero.getXp() + " " + hero.getCurrHp() + " " + hero.getLocation().getRow() + " " + hero.getLocation().getCol());
           fs.writeWord("\n");
         } catch (Exception e1) {
           e1.printStackTrace();
         }
       } else {
         try {
           FileSystem fs = GameEngine.fs;
           fs.writeWord(hero.getName() + " " + hero.getState());
           fs.writeWord("\n");
         } catch (Exception e1) {
           e1.printStackTrace();
         }
         System.out.println(hero.getName() + " " + hero.getState());
       }

     }
     try {
       fs.close();
     } catch (Exception e1) {
       e1.printStackTrace();
     }
   }

  public static ArrayList<ArrayList<Angel>> getAngels() {
    return angels;
  }

  public static ArrayList<Hero> getHeroes() {
    return heroes;
  }
}

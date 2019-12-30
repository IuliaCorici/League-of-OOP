package main.game;

import fileio.FileSystem;
import main.angel.Angel;
import main.hero.Hero;

import java.util.ArrayList;

public class AngelObserver implements Observers {
  @Override
  public void update(int round, Hero hero1, Hero hero2) {
    ArrayList<Angel> angels = GameEngine.angelToBe;

      for (Angel angel : angels) {
        System.out.println("Angel " + angel.getName() + " was spawned at "
            + angel.getLocation().getRow() + " " + angel.getLocation().getCol());
        try {
          FileSystem fs = GameEngine.fs;
          fs.writeWord("Angel " + angel.getName() + " was spawned at "
              + angel.getLocation().getRow() + " " + angel.getLocation().getCol());
          fs.writeWord("\n");
        } catch (Exception e1) {
          e1.printStackTrace();
        }
        for (Hero hero : GameEngine.getHeroes()) {
          switch (angel.getType().toString()) {
            case "Bad":
              if (angel.getName().equals("TheDoomer")) {
                System.out.println(hero.getSurname() + hero.getId() + " was killed by an angel");
                if (hero.getState().equals("alive") && hero.getLocation().equals(angel.getLocation())) {
                  try {
                    FileSystem fs = GameEngine.fs;
                    fs.writeWord(angel.getName() + " hit " + hero.getSurname() + " "
                        + hero.getId());
                    fs.writeWord("\n");
                    fs.writeWord("Player " + hero.getSurname() + " " + hero.getId()
                        + " was killed by an angel");
                    fs.writeWord("\n");
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                  hero.setState("dead");
                }
                break;
              }

              if (hero.getState().equals("alive") && hero.getLocation().equals(angel.getLocation())) {
                try {
                  FileSystem fs = GameEngine.fs;
                  fs.writeWord(angel.getName() + " hit " + hero.getSurname() + " "
                      + hero.getId());
                  fs.writeWord("\n");
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
                if (hero.getCurrHp() < 0) {
                  hero.setState("dead");
                  try {
                    FileSystem fs = GameEngine.fs;
                    fs.writeWord("Player " + hero.getSurname() + " " + hero.getId()
                        + " was killed by an angel");
                    fs.writeWord("\n");
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                }
                System.out.println(angel.getName() + " hit " + hero.getSurname() + " " + hero.getId());
              }
              break;

            case "Good":
              if (angel.getName().equals("Spawner")) {
                if (hero.getState().equals("dead") && hero.getLocation().equals(angel.getLocation())) {
                  try {
                    FileSystem fs = GameEngine.fs;
                    fs.writeWord(angel.getName() + " helped " + hero.getSurname() + " "
                        + hero.getId());
                    fs.writeWord("\n");
                    fs.writeWord("Player " + hero.getSurname() + " " + hero.getId()
                        + " was brought to life by an angel");
                    fs.writeWord("\n");
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                  hero.setState("alive");
                  System.out.println(hero.getSurname() + " " + hero.getId() + " was brought to life by an angel");
                }
                break;
              }
              if (hero.getState().equals("alive") && hero.getLocation().equals(angel.getLocation())) {
                try {
                  FileSystem fs = GameEngine.fs;
                  fs.writeWord(angel.getName() + " helped " + hero.getSurname() + " "
                      + hero.getId());
                  fs.writeWord("\n");
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
                System.out.println(angel.getName() + " helped " + hero.getSurname() + " "
                    + hero.getId());
                System.out.println(hero1.getXp() + " " + hero.getCurrHp());
                int level = hero.getLevel();
                int newLevel = (hero.calculateLevelUp(hero.getXp()));
                if (newLevel > level) {
                  for ( int i = level + 1; i <= newLevel; i++) {
                    hero.setLevel(newLevel);
                    System.out.println(hero.getName() + " reached level " + i);
                    try {
                      FileSystem fs = GameEngine.fs;
                      fs.writeWord(hero.getSurname() + " " + hero.getId()
                          + " reached level " + i);
                      fs.writeWord("\n");
                      //fs.close();
                    } catch (Exception e1) {
                      e1.printStackTrace();
                    }
                  }
                  hero.setCurrHp(hero.getBonusLevel() * hero.getLevel() + hero.getHpMaximum());
                }
              }
              break;

            default:
              System.out.println(" ");
              break;
          }
      }
    }
  }
}

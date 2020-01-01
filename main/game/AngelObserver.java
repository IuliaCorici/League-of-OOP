package main.game;

import fileio.FileSystem;
import main.angel.Angel;
import main.hero.Hero;

import java.util.ArrayList;

/**
 * Angel Observer which notifies the Great Magician about the showing-up of the Angels and their
 * effect on the Heroes, either it was a help or a hit.
 */

public final class AngelObserver implements Observers {
  @Override
  public void update(final int round, final Hero hero1, final Hero hero2) {
    ArrayList<Angel> angels = GameEngine.getAngelToBe();

      for (Angel angel : angels) {
        try {
          FileSystem fs = GameEngine.getFs();
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
                if (hero.getState().equals("alive")
                    && hero.getLocation().equals(angel.getLocation())) {
                  try {
                    FileSystem fs = GameEngine.getFs();
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

              if (hero.getState().equals("alive")
                  && hero.getLocation().equals(angel.getLocation())) {
                try {
                  FileSystem fs = GameEngine.getFs();
                  fs.writeWord(angel.getName() + " hit " + hero.getSurname() + " "
                      + hero.getId());
                  fs.writeWord("\n");
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
                if (hero.getCurrHp() < 0) {
                  hero.setState("dead");
                  try {
                    FileSystem fs = GameEngine.getFs();
                    fs.writeWord("Player " + hero.getSurname() + " " + hero.getId()
                        + " was killed by an angel");
                    fs.writeWord("\n");
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                }
              }
              break;

            case "Good":
              if (angel.getName().equals("Spawner")) {
                if (hero.getState().equals("dead")
                    && hero.getLocation().equals(angel.getLocation())) {
                  try {
                    FileSystem fs = GameEngine.getFs();
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
                }
                break;
              }
              if (hero.getState().equals("alive")
                  && hero.getLocation().equals(angel.getLocation())) {
                try {
                  FileSystem fs = GameEngine.getFs();
                  fs.writeWord(angel.getName() + " helped " + hero.getSurname() + " "
                      + hero.getId());
                  fs.writeWord("\n");
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
                int level = hero.getLevel();
                int newLevel = hero.calculateLevelUp(hero.getXp());
                if (newLevel > level) {
                  for (int i = level + 1; i <= newLevel; i++) {
                    hero.setLevel(newLevel);
                    try {
                      FileSystem fs = GameEngine.getFs();
                      fs.writeWord(hero.getSurname() + " " + hero.getId()
                          + " reached level " + i);
                      fs.writeWord("\n");
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

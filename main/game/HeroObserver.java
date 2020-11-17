package main.game;

import fileio.FileSystem;
import main.hero.Hero;

/**
 * Hero Observer that notifies the Great Magician of the behaviour of the Heroes either if one is
 * killed by another, or if one performs a level-up after the battle.
 */
public final class HeroObserver implements Observers {
  @Override
  public void update(final Hero hero1, final Hero hero2) {


    if (hero1.getCurrHp() < 0 && hero2.getCurrHp() > 0) {
      try {
        FileSystem fs = GameEngine.getFs();
        fs.writeWord("Player " + hero1.getSurname() + " " + hero1.getId()
            + " was killed by " + hero2.getSurname() + " " + hero2.getId());
        fs.writeWord("\n");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      GameEngine.checkLevelUp(hero2, hero1);
    } else {
      if (hero1.getState().equals("dead") && hero2.getState().equals("dead")) {
        try {
          FileSystem fs = GameEngine.getFs();
          fs.writeWord("Player " + hero2.getSurname() + " " + hero2.getId()
              + " was killed by " + hero1.getSurname() + " " + hero1.getId());
          fs.writeWord("\n");
          fs.writeWord("Player " + hero1.getSurname() + " " + hero1.getId()
              + " was killed by " + hero2.getSurname() + " " + hero2.getId());
          fs.writeWord("\n");
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      } else {
        if (hero2.getCurrHp() < 0 && hero1.getCurrHp() > 0) {
          try {
            FileSystem fs = GameEngine.getFs();
            fs.writeWord("Player " + hero2.getSurname() + " " + hero2.getId()
                + " was killed by " + hero1.getSurname() + " " + hero1.getId());
            fs.writeWord("\n");
          } catch (Exception e1) {
            e1.printStackTrace();
          }
          GameEngine.checkLevelUp(hero1, hero2);
        }
      }
    }
  }
}


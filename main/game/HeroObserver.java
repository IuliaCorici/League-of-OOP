package main.game;

import fileio.FileSystem;
import main.hero.Hero;

public class HeroObserver implements Observers {
  @Override
  public void update(int round, Hero hero1, Hero hero2) {

    if (hero1.getCurrHp() < 0 && hero2.getCurrHp() > 0) {
      try {
        FileSystem fs = GameEngine.fs;
        fs.writeWord("Player " + hero1.getSurname() + " " + hero1.getId() + " was killed by "
            + hero2.getSurname() + " " + hero2.getId());
        fs.writeWord("\n");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      GameEngine.checkLevelUp(hero2, hero1);
      System.out.println("Player " + hero2.getSurname() + " " + hero2.getId() + " was killed by "
          + hero1.getSurname() + " " + hero1.getId());
    } else {
      if (hero1.getState().equals("dead") && hero2.getState().equals("dead")) {
        try {
          FileSystem fs = GameEngine.fs;
          fs.writeWord("Player " + hero2.getSurname() + " " + hero2.getId() + " was killed by "
              + hero1.getSurname() + " " + hero1.getId());
          fs.writeWord("\n");
          fs.writeWord("Player " + hero1.getSurname() + " " + hero1.getId() + " was killed by "
              + hero2.getSurname() + " " + hero2.getId());
          fs.writeWord("\n");
          //fs.close();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
        System.out.println("Player " + hero2.getSurname() + " " + hero2.getId() + " was killed by "
            + hero1.getSurname() + " " + hero1.getId());
        System.out.println("Player " + hero1.getSurname() + " " + hero1.getId() + " was killed by "
            + hero2.getSurname() + " " + hero2.getId());
      } else {
        if (hero2.getCurrHp() < 0 && hero1.getCurrHp() > 0) {
          try {
            FileSystem fs = GameEngine.fs;
            fs.writeWord("Player " + hero2.getSurname() + " " + hero2.getId() + " was killed by "
                + hero1.getSurname() + " " + hero1.getId());
            fs.writeWord("\n");
          } catch (Exception e1) {
            e1.printStackTrace();
          }
          GameEngine.checkLevelUp(hero1, hero2);
          System.out.println("Player " + hero2.getSurname() + " " + hero2.getId() + " was killed by "
              + hero1.getSurname() + " " + hero1.getId());
        }
      }
    }
  }
}


package main.game;

import main.hero.Hero;

public interface Observers {
  void update(int round, Hero hero1, Hero hero2);
}

package main.game;

import main.hero.Hero;

/**
 * Observer interface for the Observer pattern.
 */
public interface Observers {
  void update(int round, Hero hero1, Hero hero2);
}

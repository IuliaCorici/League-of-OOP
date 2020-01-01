package main.game;

import main.hero.Hero;

import java.util.ArrayList;

/**
 * Observable "Great Magician" for the Observer Pattern.
 */
public final class Magician {
   private ArrayList<Observers> observers = new ArrayList<Observers>();

   public Magician() {
   }

   public void addObservers(final Observers observer) {
     this.observers.add(observer);
   }

   public void removeObservers(final Observers observer) {
     this.observers.remove(observer);
   }

   public void display(final int round, final Hero hero1, final Hero hero2) {
     for (Observers observer : observers) {
       observer.update(round, hero1, hero2);
     }
   }
}

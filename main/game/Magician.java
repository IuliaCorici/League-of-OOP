package main.game;

import main.hero.Hero;

import java.util.ArrayList;

public class Magician {
   private ArrayList<Observers> observers = new ArrayList<Observers>();

   public Magician() {
   }

   public void addObservers(Observers observer) {
     this.observers.add(observer);
   }

   public void removeObservers(Observers observer) {
     this.observers.remove(observer);
   }

   public void display(int round, Hero hero1, Hero hero2) {
     for (Observers observer : observers) {
       observer.update(round, hero1, hero2);
     }
   }
}

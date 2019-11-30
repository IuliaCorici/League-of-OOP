package main;

import hero.Hero;
import hero.HeroFactory;
import hero.Location;
import hero.map.Map;
import hero.map.Terrain;

import java.util.ArrayList;

public class GameEngine {
  private static ArrayList<ArrayList<Terrain>> mapOfGame = new ArrayList<ArrayList<Terrain>>();
  private static ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
  private static ArrayList<Hero> heroes = new ArrayList<Hero>();

  public GameEngine() { }



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

    mapOfGame = Map.getInstance().makeMap(gameInput.getMap());
    moves = gameInput.getMoves();
    for (Hero hero : heroes) {
     System.out.println(hero.getName() + " "+ hero.getId() + " "+ hero.getLocation().getRow() +", " + hero.getLocation().getCol());
    }
    System.out.println(moves);
    //System.out.println(mapOfGame.get(0).get(2).getName());
  }

   public static void startGame(final GameInput gameInput) {
      int noRounds = gameInput.getRounds();
      for (int i = 0; i < noRounds; i++) {
        System.out.println("runda " + i);
        for (Hero hero : heroes) {
          if (hero.getState().equals("alive")) {
            hero.getLocation().executeMove(moves.get(i).get(hero.getId()));
            System.out.println(hero.getName() + " " + hero.getId() + " " + hero.getLocation().getRow() + ", " + hero.getLocation().getCol());
          }
        }
      }
   }

}

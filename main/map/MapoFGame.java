package main.map;

import java.util.ArrayList;

public final class MapoFGame {

  private ArrayList<ArrayList<Terrain>> mapOfGame;
  private static MapoFGame instance = null;

  private MapoFGame() {
    mapOfGame = new ArrayList<ArrayList<Terrain>>();
  }

  public void makeMap(ArrayList<ArrayList<Terrain>> map) {
    mapOfGame.addAll(map);
  }

  public Terrain getPieceOfMap(int i, int j) {
    return mapOfGame.get(i).get(j);
  }

  public static MapoFGame getInstance() {
    if (instance == null) {
      instance = new MapoFGame();
    }
    return instance;
  }

}
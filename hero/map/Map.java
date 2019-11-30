package hero.map;

import java.util.ArrayList;

public final class Map {

  private ArrayList<ArrayList<Terrain>> mapOfGame;
  private static Map instance = null;

  private Map() {
    mapOfGame = new ArrayList<ArrayList<Terrain>>();
  }

  public ArrayList<ArrayList<Terrain>> makeMap(ArrayList<ArrayList<Terrain>> map) {
    mapOfGame.addAll(map);
    return mapOfGame;
  }
  public Terrain getPieceOfMap(int i, int j) {
    return mapOfGame.get(i).get(j);
  }

  public static Map getInstance() {
    if (instance == null) {
      instance = new Map();
    }
    return instance;
  }

}

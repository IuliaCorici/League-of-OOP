package main.game;

import main.map.Terrain;

import java.util.ArrayList;

/**
 * Class created in order to help separate into various fields the read the input data.
 */
public class GameInput {
  private ArrayList<ArrayList<Terrain>> map = new ArrayList<ArrayList<Terrain>>();
  private ArrayList<ArrayList<Character>> heroes = new ArrayList<ArrayList<Character>>();
  private ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
  private ArrayList<ArrayList<String>> angels = new ArrayList<ArrayList<String>>();
  private int mRounds;

  public GameInput() {
    map = null;
    heroes = null;
    moves = null;
    angels = null;
    mRounds = -1;
  }

  public GameInput(final ArrayList<ArrayList<Terrain>> map, final ArrayList<ArrayList<Character>>
      heroes, final ArrayList<ArrayList<Character>> moves, final int rounds,
                   final ArrayList<ArrayList<String>> angels) {
    this.map = map;
    this.heroes = heroes;
    this.moves = moves;
    this.angels = angels;
    mRounds = rounds;
  }

  public final ArrayList<ArrayList<Character>> getHeroes() {
    return heroes;
  }

  public final ArrayList<ArrayList<String>> getAngels() {
    return angels;
  }

  public  final ArrayList<ArrayList<Character>> getMoves() {
    return moves;
  }

  public final ArrayList<ArrayList<Terrain>> getMap() {
    return map;
  }

  public final int getRounds() {
    return mRounds;
  }

}

package main;

import hero.map.Terrain;

import java.util.ArrayList;

public class GameInput {
  private ArrayList<ArrayList<Terrain>> map = new ArrayList<ArrayList<Terrain>>();
  private ArrayList<ArrayList<Character>> heroes = new ArrayList<ArrayList<Character>>();
  private ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
  private int mRounds;

  public GameInput() {
    map = null;
    heroes = null;
    moves = null;
    mRounds = -1;
  }

  public GameInput(ArrayList<ArrayList<Terrain>> map, ArrayList<ArrayList<Character>> heroes,
                   ArrayList<ArrayList<Character>> moves, int rounds) {
    this.map = map;
    this.heroes = heroes;
    this.moves = moves;
    mRounds = rounds;
  }

  public ArrayList<ArrayList<Character>> getHeroes() {
    return heroes;
  }

  public ArrayList<ArrayList<Character>> getMoves() {
    return moves;
  }

  public ArrayList<ArrayList<Terrain>> getMap() {
    return map;
  }

  public final int getRounds() {
    return mRounds;
  }

}

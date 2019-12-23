package main.game;

import fileio.FileSystem;
import main.map.Terrain;
import main.map.TerrainFactory;

import java.util.ArrayList;

import static main.helpers.Constants.THREE;

/**
 * Class that reads the data from the input file.
 */

public final class GameInputLoader {
  private final String mInputPath;
  private final String mOutputPath;

  public GameInputLoader(final String inputPath, final String outputPath) {
    mInputPath = inputPath;
    mOutputPath = outputPath;
  }


  public GameInput load() {
    ArrayList<ArrayList<Terrain>> map = new ArrayList<ArrayList<Terrain>>();
    ArrayList<ArrayList<Character>> heroes = new ArrayList<ArrayList<Character>>();
    ArrayList<ArrayList<Character>> moves = new ArrayList<ArrayList<Character>>();
    ArrayList<ArrayList<String>> angels = new ArrayList<ArrayList<String>>();
    int rounds = 0;
    int noPlayers = 0;
    int dimension1 = 0;
    int dimension2 = 0;

    try {
      FileSystem fs = new FileSystem(mInputPath, mOutputPath);
      dimension1 = fs.nextInt();
      dimension2 = fs.nextInt();
      char[][] locations = new char[dimension1][dimension2];
      for (int i = 0; i < dimension1; i++) {
        String word = fs.nextWord();
        locations[i] = word.toCharArray();
      }

      for (int i = 0; i < dimension1; i++) {
        ArrayList<Terrain> terrains = new ArrayList<Terrain>();
        for (int j = 0; j < dimension2; j++) {
          terrains.add(TerrainFactory.getInstance().getTerrainByChar(locations[i][j]));
        }
        map.add(terrains);
      }

      noPlayers = fs.nextInt();

      for (int i = 0; i < noPlayers; i++) {
        ArrayList<Character> heroDetails = new ArrayList<Character>();
        for (int j = 0; j < THREE; j++) {
          String heroDetail = fs.nextWord();
          char[] aux = heroDetail.toCharArray();
          if (aux.length == 1 ) {
            if (aux[0] < 58) {
              heroDetails.add('0');
            }
            heroDetails.add(aux[0]);
          } else {
            heroDetails.add(aux[0]);
            heroDetails.add(aux[1]);
          }
        }
        heroes.add(heroDetails);
      }
      rounds = fs.nextInt();
      for (int i = 0; i < rounds; i++) {
        String movings = fs.nextWord();
        char[] movingsByChar = movings.toCharArray();
        ArrayList<Character> movesForEachRound = new ArrayList<Character>();
        for (int j = 0; j < movingsByChar.length; j++) {
          movesForEachRound.add(movingsByChar[j]);
        }
        moves.add(movesForEachRound);
      }

      for (int i = 0; i < rounds; i++) {
        int numberOfAngels = fs.nextInt();
        ArrayList<String> angelsPerRound = new ArrayList<>();
        for (int j = 0; j < numberOfAngels; j++) {
          angelsPerRound.add(fs.nextWord());
        }
        angels.add(angelsPerRound);
      }
      fs.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return new GameInput(map, heroes, moves, rounds, angels);
  }
}

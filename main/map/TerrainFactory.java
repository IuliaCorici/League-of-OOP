package main.map;

import java.util.HashMap;
import java.util.Map;

public final class TerrainFactory {

  private static TerrainFactory instance;

  private static class NameTerrain {
    public static final Character  nameWoods = 'W';
    public static final Character  nameVolcanic = 'V';
    public static final Character  nameDesert = 'D';
    public static final Character nameLand = 'L';
  }

  private static class RogueTerrainModifier {
    public static final float rogueWoods =1.15f;
    public static final float rogueDesert = 1f;
    public static final float rogueLand = 1f;
    public static final float rogueVolcanic = 1f;
  }

  private static class WizardTerrainModifier {
    public static final float wizardWoods =1f;
    public static final float wizardDesert = 1.1f;
    public static final float wizardLand = 1f;
    public static final float wizardVolcanic = 1f;
  }

  private static class PyromancerTerrainModifier {
    public static final float pyromancerWoods = 1f;
    public static float pyromancerDesert = 1f;
    public static float pyromancerLand = 1f;
    public static float pyromancerVolcanic = 1.25f;
  }

  private static class KnightTerrainModifier {
    public static final float knightWoods = 1f;
    public static final float knightDesert = 1f;
    public static final float knightLand = 1.15f;
    public static final float knightVolcanic = 1f;
  }

  private  Map<Character, Terrain> terrainByChar;

  private TerrainFactory() {
    terrainByChar = new HashMap<Character, Terrain>();

    initTerrain();
  }

  private void initTerrain() {
    Terrain terrain0 = new Terrain(NameTerrain.nameDesert, TerrainType.Desert,
        PyromancerTerrainModifier.pyromancerDesert, RogueTerrainModifier.rogueDesert,
        WizardTerrainModifier.wizardDesert, KnightTerrainModifier.knightDesert);
    Terrain terrain1 = new Terrain(NameTerrain.nameWoods, TerrainType.Woods,
        PyromancerTerrainModifier.pyromancerWoods, RogueTerrainModifier.rogueWoods,
        WizardTerrainModifier.wizardWoods, KnightTerrainModifier.knightWoods);
    Terrain terrain2 = new Terrain(NameTerrain.nameVolcanic, TerrainType.Volcanic,
        PyromancerTerrainModifier.pyromancerVolcanic, RogueTerrainModifier.rogueVolcanic,
        WizardTerrainModifier.wizardVolcanic, KnightTerrainModifier.knightVolcanic);
    Terrain terrain3 = new Terrain(NameTerrain.nameLand, TerrainType.Land,
        PyromancerTerrainModifier.pyromancerLand, RogueTerrainModifier.rogueLand,
        WizardTerrainModifier.wizardLand, KnightTerrainModifier.knightLand);
    terrainByChar.put(NameTerrain.nameDesert, terrain0);
    terrainByChar.put(NameTerrain.nameWoods, terrain1);
    terrainByChar.put(NameTerrain.nameVolcanic, terrain2);
    terrainByChar.put(NameTerrain.nameLand, terrain3);
  }

  public static TerrainFactory getInstance() {
    if (instance == null) {
      instance = new TerrainFactory();
    }
    return instance;
  }

  public Terrain getTerrainByChar(final Character name) {return terrainByChar.get(name);}

  public Map<Character, Terrain> getAllTerrains() {return terrainByChar;}
}

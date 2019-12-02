package main.map;

import java.util.HashMap;
import java.util.Map;

public final class TerrainFactory {

  private static TerrainFactory instance;

  private static class NameTerrain {
    public static final Character NAME_WOODS = 'W';
    public static final Character NAME_VOLCANIC = 'V';
    public static final Character NAME_DESERT = 'D';
    public static final Character NAME_LAND = 'L';
  }

  private static class RogueTerrainModifier {
    public static final float ROGUE_WOODS = 1.15f;
    public static final float ROGUE_DESERT = 1f;
    public static final float ROGUE_LAND = 1f;
    public static final float ROGUE_VOLCANIC = 1f;
  }

  private static class WizardTerrainModifier {
    public static final float WIZARD_WOODS = 1f;
    public static final float WIZARD_DESERT = 1.1f;
    public static final float WIZARD_LAND = 1f;
    public static final float WIZARD_VOLCANIC = 1f;
  }

  private static class PyromancerTerrainModifier {
    public static final float PYROMANCER_WOODS = 1f;
    public static final float PYROMANCER_DESERT = 1f;
    public static final float PYROMANCER_LAND = 1f;
    public static final float PYROMANCER_VOLCANIC = 1.25f;
  }

  private static class KnightTerrainModifier {
    public static final float KNIGHT_WOODS = 1f;
    public static final float KNIGHT_DESERT = 1f;
    public static final float KNIGHT_LAND = 1.15f;
    public static final float KNIGHT_VOLCANIC = 1f;
  }

  private  final Map<Character, Terrain> terrainByChar;

  private TerrainFactory() {
    terrainByChar = new HashMap<Character, Terrain>();
    initTerrain();
  }

  private void initTerrain() {
    Terrain terrain0 = new Terrain(NameTerrain.NAME_DESERT, TerrainType.Desert,
        PyromancerTerrainModifier.PYROMANCER_DESERT, RogueTerrainModifier.ROGUE_DESERT,
        WizardTerrainModifier.WIZARD_DESERT, KnightTerrainModifier.KNIGHT_DESERT);
    Terrain terrain1 = new Terrain(NameTerrain.NAME_WOODS, TerrainType.Woods,
        PyromancerTerrainModifier.PYROMANCER_WOODS, RogueTerrainModifier.ROGUE_WOODS,
        WizardTerrainModifier.WIZARD_WOODS, KnightTerrainModifier.KNIGHT_WOODS);
    Terrain terrain2 = new Terrain(NameTerrain.NAME_VOLCANIC, TerrainType.Volcanic,
        PyromancerTerrainModifier.PYROMANCER_VOLCANIC, RogueTerrainModifier.ROGUE_VOLCANIC,
        WizardTerrainModifier.WIZARD_VOLCANIC, KnightTerrainModifier.KNIGHT_VOLCANIC);
    Terrain terrain3 = new Terrain(NameTerrain.NAME_LAND, TerrainType.Land,
        PyromancerTerrainModifier.PYROMANCER_LAND, RogueTerrainModifier.ROGUE_LAND,
        WizardTerrainModifier.WIZARD_LAND, KnightTerrainModifier.KNIGHT_LAND);
    terrainByChar.put(NameTerrain.NAME_DESERT, terrain0);
    terrainByChar.put(NameTerrain.NAME_WOODS, terrain1);
    terrainByChar.put(NameTerrain.NAME_VOLCANIC, terrain2);
    terrainByChar.put(NameTerrain.NAME_LAND, terrain3);
  }

  public static TerrainFactory getInstance() {
    if (instance == null) {
      instance = new TerrainFactory();
    }
    return instance;
  }

  public Terrain getTerrainByChar(final Character name) {
    return terrainByChar.get(name);
  }
}

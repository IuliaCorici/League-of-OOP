package main.map;

public final class Terrain {
  private final char name;
  private final TerrainType terrainType;
  private final float pyromancerModifier;
  private final float rogueModifier;
  private final float wizardModifier;
  private final float knightModifier;

  public Terrain(char name, TerrainType terrainType, float pyromancerModifier, float rogueModifier,
                 float wizardModifier, float knightModifier) {
    this.name = name;
    this.terrainType = terrainType;
    this.pyromancerModifier = pyromancerModifier;
    this.rogueModifier = rogueModifier;
    this.wizardModifier = wizardModifier;
    this.knightModifier = knightModifier;
  }

  public char getName() {
    return name;
  }

  public TerrainType getTerrainType() {
    return terrainType;
  }
}

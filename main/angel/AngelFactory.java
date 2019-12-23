package main.angel;

public class AngelFactory {
  private static AngelFactory instance = null;

  private AngelFactory() { }

  public static AngelFactory getInstance() {
    if (instance == null) {
      instance = new AngelFactory();
    }
    return instance;
  }

  public static Angel createAngel(final String type) {
    switch (type) {
      case "DamageAngel": return new DamageAngel();
      case "DarkAngel": return new DarkAngel();
      case "Dracula": return new Dracula();
      case "GoodBoy": return new GoodBoy();
      case "LevelUpAngel": return new LevelUpAngel();
      case "LifeGiver": return new LifeGiver();
      case "SmallAngel": return new SmallAngel();
      case "Spawner": return new Spawner();
      case "TheDoomer": return new TheDoomer();
      case "XPAngel": return new XPAngel();
      default : return null;
    }
  }
}

package player;

final class HeroFactory {
  private static HeroFactory instance = null;

  private HeroFactory() { }

  static HeroFactory getInstance() {
    if (instance == null) {
      instance = new HeroFactory();
    }
    return instance;
  }

  Hero createHero(final String type) {
    switch (type) {
      case "Pyromancer": return new Pyromancer();
      case "Rogue": return new Rogue();
      case "Knight": return new Knight();
      case "Wizard": return new Wizard();
      default : return null;
    }
  }
}
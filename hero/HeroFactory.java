package hero;

public final class HeroFactory {
  private static HeroFactory instance = null;

  private HeroFactory() { }

  public static HeroFactory getInstance() {
    if (instance == null) {
      instance = new HeroFactory();
    }
    return instance;
  }

  public static Hero createHero(final Character type) {
    switch (type) {
      case 'P': return new Pyromancer();
      case 'R': return new Rogue();
      case 'K': return new Knight();
      case 'W': return new Wizard();
      default : return null;
    }
  }
}
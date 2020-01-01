package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Location;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

/**
 * Abstract class that is the prototype for each kind of ANGEL.
 * Due to the VISTOR pattern every Angel reacts differently to every Hero.
 */
public abstract class Angel implements AngelVisitor {
  private Location location;
  private AngelType type;
  private String name;

  public final void setAngel(final Location location1, final String name1) {
    this.location = location1;
    this.name = name1;
  }
  public final String getName() {
    return name;
  }

  public final AngelType getType() {
    return type;
  }

  public final void setType(final AngelType type) {
    this.type = type;
  }

  public final Location getLocation() {
    return location;
  }

  @Override
  public abstract void visit(Hero hero);

  @Override
  public abstract void visit(Knight knight);

  @Override
  public abstract void visit(Rogue rogue);

  @Override
  public abstract void visit(Pyromancer pyromancer);

  @Override
  public abstract void visit(Wizard wizard);
}

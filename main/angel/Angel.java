package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Location;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public abstract class Angel implements AngelVisitor {
  private Location location;
  private AngelType type;
  private int id;
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

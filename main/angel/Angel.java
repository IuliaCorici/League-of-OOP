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

  public final void setAngel(Location location,  String name) {
    this.location = location;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AngelType getType() {
    return type;
  }

  public void setType(AngelType type) {
    this.type = type;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Location getLocation() {
    return location;
  }

  @Override
  public abstract void visit(Hero hero);

  @Override
  public abstract void visit(Knight knight) ;

  @Override
  public abstract void visit(Rogue rogue);

  @Override
  public abstract void visit(Pyromancer pyromancer);

  @Override
  public abstract void visit(Wizard wizard);
}

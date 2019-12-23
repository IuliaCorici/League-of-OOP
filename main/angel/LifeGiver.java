package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public class LifeGiver extends Angel {
  public LifeGiver() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    knight.modifyHP(-100);
  }

  @Override
  public void visit(Rogue rogue) {
    rogue.modifyHP(-90);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.modifyHP(-80);
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.modifyHP(-120);
  }
}

package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public class TheDoomer extends Angel {
  public TheDoomer() {
    setType(AngelType.Bad);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    knight.setState("dead");
  }

  @Override
  public void visit(Rogue rogue) {
    rogue.setState("dead");
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.setState("dead");
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.setState("dead");
  }
}

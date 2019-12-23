package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public class DarkAngel extends Angel {
  public DarkAngel() {
    setType(AngelType.Bad);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    knight.modifyHP(40);
  }

  @Override
  public void visit(Rogue rogue) {
    rogue.modifyHP(10);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.modifyHP(30);
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.modifyHP(20);
  }
}

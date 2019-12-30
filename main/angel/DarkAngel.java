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
    if (knight.getState().equals("alive"))
    knight.modifyHP(40);
  }

  @Override
  public void visit(Rogue rogue) {
    if (rogue.getState().equals("alive"))
    rogue.modifyHP(10);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive"))
    pyromancer.modifyHP(30);
  }

  @Override
  public void visit(Wizard wizard) {
    if (wizard.getState().equals("alive"))
    wizard.modifyHP(20);
  }
}

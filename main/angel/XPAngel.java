package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public class XPAngel extends Angel {

  public XPAngel() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(Hero hero) {
    System.out.println("frt");
    visit(hero);
  }

  @Override
  public void visit(Knight knight) {
   int xp = knight.getXp() + 45;
   knight.setXp(xp);
  }

  @Override
  public void visit(Rogue rogue) {
   int xp = rogue.getXp() + 40;
   rogue.setXp(xp);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    int xp = pyromancer.getXp() + 50;
    pyromancer.setXp(xp);
  }

  @Override
  public void visit(Wizard wizard) {
    int xp = wizard.getXp() + 60;
    wizard.setXp(xp);
  }
}

package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public class Spawner extends Angel {
  public Spawner() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    if (knight.getState().equals("dead"))
      knight.setCurrHp(200);
  }

  @Override
  public void visit(Rogue rogue) {
    if (rogue.getState().equals("dead"))
      rogue.setCurrHp(180);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    if (pyromancer.getState().equals("dead"))
      pyromancer.setCurrHp(150);
  }

  @Override
  public void visit(Wizard wizard) {
    if (wizard.getState().equals("dead"))
      wizard.setCurrHp(120);
  }
}

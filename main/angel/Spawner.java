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
    knight.setState("alive");
    knight.setCurrHp(200);
  }

  @Override
  public void visit(Rogue rogue) {
    rogue.setState("alive");
    rogue.setCurrHp(180);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.setState("alive");
    pyromancer.setCurrHp(150);
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.setState("alive");
    wizard.setCurrHp(120);
  }
}

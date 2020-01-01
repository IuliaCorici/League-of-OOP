package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public final class TheDoomer extends Angel {
  public TheDoomer() {
    setType(AngelType.Bad);
  }

  @Override
  public void visit(final Hero hero) {

  }

  /**
   * The Doomer sets the HP of an alive hero to a zero value and the transition from ALIVE to DEAD
   * is made in the OBSERVER pattern.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    knight.setCurrHp(0);
  }

  @Override
  public void visit(final Rogue rogue) {
    rogue.setCurrHp(0);
  }

  @Override
  public void visit(final Pyromancer pyromancer) {
     pyromancer.setCurrHp(0);
  }

  @Override
  public void visit(final Wizard wizard) {
     wizard.setCurrHp(0);
  }
}

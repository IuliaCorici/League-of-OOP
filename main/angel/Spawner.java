package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import static main.helpers.Constants.SPAWNER_HP_KNIGHT;
import static main.helpers.Constants.SPAWNER_HP_PYROMANCER;
import static main.helpers.Constants.SPAWNER_HP_ROGUE;
import static main.helpers.Constants.SPAWNER_HP_WIZARD;

public final class Spawner extends Angel {
  public Spawner() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(final Hero hero) {

  }

  /**
   * Spawner sets the HP of a dead hero to a initial value and the transition from DEAD to ALIVE is
   * made in the OBSERVER pattern.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("dead")) {
      knight.setCurrHp(SPAWNER_HP_KNIGHT);
    }
  }

  /**
   * idem.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("dead")) {
      rogue.setCurrHp(SPAWNER_HP_ROGUE);
    }
  }

  /**
   * idem.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("dead")) {
      pyromancer.setCurrHp(SPAWNER_HP_PYROMANCER);
    }
  }

  /**
   * idem.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("dead")) {
      wizard.setCurrHp(SPAWNER_HP_WIZARD);
    }
  }
}

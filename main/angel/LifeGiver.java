package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import static main.helpers.Constants.LIFE_HP_KNIGHT;
import static main.helpers.Constants.LIFE_HP_PYROMANCER;
import static main.helpers.Constants.LIFE_HP_ROGUE;
import static main.helpers.Constants.LIFE_HP_WIZARD;

public final class LifeGiver extends Angel {
  public LifeGiver() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(final Hero hero) {

  }

  /**
   * The way LifeGiver increases the KNIGHT's HP.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(-LIFE_HP_KNIGHT);
    }
  }

  /**
   * The way LifeGiver increases the ROGUE's HP.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(-LIFE_HP_ROGUE);
    }
  }

  /**
   * The way LifeGiver increases the PYROMANCER's HP.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(-LIFE_HP_PYROMANCER);
    }
  }

  /**
   * The way LifeGiver increases the WIZARD's HP.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(-LIFE_HP_WIZARD);
    }
  }
}

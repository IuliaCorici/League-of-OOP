package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import static main.helpers.Constants.DARK_ANGEL_KNIGHT;
import static main.helpers.Constants.DARK_ANGEL_PYROMANCER;
import static main.helpers.Constants.DARK_ANGEL_ROGUE;
import static main.helpers.Constants.DARK_ANGEL_WIZARD;

public final class DarkAngel extends Angel {
  public DarkAngel() {
    setType(AngelType.Bad);
  }

  @Override
  public void visit(final Hero hero) {

  }

  /**
   * The way Dark Angel behaves around a KNIGHT regarding the HP.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(DARK_ANGEL_KNIGHT);
    }
  }

  /**
   * The way Dark Angel behaves around a ROGUE regarding the HP.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(DARK_ANGEL_ROGUE);
    }
  }

  /**
   * The way Dark Angel behaves around a PYROMANCER regarding the HP.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(DARK_ANGEL_PYROMANCER);
    }
  }

  /**
   * The way Dark Angel behaves around a WIZARD regarding the HP.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(DARK_ANGEL_WIZARD);
    }
  }
}

package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import static main.helpers.Constants.XP_KNIGHT;
import static main.helpers.Constants.XP_PYROMANCER;
import static main.helpers.Constants.XP_ROGUE;
import static main.helpers.Constants.XP_WIZARD;

public final class XPAngel extends Angel {

  public XPAngel() {
    setType(AngelType.Good);
  }

  @Override
  public void visit(final Hero hero) {
  }

  /**
   * XP Angel increases the KNIGHT's XP.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      int xp = knight.getXp() + XP_KNIGHT;
      knight.setXp(xp);
    }
  }

  /**
   * XP Angel increases the ROGUE's XP.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      int xp = rogue.getXp() + XP_ROGUE;
      rogue.setXp(xp);
    }
  }

  /**
   * XP Angel increases the PYROMANCER's XP.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      int xp = pyromancer.getXp() + XP_PYROMANCER;
      pyromancer.setXp(xp);
    }
  }

  /**
   * XP Angel increases the WIZARD's XP.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      int xp = wizard.getXp() + XP_WIZARD;
      wizard.setXp(xp);
    }
  }
}

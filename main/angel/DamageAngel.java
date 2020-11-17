package main.angel;


import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.DMG_ANGEL_KNIGHT;
import static main.helpers.Constants.DMG_ANGEL_PYROMANCER;
import static main.helpers.Constants.DMG_ANGEL_ROGUE;
import static main.helpers.Constants.DMG_ANGEL_WIZARD;

public final class DamageAngel extends Angel {

  public DamageAngel() {
    setType(AngelType.Good);
  }

  /**
   * Function that modifies the race modifiers of a Hero.
   * @param hero
   * @param f
   */
  private void modifyDamageModifiers(final Hero hero, final float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      modifiers.add(hero.getRaceModifiers().get(i) + f);
    }
    hero.setRaceModifiers(modifiers);
  }

  @Override
  public void visit(final Hero hero) {

  }

  /**
   * The way Damage Angel behaves around a KNIGHT.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      modifyDamageModifiers(knight, DMG_ANGEL_KNIGHT);
    }
  }

  /**
   * The way Damage Angel behaves around a ROGUE.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      modifyDamageModifiers(rogue, DMG_ANGEL_ROGUE);
    }
  }

  /**
   * The way Damage Angel behaves around a PYROMANCER.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      modifyDamageModifiers(pyromancer, DMG_ANGEL_PYROMANCER);
    }
  }

  /**
   * The way Damage Angel behaves around a WIZARD.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      modifyDamageModifiers(wizard, DMG_ANGEL_WIZARD);
    }
  }
}

package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.SMALL_DMG_KNIGHT;
import static main.helpers.Constants.SMALL_DMG_PYROMANCER;
import static main.helpers.Constants.SMALL_DMG_ROGUE;
import static main.helpers.Constants.SMALL_DMG_WIZARD;
import static main.helpers.Constants.SMALL_HP_KNIGHT;
import static main.helpers.Constants.SMALL_HP_PYROMANCER;
import static main.helpers.Constants.SMALL_HP_ROGUE;
import static main.helpers.Constants.SMALL_HP_WIZARD;

public final class SmallAngel extends Angel {
  public SmallAngel() {
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

  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(-SMALL_HP_KNIGHT);
      modifyDamageModifiers(knight, SMALL_DMG_KNIGHT);
    }
  }

  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(-SMALL_HP_ROGUE);
      modifyDamageModifiers(rogue, SMALL_DMG_ROGUE);
    }
  }

  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(-SMALL_HP_PYROMANCER);
      modifyDamageModifiers(pyromancer, SMALL_DMG_PYROMANCER);
    }
  }

  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(-SMALL_HP_WIZARD);
      modifyDamageModifiers(wizard, SMALL_DMG_WIZARD);
    }
  }
}

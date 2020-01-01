package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.GOOD_DMG_KNIGHT;
import static main.helpers.Constants.GOOD_DMG_PYROMANCER;
import static main.helpers.Constants.GOOD_DMG_ROGUE;
import static main.helpers.Constants.GOOD_DMG_WIZARD;
import static main.helpers.Constants.GOOD_HP_KNIGHT;
import static main.helpers.Constants.GOOD_HP_PYROMANCER;
import static main.helpers.Constants.GOOD_HP_ROGUE;
import static main.helpers.Constants.GOOD_HP_WIZARD;

public final class GoodBoy extends Angel {

  public GoodBoy() {
    setType(AngelType.Good);
  }

  /**
   * Function that makes the race modifiers greater.
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
   * The way Good Boy modifies the race modifiers and HP of a KNIGHT.
   * @param knight
   */
  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(GOOD_HP_KNIGHT);
      modifyDamageModifiers(knight, GOOD_DMG_KNIGHT);
    }
  }

  /**
   * The way Good Boy modifies the race modifiers and HP of a ROGUE.
   * @param rogue
   */
  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(GOOD_HP_ROGUE);
      modifyDamageModifiers(rogue, GOOD_DMG_ROGUE);
    }
  }

  /**
   * The way Good Boy modifies the race modifiers and HP of a PYROMANCER.
   * @param pyromancer
   */
  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(GOOD_HP_PYROMANCER);
      modifyDamageModifiers(pyromancer, GOOD_DMG_PYROMANCER);
    }
  }

  /**
   * The way Good Boy modifies the race modifiers and HP of a WIZARD.
   * @param wizard
   */
  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(GOOD_HP_WIZARD);
      modifyDamageModifiers(wizard, GOOD_DMG_WIZARD);
    }
  }

}

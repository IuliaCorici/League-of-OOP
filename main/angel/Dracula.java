package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.DRACULA_DMG_KNIGHT;
import static main.helpers.Constants.DRACULA_DMG_PYROMANCER;
import static main.helpers.Constants.DRACULA_DMG_ROGUE;
import static main.helpers.Constants.DRACULA_DMG_WIZARD;
import static main.helpers.Constants.DRACULA_HP_KNIGHT;
import static main.helpers.Constants.DRACULA_HP_PYROMANCER;
import static main.helpers.Constants.DRACULA_HP_ROGUE;
import static main.helpers.Constants.DRACULA_HP_WIZARD;
import static main.helpers.Constants.SIX;

public class Dracula extends Angel {
  public  Dracula() {
    setType(AngelType.Bad);
  }

  /**
   * Function that modifies negatively the race modifiers of a HERO, except for a Knight one.
   * @param hero
   * @param f
   */
  private void modifyRaceModifiers(final Hero hero, final float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      if (hero.getSurname().equals("Knight")) {
        if (i == SIX) {
            modifiers.add(hero.getRaceModifiers().get(i));
        } else {
          modifiers.add(hero.getRaceModifiers().get(i) - f);
        }
      } else {
        modifiers.add(hero.getRaceModifiers().get(i) - f);
      }
    }
    hero.setRaceModifiers(modifiers);
  }

  @Override
  public final void visit(final Hero hero) {

  }

  /**
   * The way Dracula modifies the race modifiers and HP of a KNIGHT.
   * @param knight
   */
  @Override
  public final void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(DRACULA_HP_KNIGHT);
      modifyRaceModifiers(knight, DRACULA_DMG_KNIGHT);
    }

  }

  /**
   * The way Dracula modifies the race modifiers and HP of a ROGUE.
   * @param rogue
   */
  @Override
  public final void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(DRACULA_HP_ROGUE);
      modifyRaceModifiers(rogue, DRACULA_DMG_ROGUE);
    }
  }

  /**
   * The way Dracula modifies the race modifiers and HP of a PYROMANCER.
   * @param pyromancer
   */
  @Override
  public final void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(DRACULA_HP_PYROMANCER);
      modifyRaceModifiers(pyromancer, DRACULA_DMG_PYROMANCER);
    }
  }

  /**
   * The way Dracula modifies the race modifiers and HP of a WIZARD.
   * @param wizard
   */
  @Override
  public final void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(DRACULA_HP_WIZARD);
      modifyRaceModifiers(wizard, DRACULA_DMG_WIZARD);
    }
  }
}

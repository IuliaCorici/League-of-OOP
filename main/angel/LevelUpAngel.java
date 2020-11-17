package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.BORDER_VALUE;
import static main.helpers.Constants.FIFTY;
import static main.helpers.Constants.LEVEL_DMG_KNIGHT;
import static main.helpers.Constants.LEVEL_DMG_PYROMANCER;
import static main.helpers.Constants.LEVEL_DMG_ROGUE;
import static main.helpers.Constants.LEVEL_DMG_WIZARD;

public final class LevelUpAngel extends Angel {

  public LevelUpAngel() {
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

  /**
   * Function that calculates the XP needed for the level-up.
   * @param hero
   */
  private void levelUp(final Hero hero) {
    int currLvl = hero.getLevel();
    int xplevelup;
    xplevelup = BORDER_VALUE + (currLvl) * FIFTY;
    hero.setXp(xplevelup);
  }
  @Override
  public void visit(final Hero hero) {
  }

  @Override
  public void visit(final Knight knight) {
    if (knight.getState().equals("alive")) {
      levelUp(knight);
      modifyDamageModifiers(knight, LEVEL_DMG_KNIGHT);
    }
  }

  @Override
  public void visit(final Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      levelUp(rogue);
      modifyDamageModifiers(rogue, LEVEL_DMG_ROGUE);
    }
  }

  @Override
  public void visit(final Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      levelUp(pyromancer);
      modifyDamageModifiers(pyromancer, LEVEL_DMG_PYROMANCER);
    }
  }

  @Override
  public void visit(final Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      levelUp(wizard);
      modifyDamageModifiers(wizard, LEVEL_DMG_WIZARD);
    }
  }
}

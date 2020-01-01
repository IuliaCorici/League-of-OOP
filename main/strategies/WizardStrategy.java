package main.strategies;

import main.hero.Hero;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_W_MAX;
import static main.helpers.Constants.TEN;
import static main.helpers.Constants.W1_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.W2_STRATEGY_PERCENTAGE;

/**
 * Strategy that helps the WIZARD hero choose the way it is going to prepare for the battle
 * depending on the HIT points that it has at that level.
 */
public final class WizardStrategy implements Strategy {
  private Wizard wizard;
  public WizardStrategy(final Wizard wizard) {
    this.wizard = wizard;
  }

  /**
   * Function that modifies all the race modifiers.
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
   * Modifies the HP and the modifiers depending on the current HP.
   */
  @Override
  public void prepareForBattle() {
    if ((int) (((float) 1 / FOUR) * HP_W_MAX) < wizard.getCurrHp()
        && wizard.getCurrHp() < (int) (((float) 1 / 2) * HP_W_MAX)) {
      int hp = wizard.getCurrHp() - (int) (((float) 1 / TEN) * wizard.getCurrHp());
      wizard.setCurrHp(hp);
      modifyDamageModifiers(wizard, W1_STRATEGY_PERCENTAGE);
    } else {
      if (wizard.getCurrHp() < (int) (((float) 1 / FOUR) * HP_W_MAX)) {
        int hp = wizard.getCurrHp() + (int) (((float) 1 / FIVE) * wizard.getCurrHp());
        wizard.setCurrHp(hp);
        modifyDamageModifiers(wizard, W2_STRATEGY_PERCENTAGE);
      }
    }
  }
}

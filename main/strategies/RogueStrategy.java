package main.strategies;

import main.hero.Hero;
import main.hero.Rogue;

import java.util.ArrayList;

import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.HP_R_MAX;
import static main.helpers.Constants.R1_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.R2_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.SEVEN;

/**
 * Strategy that helps the ROGUE hero choose the way it is going to prepare for the battle
 * depending on the HIT points that it has at that level.
 */
public final class RogueStrategy implements Strategy {
  private Rogue rogue;

  public RogueStrategy(final Rogue rogue) {
    this.rogue = rogue;
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
    if ((int) (((float) 1 / SEVEN) * HP_R_MAX) < rogue.getCurrHp()
        && rogue.getCurrHp() < (int) (((float) 1 / FIVE) * HP_R_MAX)) {
      int hp = rogue.getCurrHp() - (int) (((float) 1 / SEVEN) * rogue.getCurrHp());
      rogue.setCurrHp(hp);
      modifyDamageModifiers(rogue, R1_STRATEGY_PERCENTAGE);
    } else {
      if (rogue.getCurrHp() < (int) (((float) 1 / SEVEN) * HP_R_MAX)) {
        int hp = rogue.getCurrHp() + (int) (((float) 1 / 2) * rogue.getCurrHp());
        rogue.setCurrHp(hp);
        modifyDamageModifiers(rogue, R2_STRATEGY_PERCENTAGE);
      }
    }
  }
}

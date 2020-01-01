package main.strategies;

import main.hero.Hero;
import main.hero.Pyromancer;

import java.util.ArrayList;

import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_P_MAX;
import static main.helpers.Constants.P1_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.P2_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.THREE;

/**
 * Strategy that helps the PYROMANCER hero choose the way it is going to prepare for the battle
 * depending on the HIT points that it has at that level.
 */
public final class PyromancerStrategy implements Strategy {
  private Pyromancer pyromancer;
  public PyromancerStrategy(final Pyromancer pyromancer) {
    this.pyromancer = pyromancer;
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
    if ((int) (((float) 1 / FOUR) * HP_P_MAX) < pyromancer.getCurrHp()
        && pyromancer.getCurrHp() < (int) (((float) 1 / THREE) * HP_P_MAX)) {
      int hp = pyromancer.getCurrHp() - (int) (((float) 1 / FOUR) * pyromancer.getCurrHp());
      pyromancer.setCurrHp(hp);
      modifyDamageModifiers(pyromancer, P1_STRATEGY_PERCENTAGE);
    } else {
      if (pyromancer.getCurrHp() < (int) (((float) 1 / FOUR) * HP_P_MAX)) {
        int hp = pyromancer.getCurrHp() + (int) (((float) 1 / THREE) * pyromancer.getCurrHp());
        pyromancer.setCurrHp(hp);
        modifyDamageModifiers(pyromancer, P2_STRATEGY_PERCENTAGE);
      }
    }
  }
}

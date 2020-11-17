package main.strategies;

import main.hero.Hero;
import main.hero.Knight;

import java.util.ArrayList;

import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.K1_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.K2_STRATEGY_PERCENTAGE;
import static main.helpers.Constants.SIX;
import static main.helpers.Constants.THREE;
import static main.helpers.Constants.UPPER_BOUND;

/**
 * Strategy that helps the KNIGHT hero choose the way it is going to prepare for the battle
 * depending on the HIT points that it has at that level.
 */
public final class KnightStrategy implements Strategy {
  private Knight knight;

  public KnightStrategy(final Knight knight) {
    this.knight = knight;
  }

  /**
   * Modifies the race modifiers except the KNIGHTvsKNIGHT modifier because it's ZERO.
   * @param hero
   * @param f
   */
  private void modifyDamageModifiers(final Hero hero, final float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      if (i == SIX) {
          modifiers.add(hero.getRaceModifiers().get(i));
      } else {
        modifiers.add(hero.getRaceModifiers().get(i) + f);
      }
    }
    hero.setRaceModifiers(modifiers);
  }

  /**
   * Modifies the HP and the modifiers depending on the current HP.
   */
  @Override
  public void prepareForBattle() {
    int currHp = knight.getCurrHp();
    if (currHp >= UPPER_BOUND) {
      knight.setHpMaximum(currHp);
    }
    if ((int) (((float) 1 / THREE) * knight.getHpMaximum()) < currHp
        && currHp < (int) (((float) 1 / 2) * knight.getHpMaximum())) {
      int hp = knight.getCurrHp() - (int) (((float) 1 / FIVE) * knight.getCurrHp());
      knight.setCurrHp(hp);
      modifyDamageModifiers(knight, K1_STRATEGY_PERCENTAGE);
    } else {
      if (knight.getCurrHp() < (int) (((float) 1 / THREE) * knight.getHpMaximum())) {
        int hp = knight.getCurrHp() + (int) (((float) 1 / FOUR) * knight.getCurrHp());
        knight.setCurrHp(hp);
        modifyDamageModifiers(knight, K2_STRATEGY_PERCENTAGE);
      }
    }
  }
}

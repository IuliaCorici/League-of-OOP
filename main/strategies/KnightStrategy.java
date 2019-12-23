package main.strategies;

import main.hero.Hero;
import main.hero.Knight;

import java.util.ArrayList;

import static main.helpers.Constants.HP_K_MAX;

public class KnightStrategy implements Strategy {
  private Knight knight;

  public KnightStrategy(Knight knight) {
    this.knight = knight;
  }

  private void modifyDamageModifiers(Hero hero, float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      modifiers.add(hero.getRaceModifiers().get(i) + f);
    }
    hero.setRaceModifiers(modifiers);
  }

  @Override
  public void prepareForBattle() {
    int currHp = knight.getCurrHp();
    if ((float)(1/3) * HP_K_MAX < currHp && currHp < ((float)(1/2) * HP_K_MAX)) {
      int hp = knight.getCurrHp() - Math.round((float)(1/5) * knight.getCurrHp());
      knight.setCurrHp(hp);
      modifyDamageModifiers(knight, 0.5f);
    } else {
      if (knight.getCurrHp() < (float)(1/3) * HP_K_MAX) {
        int hp = knight.getCurrHp() + Math.round((float)(1/4) * knight.getCurrHp());
        knight.setCurrHp(hp);
        modifyDamageModifiers(knight,-0.3f);
      }
    }
  }
}

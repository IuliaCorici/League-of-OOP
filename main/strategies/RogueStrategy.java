package main.strategies;

import main.hero.Hero;
import main.hero.Rogue;

import java.util.ArrayList;

import static main.helpers.Constants.HP_R_MAX;

public class RogueStrategy implements Strategy {
  private Rogue rogue;

  public RogueStrategy(Rogue rogue) {
    this.rogue = rogue;
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
    if (Math.round(((float)1/7) * HP_R_MAX) < rogue.getCurrHp() && rogue.getCurrHp() < Math.round(((float)1/5)
        * HP_R_MAX)) {
      int hp = rogue.getCurrHp() - Math.round(((float)1/7) * rogue.getCurrHp());
      rogue.setCurrHp(hp);
      modifyDamageModifiers(rogue, 0.4f);
    } else {
      if (rogue.getCurrHp() < Math.round(((float)1/7) * HP_R_MAX)) {
        int hp = rogue.getCurrHp() + Math.round(((float)1/2) * rogue.getCurrHp());
        rogue.setCurrHp(hp);
        modifyDamageModifiers(rogue,-0.1f);
      }
    }
  }
}

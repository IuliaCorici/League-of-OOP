package main.strategies;

import main.hero.Hero;
import main.hero.Pyromancer;

import java.util.ArrayList;

import static main.helpers.Constants.HP_P_MAX;

public class PyromancerStrategy implements Strategy {
  private Pyromancer pyromancer;
  public PyromancerStrategy(Pyromancer pyromancer) {
    this.pyromancer = pyromancer;
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
    if (Math.round(((float)1/4) * HP_P_MAX) < pyromancer.getCurrHp() && pyromancer.getCurrHp() < Math.round(((float)1/3)
        * HP_P_MAX)) {
      int hp = pyromancer.getCurrHp() - Math.round(((float)1/4) * pyromancer.getCurrHp());
      pyromancer.setCurrHp(hp);
      modifyDamageModifiers(pyromancer, 0.7f);
    } else {
      if (pyromancer.getCurrHp() < Math.round(((float)1/4) * HP_P_MAX)) {
        int hp = pyromancer.getCurrHp() + Math.round(((float)1/3) * pyromancer.getCurrHp());
        pyromancer.setCurrHp(hp);
        modifyDamageModifiers(pyromancer,-0.3f);
      }
    }
  }
}

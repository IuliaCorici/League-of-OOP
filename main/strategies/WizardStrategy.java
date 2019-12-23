package main.strategies;

import main.hero.Hero;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.HP_W_MAX;

public class WizardStrategy implements Strategy {
  private Wizard wizard;
  public WizardStrategy(Wizard wizard) {
    this.wizard = wizard;
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
    if ((float)(1/4) * HP_W_MAX < wizard.getCurrHp() && wizard.getCurrHp() < (float)(1/2) * HP_W_MAX) {
      int hp = wizard.getCurrHp() - Math.round((float)(1/10) * wizard.getCurrHp());
      wizard.setCurrHp(hp);
      modifyDamageModifiers(wizard, 0.6f);
    } else {
      if (wizard.getCurrHp() < (float)(1/4) * HP_W_MAX) {
        int hp = wizard.getCurrHp() + Math.round((float)(1/5) * wizard.getCurrHp());
        wizard.setCurrHp(hp);
        modifyDamageModifiers(wizard,-0.2f);
      }
    }
  }
}

package main.angel;


import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

public class DamageAngel extends Angel {

  public DamageAngel() {
    setType(AngelType.Good);
  }

  private void modifyDamageModifiers(Hero hero, float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      modifiers.add(hero.getRaceModifiers().get(i) + f);
    }
    hero.setRaceModifiers(modifiers);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    if (knight.getState().equals("alive")) {
      modifyDamageModifiers(knight, 0.15f);
    }
  }

  @Override
  public void visit(Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      modifyDamageModifiers(rogue, 0.3f);
    }
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      modifyDamageModifiers(pyromancer, 0.2f);
    }
  }

  @Override
  public void visit(Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      modifyDamageModifiers(wizard, 0.4f);
    }
  }
}

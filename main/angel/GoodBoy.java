package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

public class GoodBoy extends Angel {

  public GoodBoy() {
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
    knight.modifyHP(-20);
    modifyDamageModifiers(knight, 0.4f);
  }

  @Override
  public void visit(Rogue rogue) {
    rogue.modifyHP(-40);
    modifyDamageModifiers(rogue, 0.4f);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.modifyHP(-30);
    modifyDamageModifiers(pyromancer, 0.5f);
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.modifyHP(-50);
    modifyDamageModifiers(wizard,0.3f);
  }
}

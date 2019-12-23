package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

public class Dracula extends Angel {
  public  Dracula() {
    setType(AngelType.Bad);
  }

  private void modifyRaceModifiers(Hero hero, float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      modifiers.add(hero.getRaceModifiers().get(i) - f);
    }
    hero.setRaceModifiers(modifiers);
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    knight.modifyHP(60);
    modifyRaceModifiers(knight,0.2f);

  }

  @Override
  public void visit(Rogue rogue) {
    rogue.modifyHP(35);
    modifyRaceModifiers(rogue,0.1f);
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    pyromancer.modifyHP(40);
    modifyRaceModifiers(pyromancer, 0.3f);
  }

  @Override
  public void visit(Wizard wizard) {
    wizard.modifyHP(20);
    modifyRaceModifiers(wizard, 0.4f);
  }
}

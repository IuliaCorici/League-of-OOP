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
      if (hero.getSurname().equals("Knight")) {
        if (i == 6) {

            modifiers.add(hero.getRaceModifiers().get(i));

        } else {
          modifiers.add(hero.getRaceModifiers().get(i) - f);
        }
      } else {
        modifiers.add(hero.getRaceModifiers().get(i) - f);
      }
    }
    hero.setRaceModifiers(modifiers);
    System.out.println(hero.getRaceModifiers().get(6));
  }

  @Override
  public void visit(Hero hero) {

  }

  @Override
  public void visit(Knight knight) {
    if (knight.getState().equals("alive")) {
      knight.modifyHP(60);
      modifyRaceModifiers(knight, 0.2f);
    }

  }

  @Override
  public void visit(Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      rogue.modifyHP(35);
      modifyRaceModifiers(rogue, 0.1f);
    }
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      pyromancer.modifyHP(40);
      modifyRaceModifiers(pyromancer, 0.3f);
    }
  }

  @Override
  public void visit(Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      wizard.modifyHP(20);
      modifyRaceModifiers(wizard, 0.4f);
    }
  }
}

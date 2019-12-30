package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

import java.util.ArrayList;

import static main.helpers.Constants.BORDER_VALUE;
import static main.helpers.Constants.FIFTY;

public class LevelUpAngel extends Angel {

  public LevelUpAngel() {
    setType(AngelType.Good);
  }

  private void modifyDamageModifiers(Hero hero, float f) {
    ArrayList<Float> modifiers = new ArrayList<Float>();
    for (int i = 0; i < hero.getRaceModifiers().size(); i++) {
      modifiers.add(hero.getRaceModifiers().get(i) + f);
    }
    hero.setRaceModifiers(modifiers);
  }

  private void levelUp(Hero hero) {
    int currLvl = hero.getLevel();
    int xplevelup;
    xplevelup = BORDER_VALUE + (currLvl) * FIFTY;
    hero.setXp(xplevelup);
  }
  @Override
  public void visit(Hero hero) {
    System.out.println("da frt ");
    visit(hero);
  }

  @Override
  public void visit(Knight knight) {
    if (knight.getState().equals("alive")) {
      levelUp(knight);
      modifyDamageModifiers(knight, 0.1f);
    }
  }

  @Override
  public void visit(Rogue rogue) {
    if (rogue.getState().equals("alive")) {
      levelUp(rogue);
      modifyDamageModifiers(rogue, 0.15f);
    }
  }

  @Override
  public void visit(Pyromancer pyromancer) {
    if (pyromancer.getState().equals("alive")) {
      levelUp(pyromancer);
      modifyDamageModifiers(pyromancer, 0.2f);
    }
  }

  @Override
  public void visit(Wizard wizard) {
    if (wizard.getState().equals("alive")) {
      levelUp(wizard);
      modifyDamageModifiers(wizard, 0.25f);
    }
  }
}

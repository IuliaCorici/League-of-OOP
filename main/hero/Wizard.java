package main.hero;
import main.map.MapoFGame;

import static main.helpers.Constants.HP_K_MAX;
import static main.helpers.Constants.HP_P_MAX;
import static main.helpers.Constants.HP_R_MAX;
import static main.helpers.Constants.HP_W_LEVEL;
import static main.helpers.Constants.HP_W_MAX;
import static main.helpers.Constants.PROCENT1;
import static main.helpers.Constants.PROCENT2;
import static main.helpers.Constants.PROCENT_LEVEL1;
import static main.helpers.Constants.PROCENT_LEVEL2;
import static main.helpers.Constants.wizardToKnightDeflectModifier;
import static main.helpers.Constants.wizardToKnightDrainModifier;
import static main.helpers.Constants.wizardToPyromancerDeflectModifier;
import static main.helpers.Constants.wizardToPyromancerDrainModifier;
import static main.helpers.Constants.wizardToRogueDeflectModifier;
import static main.helpers.Constants.wizardToRogueDrainModifier;
import static main.helpers.Constants.wizardToWizardDeflectModifier;
import static main.helpers.Constants.wizardToWizardDrainModifier;

public final class Wizard extends Hero {
  Wizard() {
    super();
    super.setCURR_HP(HP_W_MAX);
    super.setBonusLevel(HP_W_LEVEL);
    super.setHP_MAXIMUM(HP_W_MAX);
  }


  private int DMGwithModifiers(float DMG, float terrModifier, float raceModifier) {
    System.out.println((DMG + " "+ terrModifier + " " + raceModifier));
    return Math.round(DMG * terrModifier * raceModifier);
  }

  private int DMGwithoutModifiers(float DMG, float procent, float terrModifiers) {
    return Math.round(DMG * procent * terrModifiers);
  }

  private float calculateProcent1() {
    float percentage = PROCENT1 + getLevel()* PROCENT_LEVEL1;
    return percentage;
  }
  private float calculateProcent2() {
    float percentage = PROCENT2 + getLevel()* PROCENT_LEVEL2;
    return percentage;
  }


  @Override
  public void isAttackedBy(Hero hero, MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }


  @Override
  public void setUpDoT(Hero hero) {

  }

  @Override
  public void ability1(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + wizardToRogueDrainModifier);
    int baseHP = (int)Math.min(0.3 * HP_R_MAX, rogue.getCURR_HP());
    super.setDMGwithoutModifier1(DMGwithoutModifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDMGwithModifier1(DMGwithModifiers(baseHP, terrModifier, raceModifier));
  }

  @Override
  public void ability2(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getWizardModifier();
    float raceModifier = wizardToRogueDeflectModifier;
    System.out.println("ce tebuie " + rogue.getDMGwithoutModifier1() + " " +
        rogue.getDMGwithoutModifier2());
    super.setDMGwithoutModifier2(Math.round(calculateProcent2()*(rogue.getDMGwithoutModifier1()+
        rogue.getDMGwithoutModifier2()) * terrModifier));
    super.setDMGwithModifier2(Math.round(calculateProcent2() *(rogue.getDMGwithoutModifier1()+
        rogue.getDMGwithoutModifier2()) * terrModifier * raceModifier));
    System.out.println("k2:" + getDMGwithModifier2());
  }

  @Override
  public void ability1(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + wizardToWizardDrainModifier);
    int baseHP = (int)Math.min(0.3 * HP_W_MAX, wizard.getCURR_HP());
    super.setDMGwithoutModifier1(DMGwithoutModifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDMGwithModifier1(DMGwithModifiers(baseHP, terrModifier, raceModifier));
  }

  @Override
  public void ability2(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getWizardModifier();
    float raceModifier = wizardToWizardDeflectModifier;
    super.setDMGwithoutModifier2(Math.round(calculateProcent2()*(wizard.getDMGwithoutModifier1()+
        wizard.getDMGwithoutModifier2()) * terrModifier));
    System.out.println(getDMGwithoutModifier2());
    super.setDMGwithModifier2(Math.round(calculateProcent2() *(wizard.getDMGwithoutModifier1()+
        wizard.getDMGwithoutModifier2()) * terrModifier * raceModifier));
    System.out.println(getDMGwithModifier2());

  }

  @Override
  public void ability1(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + wizardToPyromancerDrainModifier);
    int baseHP = (int)Math.min(0.3 * HP_P_MAX, pyromancer.getCURR_HP());
    super.setDMGwithoutModifier1(DMGwithoutModifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDMGwithModifier1(DMGwithModifiers(baseHP, terrModifier, raceModifier));
  }

  @Override
  public void ability2(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getWizardModifier();
    float raceModifier = wizardToPyromancerDeflectModifier;
    System.out.println("nla" + pyromancer.getDMGwithoutModifier2());
    super.setDMGwithoutModifier2(Math.round(calculateProcent2()*(pyromancer.getDMGwithoutModifier1()+
        pyromancer.getDMGwithoutModifier2()) * terrModifier));
    super.setDMGwithModifier2(Math.round(calculateProcent2() *(pyromancer.getDMGwithoutModifier1()+
        pyromancer.getDMGwithoutModifier2()) * terrModifier * raceModifier));
    System.out.println("mm" + getDMGwithModifier2());
  }

  @Override
  public void ability1(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getWizardModifier();
    float raceModifier = (1 + wizardToKnightDrainModifier);
    System.out.println(raceModifier);
    float baseHP = Math.min(0.3f * HP_K_MAX, knight.getCURR_HP());
    float newbaseHP = calculateProcent1() * baseHP;
    super.setDMGwithoutModifier1(DMGwithoutModifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDMGwithModifier1(DMGwithModifiers(newbaseHP, terrModifier, raceModifier));
    System.out.println("k1:" + getDMGwithModifier1());
  }

  @Override
  public void ability2(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getWizardModifier();
    float raceModifier = wizardToKnightDeflectModifier;
    System.out.println("ce tebuie " + knight.getDMGwithoutModifier1() + " " +
        knight.getDMGwithoutModifier2());
    super.setDMGwithoutModifier2(Math.round(calculateProcent2()*(knight.getDMGwithoutModifier1()+
        knight.getDMGwithoutModifier2()) * terrModifier));
    super.setDMGwithModifier2(Math.round(calculateProcent2() *(knight.getDMGwithoutModifier1()+
        knight.getDMGwithoutModifier2()) * terrModifier * raceModifier));
    System.out.println("k2:" + getDMGwithModifier2());
  }
}

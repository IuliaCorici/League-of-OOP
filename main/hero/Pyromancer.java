package main.hero;

import main.map.MapoFGame;
import static main.helpers.Constants.*;

public final class Pyromancer extends Hero {
  Pyromancer() {
    super();
    super.setCURR_HP(HP_P_MAX);
    super.setBonusLevel(HP_P_LEVEL);
    super.setHP_MAXIMUM(HP_P_MAX);
  }

  private int DMGwithModifiers(float terrModifier, float raceModifier) {
    int DMG = BASE_DAMAGE_IGNITE + getLevel() * LEVEL_DAMAGE_IGNITE;
    return Math.round(DMG * terrModifier*raceModifier);
  }

  private int DMGwithoutModifiers(float terrModifiers) {
    int DMG = BASE_DAMAGE_IGNITE + getLevel() * LEVEL_DAMAGE_IGNITE;
    return Math.round(DMG * terrModifiers);
  }

  @Override
  public void setUpDoT(Hero hero) {
    hero.getDot().setNumRounds(2);
    hero.getDot().setPerRoundDMG(50 + hero.getLevel()*30);
  }

  @Override
  public void isAttackedBy(Hero hero, MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }


  @Override
  public void ability1(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = pyromancerToRogueFireblastModifier;
    int DMG = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability2(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = pyromancerToRogueIgniteModifier;
    super.setDMGwithoutModifier2(DMGwithoutModifiers(raceModifier));
    super.setDMGwithModifier2(DMGwithModifiers(terrModifier, raceModifier));
    rogue.getDot().setNumRounds(2);
    rogue.getDot().setPerRoundDMG(Math.round((50 + rogue.getLevel()*30) * raceModifier));
  }

  @Override
  public void ability1(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getPyromancerModifier();
    System.out.println(terrModifier);
    float raceModifier = pyromancerToWizardFireblastModifier;
    int DMG = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
    System.out.println("p: " + getDMGwithoutModifier1());
  }

  @Override
  public void ability2(Wizard wizard, MapoFGame map) {
      float terrModifier = terrain(wizard, map).getPyromancerModifier();
      float raceModifier = pyromancerToWizardIgniteModifier;
      super.setDMGwithoutModifier2(DMGwithoutModifiers(terrModifier));
    System.out.println("p" + getDMGwithoutModifier2());
      super.setDMGwithModifier2(DMGwithModifiers(terrModifier, raceModifier));
    wizard.getDot().setNumRounds(2);
    wizard.getDot().setPerRoundDMG(Math.round((50 + wizard.getLevel()*30) * raceModifier));
//      setUpDoT(wizard);
  }

  @Override
  public void ability1(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = pyromancerToPyromancerFireblastModifier;
    int DMG = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability2(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = pyromancerToPyromancerIgniteModifier;
    super.setDMGwithoutModifier2(DMGwithoutModifiers(raceModifier));
    super.setDMGwithModifier2(DMGwithModifiers(terrModifier, raceModifier));
//    setUpDoT(pyromancer);
    pyromancer.getDot().setNumRounds(2);
    pyromancer.getDot().setPerRoundDMG(Math.round((50 + pyromancer.getLevel()*30) * raceModifier));
  }

  @Override
  public void ability1(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = pyromancerToKnightFireblastModifier;
    int DMG = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability2(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = pyromancerToKnightIgniteModifier;
    super.setDMGwithoutModifier2(DMGwithoutModifiers(raceModifier));
    super.setDMGwithModifier2(DMGwithModifiers(terrModifier, raceModifier));
    //setUpDoT(knight);
    knight.getDot().setNumRounds(2);
    knight.getDot().setPerRoundDMG(Math.round((50 + knight.getLevel()*30) * raceModifier));
  }
}

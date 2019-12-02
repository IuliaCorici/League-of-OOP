package main.hero;

import main.map.MapoFGame;
import main.map.Terrain;

import static main.helpers.Constants.*;

public final class Rogue extends Hero {
  private int numberOfHits;

  Rogue() {
    super();
    super.setHP_MAXIMUM(HP_R_MAX);
    super.setCURR_HP(HP_R_MAX );
    super.setBonusLevel(HP_R_LEVEL);
    numberOfHits = 0;
  }

  @Override
  public void setUpDoT(Hero hero) {
    hero.getDot().setNumRoundsParalysis(3);
    hero.getDot().setNumRounds(3);
    hero.getDot().setPerRoundDMG(Math.round((40 + hero.getLevel() * 10) * rogueToRogueParalysisModifier));
  }

  @Override
  public void isAttackedBy(Hero hero, MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }

  @Override
  public void ability1(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getRogueModifier();
    float raceModifier = rogueToRogueBackstabModifier;
    numberOfHits += 1;
    float DMG = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % 3 == 0 &&  terrain(rogue, map).getName() == 'W') {
      DMG = 1.5f * DMG;
    } else {
      numberOfHits = 0;
    }
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability2(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getRogueModifier();
    float raceModifier = rogueToRogueParalysisModifier;
    int DMG = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    if (terrain(rogue, map).getName() == 'W') {
      rogue.getDot().setNumRoundsParalysis(3);
      rogue.getDot().setNumRounds(3);
    } else {
      rogue.getDot().setNumRoundsParalysis(6);
      rogue.getDot().setNumRounds(6);
    }
    rogue.getDot().setPerRoundDMG(Math.round(raceModifier * DMG));
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));

  }

  @Override
  public void ability1(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getRogueModifier();
    float raceModifier = rogueToWizardBackstabModifier;
    numberOfHits += 1;
    float DMG = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % 3 == 0 &&  terrain(wizard, map).getName() == 'W') {
      DMG = 1.5f * DMG;
    } else {
      numberOfHits = 0;
    }
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability2(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getRogueModifier();
    float raceModifier = rogueToWizardParalysisModifier;
    int DMG = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    if (terrain(wizard, map).getName() == 'W') {
      wizard.getDot().setNumRoundsParalysis(3);
      wizard.getDot().setNumRounds(3);
    } else {
      wizard.getDot().setNumRoundsParalysis(6);
      wizard.getDot().setNumRounds(6);
    }
    wizard.getDot().setPerRoundDMG(Math.round(raceModifier * DMG));
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability1(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getRogueModifier();
    float raceModifier = rogueToPyromancerBackstabModifier;
    numberOfHits += 1;
    float DMG = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % 3 == 0 &&  terrain(pyromancer, map).getName() == 'W') {
      DMG = 1.5f * DMG;
    } else {
      numberOfHits = 0;
    }
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG ));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }


  @Override
  public void ability2(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getRogueModifier();
    System.out.println(terrModifier);
    float raceModifier = rogueToPyromancerParalysisModifier;
    int DMG = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    Terrain terrain = map.getPieceOfMap(this.getLocation().getRow(), this.getLocation().getCol());
    Character terrName = terrain.getName();
    if (terrName == 'W') {
      //System.out.println(" daa");
      pyromancer.getDot().setNumRoundsParalysis(6);
      pyromancer.getDot().setNumRounds(6);
    } else {
      pyromancer.getDot().setNumRoundsParalysis(3);
      pyromancer.getDot().setNumRounds(3);
    }
    pyromancer.getDot().setPerRoundDMG(Math.round(raceModifier * DMG * terrModifier));
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
  }

  @Override
  public void ability1(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getRogueModifier();
    float raceModifier = rogueToKnightBackstabModifier;
    numberOfHits += 1;
    float DMG = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits -1) % 3 == 0 && terrain(knight, map).getName() == 'W') {
      DMG = 1.5f * DMG;
    } else {
      numberOfHits = 0;
    }
    super.setDMGwithoutModifier1(Math.round(terrModifier * DMG));
    super.setDMGwithModifier1(Math.round(raceModifier * terrModifier * DMG));
  }


  @Override
  public void ability2(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getRogueModifier();
    float raceModifier = rogueToKnightParalysisModifier;
    int DMG = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    if (terrain(knight, map).getName() == 'W') {
      knight.getDot().setNumRoundsParalysis(6);
      knight.getDot().setNumRounds(6);
    } else {
      knight.getDot().setNumRoundsParalysis(3);
      knight.getDot().setNumRounds(3);
    }
    knight.getDot().setPerRoundDMG(Math.round(raceModifier * DMG * terrModifier));
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
  }
}

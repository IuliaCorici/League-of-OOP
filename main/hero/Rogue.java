package main.hero;

import main.angel.AngelVisitor;
import main.strategies.RogueStrategy;

import java.util.ArrayList;

import static main.helpers.Constants.BASE_DAMAGE_BACKSTAB;
import static main.helpers.Constants.BASIC_ROUNDS_ROGUE;
import static main.helpers.Constants.COEFICIENT;
import static main.helpers.Constants.DAMAGE_PARALYSIS;
import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_R_LEVEL;
import static main.helpers.Constants.HP_R_MAX;
import static main.helpers.Constants.LEVEL_DAMAGE_BACKSTAB;
import static main.helpers.Constants.LEVEL_DAMAGE_PARALYSIS;
import static main.helpers.Constants.ROGUE_TO_KNIGHT_BACKSTAB_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_KNIGHT_PARALYSIS_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_PYROMANCER_BACKSTAB_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_PYROMANCER_PARALYSIS_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_ROGUE_BACKSTAB_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_ROGUE_PARALYSIS_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_WIZARD_BACKSTAB_MODIFIER;
import static main.helpers.Constants.ROGUE_TO_WIZARD_PARALYSIS_MODIFIER;
import static main.helpers.Constants.SEVEN;
import static main.helpers.Constants.SIX;
import static main.helpers.Constants.THREE;
import static main.helpers.Constants.WOODS_ROUNDS_ROGUE;

public final class Rogue extends Hero {
  private int numberOfHits;

  Rogue() {
    super();
    super.setHpMaximum(HP_R_MAX);
    super.setCurrHp(HP_R_MAX);
    super.setBonusLevel(HP_R_LEVEL);
    initRaceModifiers();
    numberOfHits = 0;
    setSurname("Rogue");
  }

  @Override
  public void chooseStrategy() {
    setStrategy(new RogueStrategy(this));
    getStrategy().prepareForBattle();
  }

  @Override
  public void accept(final AngelVisitor v) {
    v.visit(this);
  }

  @Override
  public void setUpDoT(final Hero hero) {
  }


  private void initRaceModifiers() {
    ArrayList<Float> raceModifiers = new ArrayList<Float>();
    raceModifiers.add(ROGUE_TO_ROGUE_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_ROGUE_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_WIZARD_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_WIZARD_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_PYROMANCER_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_PYROMANCER_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_KNIGHT_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_KNIGHT_PARALYSIS_MODIFIER);
    setRaceModifiers(raceModifiers);
  }

  private void setRounds(final Hero hero) {
    if (terrain(hero).getName() != 'W') {
      hero.getDot().setNumRoundsParalysis(BASIC_ROUNDS_ROGUE);
      hero.getDot().setNumRounds(BASIC_ROUNDS_ROGUE);
      hero.getDot().setCurrRound(0);
    } else {
      hero.getDot().setNumRoundsParalysis(WOODS_ROUNDS_ROGUE);
      hero.getDot().setNumRounds(WOODS_ROUNDS_ROGUE);
      hero.getDot().setCurrRound(0);
    }
  }
  @Override
  public void isAttackedBy(final Hero hero) {
    hero.ability1(this);
    hero.ability2(this);
  }

  /**
   * Implements BACKSTAB against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability1(final Rogue rogue) {
    float terrModifier = terrain(rogue).getRogueModifier();
    float raceModifier = getRaceModifiers().get(0);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(rogue).getName() == 'W') {
        dmg = COEFICIENT * dmg;
      } else {
        numberOfHits = 0;
      }
    }
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements PARALYSIS against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability2(final Rogue rogue) {
    float terrModifier = terrain(rogue).getRogueModifier();
    float raceModifier = getRaceModifiers().get(1);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(rogue);
    rogue.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));

  }

  /**
   * Implements BACKSTAB against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability1(final Wizard wizard) {
    float terrModifier = terrain(wizard).getRogueModifier();
    float raceModifier = getRaceModifiers().get(2);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(wizard).getName() == 'W') {
        dmg = COEFICIENT * dmg;
      } else {
        numberOfHits = 0;
      }
    }
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements PARALYSIS against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability2(final Wizard wizard) {
    float terrModifier = terrain(wizard).getRogueModifier();
    float raceModifier = getRaceModifiers().get(THREE);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(wizard);
    wizard.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements BACKSTAB against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability1(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getRogueModifier();
    float raceModifier = getRaceModifiers().get(FOUR);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(pyromancer).getName() == 'W') {
        dmg = COEFICIENT * dmg;
      } else {
        numberOfHits = 0;
      }
    }
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements PARALYSIS against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability2(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getRogueModifier();
    float raceModifier = getRaceModifiers().get(FIVE);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(pyromancer);
    pyromancer.getDot().setPerRoundDMG(Math.round((raceModifier) * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements BACKSTAB against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability1(final Knight knight) {
    float terrModifier = terrain(knight).getRogueModifier();
    float raceModifier = getRaceModifiers().get(SIX);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(knight).getName() == 'W') {

        dmg = COEFICIENT * dmg;

      } else {
        numberOfHits = 0;
      }
    }
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * Math.round(terrModifier
        * Math.round(dmg))));
  }

  /**
   * Implements PARALYSIS against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability2(final Knight knight) {
    float terrModifier = terrain(knight).getRogueModifier();
    float raceModifier = getRaceModifiers().get(SEVEN);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(knight);
    knight.getDot().setPerRoundDMG(Math.round(raceModifier  * Math.round(dmg * terrModifier)));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * Math.round(terrModifier * dmg)));
  }
}

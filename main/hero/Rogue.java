package main.hero;

import main.angel.AngelVisitor;
import main.map.MapoFGame;
import main.strategies.RogueStrategy;

import static main.helpers.Constants.BASE_DAMAGE_BACKSTAB;
import static main.helpers.Constants.BASIC_ROUNDS_ROGUE;
import static main.helpers.Constants.COEFICIENT;
import static main.helpers.Constants.DAMAGE_PARALYSIS;
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
  }

  @Override
  public void chooseStrategy() {
    strategy = new RogueStrategy(this);
  }

  @Override
  public void accept(AngelVisitor v) {
    v.visit(this);
  }

  @Override
  public void setUpDoT(final Hero hero) {
  }


  private void initRaceModifiers() {
    raceModifiers.add(ROGUE_TO_ROGUE_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_ROGUE_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_WIZARD_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_WIZARD_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_PYROMANCER_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_PYROMANCER_PARALYSIS_MODIFIER);
    raceModifiers.add(ROGUE_TO_KNIGHT_BACKSTAB_MODIFIER);
    raceModifiers.add(ROGUE_TO_KNIGHT_PARALYSIS_MODIFIER);

  }
  private void setRounds(final Hero hero, final MapoFGame map) {
    if (terrain(hero, map).getName() != 'W') {
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
  public void isAttackedBy(final Hero hero, final MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }

  /**
   * Implements BACKSTAB against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getRogueModifier();
    float raceModifier = raceModifiers.get(0);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(rogue, map).getName() == 'W') {
        System.out.println("numar de lovituri " + numberOfHits);
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
   * @param map
   */
  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getRogueModifier();
    float raceModifier = raceModifiers.get(1);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(rogue, map);
    rogue.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));

  }

  /**
   * Implements BACKSTAB against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getRogueModifier();
    float raceModifier = raceModifiers.get(2);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if ( terrain(wizard, map).getName() == 'W') {
        System.out.println("numar de lovituri " + numberOfHits);
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
   * @param map
   */
  @Override
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getRogueModifier();
    float raceModifier = raceModifiers.get(3);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(wizard, map);
    wizard.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements BACKSTAB against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getRogueModifier();
    float raceModifier = raceModifiers.get(4);
    numberOfHits += 1;
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(pyromancer, map).getName() == 'W') {
        System.out.println("numar de lovituri " + numberOfHits);

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
   * @param map
   */
  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getRogueModifier();
    System.out.println(terrModifier);
    float raceModifier = raceModifiers.get(5);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(pyromancer, map);
    pyromancer.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements BACKSTAB against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getRogueModifier();
    float raceModifier = raceModifiers.get(6);
    numberOfHits += 1;
    System.out.println("numar de lovituri " + numberOfHits);
    float dmg = BASE_DAMAGE_BACKSTAB + LEVEL_DAMAGE_BACKSTAB * getLevel();
    if ((numberOfHits - 1) % THREE == 0 && numberOfHits > 0) {
      if (terrain(knight, map).getName() == 'W') {

        dmg = COEFICIENT * dmg;

      } else {
        numberOfHits = 0;
      }
    }
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements PARALYSIS against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getRogueModifier();
    float raceModifier = raceModifiers.get(7);
    int dmg = DAMAGE_PARALYSIS + LEVEL_DAMAGE_PARALYSIS * getLevel();
    setRounds(knight, map);
    knight.getDot().setPerRoundDMG(Math.round(raceModifier * dmg * terrModifier));
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
  }
}

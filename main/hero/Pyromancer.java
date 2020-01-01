package main.hero;

import main.angel.AngelVisitor;
import main.strategies.PyromancerStrategy;

import java.util.ArrayList;

import static main.helpers.Constants.BASE_DAMAGE_FIREBLAST;
import static main.helpers.Constants.BASE_DAMAGE_IGNITE;
import static main.helpers.Constants.DOT_IGNITE_DAMAGE;
import static main.helpers.Constants.DOT_IGNITE_LEVEL;
import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_P_LEVEL;
import static main.helpers.Constants.HP_P_MAX;
import static main.helpers.Constants.LEVEL_DAMAGE_FIREBLAST;
import static main.helpers.Constants.LEVEL_DAMAGE_IGNITE;
import static main.helpers.Constants.PYROMANCER_TO_KNIGHT_FIREBLAST_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_KNIGHT_IGNITE_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_PYROMANCER_FIREBLAST_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_PYROMANCER_IGNITE_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_ROGUE_FIREBLAST_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_ROGUE_IGNITE_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_WIZARD_FIREBLAST_MODIFIER;
import static main.helpers.Constants.PYROMANCER_TO_WIZARD_IGNITE_MODIFIER;
import static main.helpers.Constants.SEVEN;
import static main.helpers.Constants.SIX;
import static main.helpers.Constants.THREE;

public final class Pyromancer extends Hero {
  Pyromancer() {
    super();
    super.setCurrHp(HP_P_MAX);
    super.setBonusLevel(HP_P_LEVEL);
    super.setHpMaximum(HP_P_MAX);
    setSurname("Pyromancer");
    initRaceModifiers();
  }

  @Override
  public void chooseStrategy() {
    setStrategy(new PyromancerStrategy(this));
    getStrategy().prepareForBattle();
  }

  private void initRaceModifiers() {
    ArrayList<Float> raceModifiers = new ArrayList<Float>();
    raceModifiers.add(PYROMANCER_TO_ROGUE_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_ROGUE_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_WIZARD_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_WIZARD_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_PYROMANCER_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_PYROMANCER_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_KNIGHT_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_KNIGHT_IGNITE_MODIFIER);
    setRaceModifiers(raceModifiers);
  }

  @Override
  public void accept(final AngelVisitor v) {
    v.visit(this);
  }
  /**
   *  Calculates the damage with race modifier the Pyromancer is giving to its opponent.
   * @param terrModifier
   * @param raceModifier
   * @return
   */
  private int dmgwithmodifiers(final float terrModifier, final float raceModifier) {
    int dmg = BASE_DAMAGE_IGNITE + getLevel() * LEVEL_DAMAGE_IGNITE;
    return Math.round(Math.round(dmg * terrModifier) * raceModifier);
  }
  /**
   * Calculates the damage witout race modifier the Pyromancer is giving to its opponent.
   * @param terrModifiers
   * @return
   */
  private int dmgwithoutmodifiers(final float terrModifiers) {
    int dmg = BASE_DAMAGE_IGNITE + getLevel() * LEVEL_DAMAGE_IGNITE;
    return Math.round(dmg * terrModifiers);
  }

  @Override
  public void setUpDoT(final Hero hero) {
    hero.getDot().setNumRounds(2);
    hero.getDot().setPerRoundDMG(DOT_IGNITE_DAMAGE + hero.getLevel() * DOT_IGNITE_LEVEL);
  }

  @Override
  public void isAttackedBy(final Hero hero) {
    hero.ability1(this);
    hero.ability2(this);
  }

  /**
   * Implements FIREBLAST against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability1(final Rogue rogue) {
    float terrModifier = terrain(rogue).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(0);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * Math.round(terrModifier * dmg)));
  }

  /**
   * Implements IGNITE against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability2(final Rogue rogue) {
    float terrModifier = terrain(rogue).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(1);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    rogue.getDot().setNumRounds(2);
    rogue.getDot().setPerRoundDMG(Math.round(Math.round((DOT_IGNITE_DAMAGE + super.getLevel()
        * DOT_IGNITE_LEVEL) * terrModifier) * raceModifier));
    rogue.getDot().setNumRoundsParalysis(0);
    rogue.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability1(final Wizard wizard) {
    float terrModifier = terrain(wizard).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(2);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * Math.round(terrModifier * dmg)));
  }

  /**
   * Implements IGNITE against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability2(final Wizard wizard) {
    float terrModifier = terrain(wizard).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(THREE);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(terrModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    wizard.getDot().setNumRounds(2);
    wizard.getDot().setPerRoundDMG(Math.round(Math.round((DOT_IGNITE_DAMAGE + super.getLevel()
        * DOT_IGNITE_LEVEL) * terrModifier) * raceModifier));
    wizard.getDot().setNumRoundsParalysis(0);
    wizard.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability1(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(FOUR);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * Math.round(terrModifier * dmg)));
  }
  /**
   * Implements IGNITE against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability2(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(FIVE);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    pyromancer.getDot().setNumRounds(2);
    pyromancer.getDot().setPerRoundDMG(Math.round(Math.round((DOT_IGNITE_DAMAGE + super.getLevel()
        * DOT_IGNITE_LEVEL) * terrModifier) * raceModifier));
    pyromancer.getDot().setNumRoundsParalysis(0);
    pyromancer.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability1(final Knight knight) {
    float terrModifier = terrain(knight).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(SIX);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * Math.round(terrModifier * dmg)));
  }

  /**
   *  Implements IGNITE against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability2(final Knight knight) {
    float terrModifier = terrain(knight).getPyromancerModifier();
    float raceModifier = getRaceModifiers().get(SEVEN);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    knight.getDot().setNumRounds(2);
    knight.getDot().setPerRoundDMG(Math.round(Math.round((DOT_IGNITE_DAMAGE + super.getLevel()
        * DOT_IGNITE_LEVEL) * terrModifier) * raceModifier));
    knight.getDot().setNumRoundsParalysis(0);
    knight.getDot().setCurrRound(0);
  }
}

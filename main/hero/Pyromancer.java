package main.hero;

import main.angel.AngelVisitor;
import main.map.MapoFGame;
import main.strategies.PyromancerStrategy;

import static main.helpers.Constants.BASE_DAMAGE_FIREBLAST;
import static main.helpers.Constants.BASE_DAMAGE_IGNITE;
import static main.helpers.Constants.DOT_IGNITE_DAMAGE;
import static main.helpers.Constants.DOT_IGNITE_LEVEL;
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

public final class Pyromancer extends Hero {
  Pyromancer() {
    super();
    super.setCurrHp(HP_P_MAX);
    super.setBonusLevel(HP_P_LEVEL);
    super.setHpMaximum(HP_P_MAX);
    initRaceModifiers();
  }

  @Override
  public void chooseStrategy() {
    strategy = new PyromancerStrategy(this);
  }

  private void initRaceModifiers() {
    raceModifiers.add(PYROMANCER_TO_ROGUE_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_ROGUE_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_WIZARD_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_WIZARD_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_PYROMANCER_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_PYROMANCER_IGNITE_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_KNIGHT_FIREBLAST_MODIFIER);
    raceModifiers.add(PYROMANCER_TO_KNIGHT_IGNITE_MODIFIER);
  }

  @Override
  public void accept(AngelVisitor v) {
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
    return Math.round(dmg * terrModifier * raceModifier);
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
  public void isAttackedBy(final Hero hero, final MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }

  /**
   * Implements FIREBLAST against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(0);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements IGNITE against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(1);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    rogue.getDot().setNumRounds(2);
    rogue.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + rogue.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier * terrModifier));
    rogue.getDot().setNumRoundsParalysis(0);
    rogue.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(2);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   * Implements IGNITE against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(3);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(terrModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    wizard.getDot().setNumRounds(2);
    wizard.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + wizard.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier * terrModifier));
    wizard.getDot().setNumRoundsParalysis(0);
    wizard.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(4);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }
  /**
   * Implements IGNITE against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(5);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    pyromancer.getDot().setNumRounds(2);
    pyromancer.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + pyromancer.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier * terrModifier));
    pyromancer.getDot().setNumRoundsParalysis(0);
    pyromancer.getDot().setCurrRound(0);
  }

  /**
   * Implements FIREBLAST against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(6);
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  /**
   *  Implements IGNITE against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = raceModifiers.get(7);
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    knight.getDot().setNumRounds(2);
    knight.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + knight.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier * terrModifier));
    knight.getDot().setNumRoundsParalysis(0);
    knight.getDot().setCurrRound(0);
  }
}

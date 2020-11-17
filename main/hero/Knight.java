package main.hero;
import main.angel.AngelVisitor;
import main.strategies.KnightStrategy;

import java.util.ArrayList;

import static main.helpers.Constants.BASE_DAMAGE_EXECUTE;
import static main.helpers.Constants.BASE_DAMAGE_SLAM;
import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_K_LEVEL;
import static main.helpers.Constants.HP_K_MAX;
import static main.helpers.Constants.KNIGHT_TO_KNIGHT_EXECUTE_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_KNIGHT_SLAM_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_PYROMANCER_EXECUTE_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_PYROMANCER_SLAM_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_ROGUE_EXECUTE_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_ROGUE_SLAM_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_WIZARD_EXECUTE_MODIFIER;
import static main.helpers.Constants.KNIGHT_TO_WIZARD_SLAM_MODIFIER;
import static main.helpers.Constants.LEVEL_DAMAGE_EXECUTE;
import static main.helpers.Constants.LEVEL_DAMAGE_SLAM;
import static main.helpers.Constants.SEVEN;
import static main.helpers.Constants.SIX;
import static main.helpers.Constants.THREE;


public final class Knight extends Hero {

  Knight() {
    super();
    super.setCurrHp(HP_K_MAX);
    super.setBonusLevel(HP_K_LEVEL);
    super.setHpMaximum(HP_K_MAX);
    setSurname("Knight");
    initRaceModifiers();
  }

  @Override
  public void chooseStrategy() {
    setStrategy(new KnightStrategy(this));
    getStrategy().prepareForBattle();
  }

  private void initRaceModifiers() {
    ArrayList<Float> raceModifiers = new ArrayList<Float>();
    raceModifiers.add(KNIGHT_TO_ROGUE_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_ROGUE_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_WIZARD_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_WIZARD_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_PYROMANCER_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_PYROMANCER_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_KNIGHT_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_KNIGHT_SLAM_MODIFIER);
    setRaceModifiers(raceModifiers);
  }



  @Override
  public void accept(final AngelVisitor v) {
      v.visit(this);
  }

  /**
   * Calculates the damage witout race modifier the knight is giving to its opponent.
   * @param terrModifier
   * @return
   */
  private int dmgwithoutmodifier(final float terrModifier) {
    int dmg = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    return Math.round(dmg * terrModifier);
  }

  /**
   *  Calculates the damage with race modifier the knight is giving to its opponent.
   * @param terrModifier
   * @param raceModifier
   * @return
   */
  private int dmgwithmodifier(final float terrModifier, final float raceModifier) {
    int dmg = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    return Math.round(Math.round(dmg * terrModifier) * raceModifier);
  }

  private int calculateHPlimit(final Hero hero) {
    return Math.round(Math.round(0.2f * (hero.xpLevelUp() - 1))
        + Math.round(0.01f * hero.getLevel()));
  }

  @Override
  public void setUpDoT(final Hero hero) {
    hero.getDot().setNumRounds(1);
    hero.getDot().setNumRoundsParalysis(1);
    hero.getDot().setCurrRound(0);
    hero.getDot().setPerRoundDMG(0);
  }

  @Override
  public void isAttackedBy(final Hero hero) {
    hero.ability1(this);
    hero.ability2(this);
  }

  /**
   * Implements EXECUTE against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability1(final Rogue rogue) {
    float terrModifier = terrain(rogue).getKnightModifier();
    float raceModifier = getRaceModifiers().get(0);
    int hplimit = calculateHPlimit(rogue);
    if (rogue.getCurrHp() < hplimit)  {
      rogue.setState("dead");
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability2(final Rogue rogue) {
    float terrModifier = terrain(rogue).getKnightModifier();
    float raceModifier = getRaceModifiers().get(1);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * Math.round(terrModifier * dmg)));
    setUpDoT(rogue);
  }

  /**
   * Implements EXECUTE against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability1(final Wizard wizard) {
    float terrModifier = terrain(wizard).getKnightModifier();
    float raceModifier = getRaceModifiers().get(2);
    int hplimit = calculateHPlimit(wizard);
    if (wizard.getCurrHp() < hplimit)  {
      wizard.setState("dead");
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(Math.round(dmgwithmodifier(terrModifier, raceModifier)));
    }
  }

  /**
   * Implements SLAM against WIZARD hero.
   * @param wizard
   */
  @Override
  public void ability2(final Wizard wizard) {
    float terrModifier = terrain(wizard).getKnightModifier();
    float raceModifier = getRaceModifiers().get(THREE);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * Math.round(terrModifier * dmg)));
    setUpDoT(wizard);
  }

  /**
   * Implemnts EXECUTE against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability1(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getKnightModifier();
    float raceModifier = getRaceModifiers().get(FOUR);
    int hplimit = calculateHPlimit(pyromancer);
    if (pyromancer.getCurrHp() < hplimit)  {
      pyromancer.setState("dead");
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability2(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getKnightModifier();
    float raceModifier = getRaceModifiers().get(FIVE);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * Math.round(terrModifier * dmg)));
    setUpDoT(pyromancer);
  }

  /**
   * Implements EXECUTE against Knight hero.
   * @param knight
   *
   */
  @Override
  public void ability1(final Knight knight) {
    float terrModifier = terrain(knight).getKnightModifier();
    float raceModifier = getRaceModifiers().get(SIX);
    int hplimit = calculateHPlimit(knight);
    if (knight.getCurrHp() < hplimit)  {
      knight.setState("dead");
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability2(final Knight knight) {
    float terrModifier = terrain(knight).getKnightModifier();
    float raceModifier = getRaceModifiers().get(SEVEN);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * Math.round(terrModifier * dmg)));
    setUpDoT(knight);
  }
}

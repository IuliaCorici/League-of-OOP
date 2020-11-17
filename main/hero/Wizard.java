package main.hero;
import main.angel.AngelVisitor;
import main.strategies.WizardStrategy;

import java.util.ArrayList;

import static main.helpers.Constants.FIVE;
import static main.helpers.Constants.FOUR;
import static main.helpers.Constants.HP_K_MAX;
import static main.helpers.Constants.HP_P_MAX;
import static main.helpers.Constants.HP_R_MAX;
import static main.helpers.Constants.HP_W_LEVEL;
import static main.helpers.Constants.HP_W_MAX;
import static main.helpers.Constants.PROCENT1;
import static main.helpers.Constants.PROCENT2;
import static main.helpers.Constants.PROCENT3;
import static main.helpers.Constants.PROCENT_LEVEL1;
import static main.helpers.Constants.PROCENT_LEVEL2;
import static main.helpers.Constants.SEVEN;
import static main.helpers.Constants.SIX;
import static main.helpers.Constants.THREE;
import static main.helpers.Constants.WIZARD_TO_KNIGHT_DEFLECT_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_KNIGHT_DRAIN_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_PYROMANCER_DEFLECT_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_PYROMANCER_DRAIN_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_ROGUE_DEFLECT_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_ROGUE_DRAIN_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_WIZARD_DEFLECT_MODIFIER;
import static main.helpers.Constants.WIZARD_TO_WIZARD_DRAIN_MODIFIER;

public final class Wizard extends Hero {
  Wizard() {
    super();
    super.setCurrHp(HP_W_MAX);
    super.setBonusLevel(HP_W_LEVEL);
    super.setHpMaximum(HP_W_MAX);
    setSurname("Wizard");
    initRaceModifiers();
  }

  @Override
  public void chooseStrategy() {
    setStrategy(new WizardStrategy(this));
    getStrategy().prepareForBattle();
  }

  private  void initRaceModifiers() {
    ArrayList<Float> raceModifiers = new ArrayList<Float>();
    raceModifiers.add(WIZARD_TO_ROGUE_DRAIN_MODIFIER);
    raceModifiers.add(WIZARD_TO_ROGUE_DEFLECT_MODIFIER);
    raceModifiers.add(WIZARD_TO_WIZARD_DRAIN_MODIFIER);
    raceModifiers.add(WIZARD_TO_WIZARD_DEFLECT_MODIFIER);
    raceModifiers.add(WIZARD_TO_PYROMANCER_DRAIN_MODIFIER);
    raceModifiers.add(WIZARD_TO_PYROMANCER_DEFLECT_MODIFIER);
    raceModifiers.add(WIZARD_TO_KNIGHT_DRAIN_MODIFIER);
    raceModifiers.add(WIZARD_TO_KNIGHT_DEFLECT_MODIFIER);
    setRaceModifiers(raceModifiers);
  }
  @Override
  public void accept(final AngelVisitor v) {
    v.visit(this);
  }

  /**
   *  Calculates the damage with race modifier the WIZARD is giving to its opponent.
   * @param dmg
   * @param terrModifier
   * @param raceModifier
   * @return
   */
  private int dmgwithmodifiers(final float dmg, final float terrModifier,
                               final float raceModifier) {
    return Math.round(dmg * terrModifier * raceModifier);
  }

  /**
   *  Calculates the damage without race modifier the WIZARD is giving to its opponent.
   * @param dmg
   * @param procent
   * @param terrModifiers
   * @return
   */
  private int dmgwithoutmodifiers(final float dmg, final float procent, final float terrModifiers) {
    return Math.round(dmg * procent * terrModifiers);
  }

  /**
   * Calculates the percentage of the damage.
   * @return
   */
  private float calculateProcent1() {
    return PROCENT1 + getLevel() * PROCENT_LEVEL1;
  }
  private float calculateProcent2() {
    return PROCENT2 + getLevel() * PROCENT_LEVEL2;
  }


  @Override
  public void isAttackedBy(final Hero hero) {
    hero.ability1(this);
    hero.ability2(this);
  }


  @Override
  public void setUpDoT(final Hero hero) {
  }

  /**
   * Implements DRAIN against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability1(final Rogue rogue) {
    float terrModifier = terrain(rogue).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + getRaceModifiers().get(0));
    int baseHP;
    if (rogue.getCurrHp() < HP_R_MAX) {
      baseHP = (int) Math.min(PROCENT3 * HP_R_MAX, rogue.getCurrHp());
    } else {
      baseHP = (int) Math.min(PROCENT3 * rogue.getCurrHp(), rogue.getCurrHp());
    }
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against ROGUE hero.
   * @param rogue
   *
   */
  @Override
  public void ability2(final Rogue rogue) {
    float terrModifier = terrain(rogue).getWizardModifier();
    float raceModifier = getRaceModifiers().get(1);
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2() * (rogue.getDmgwithoutmodifier1()
        + rogue.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2() * (rogue.getDmgwithoutmodifier1()
        + rogue.getDmgwithoutmodifier2()) * terrModifier * raceModifier));
  }

  /**
   * Implements DRAIN against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability1(final Wizard wizard) {
    float terrModifier = terrain(wizard).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + getRaceModifiers().get(2));
    int baseHP;
    if (wizard.getCurrHp() < HP_W_MAX) {
      baseHP = (int) Math.min(PROCENT3 * HP_W_MAX, wizard.getCurrHp());
    } else {
      baseHP = (int) Math.min(PROCENT3 * wizard.getCurrHp(), wizard.getCurrHp());
    }
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against WIZARD hero.
   * @param wizard
   *
   */
  @Override
  public void ability2(final Wizard wizard) {
    float terrModifier = terrain(wizard).getWizardModifier();
    float raceModifier = getRaceModifiers().get(THREE);
    super.setDmgwithoutmodifier2(0);
    super.setDmgwithmodifier2(0);
  }

  /**
   * Implements DRAIN against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability1(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + getRaceModifiers().get(FOUR));
    int baseHP;
    if (pyromancer.getCurrHp() < HP_P_MAX) {
      baseHP = (int) Math.min(PROCENT3 * HP_P_MAX, pyromancer.getCurrHp());
    } else {
      baseHP = (int) Math.min(PROCENT3 * pyromancer.getCurrHp(), pyromancer.getCurrHp());
    }
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   *  Implements DEFLECT against PYROMANCER hero.
   * @param pyromancer
   *
   */
  @Override
  public void ability2(final Pyromancer pyromancer) {
    float terrModifier = terrain(pyromancer).getWizardModifier();
    float raceModifier = getRaceModifiers().get(FIVE);
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2()
        * (pyromancer.getDmgwithoutmodifier1()
        + pyromancer.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2()
        * (pyromancer.getDmgwithoutmodifier1() + pyromancer.getDmgwithoutmodifier2())
        * terrModifier * raceModifier));
  }

  /**
   * Implements DRAIN against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability1(final Knight knight) {
    float terrModifier = terrain(knight).getWizardModifier();
    float raceModifier = (1 + getRaceModifiers().get(SIX));
    float baseHP = Math.min(PROCENT3 * HP_K_MAX, knight.getCurrHp());
    float newbaseHP = calculateProcent1() * baseHP;
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(newbaseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against KNIGHT hero.
   * @param knight
   *
   */
  @Override
  public void ability2(final Knight knight) {
    float terrModifier = terrain(knight).getWizardModifier();
    float raceModifier = getRaceModifiers().get(SEVEN);
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2() * (knight.getDmgwithoutmodifier1()
        + knight.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2() * (knight.getDmgwithoutmodifier1()
        + knight.getDmgwithoutmodifier2()) * terrModifier * raceModifier));
  }
}

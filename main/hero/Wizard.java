package main.hero;
import main.map.MapoFGame;

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
  public void isAttackedBy(final Hero hero, final MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }


  @Override
  public void setUpDoT(final Hero hero) {
  }

  /**
   * Implements DRAIN against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + WIZARD_TO_ROGUE_DRAIN_MODIFIER);
    int baseHP = (int) Math.min(PROCENT3 * HP_R_MAX, rogue.getCurrHp());
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getWizardModifier();
    float raceModifier = WIZARD_TO_ROGUE_DEFLECT_MODIFIER;
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2() * (rogue.getDmgwithoutmodifier1()
        + rogue.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2() * (rogue.getDmgwithoutmodifier1()
        + rogue.getDmgwithoutmodifier2()) * terrModifier * raceModifier));
  }

  /**
   * Implements DRAIN against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + WIZARD_TO_WIZARD_DRAIN_MODIFIER);
    int baseHP = (int) Math.min(PROCENT3 * HP_W_MAX, wizard.getCurrHp());
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getWizardModifier();
    float raceModifier = WIZARD_TO_WIZARD_DEFLECT_MODIFIER;
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2() * (wizard.getDmgwithoutmodifier1()
        + wizard.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2() * (wizard.getDmgwithoutmodifier1()
        + wizard.getDmgwithoutmodifier2()) * terrModifier * raceModifier));
  }

  /**
   * Implements DRAIN against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getWizardModifier();
    float raceModifier = calculateProcent1() * (1 + WIZARD_TO_PYROMANCER_DRAIN_MODIFIER);
    int baseHP = (int) Math.min(PROCENT3 * HP_P_MAX, pyromancer.getCurrHp());
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(baseHP, terrModifier, raceModifier));
  }

  /**
   *  Implements DEFLECT against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getWizardModifier();
    float raceModifier = WIZARD_TO_PYROMANCER_DEFLECT_MODIFIER;
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
   * @param map
   */
  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getWizardModifier();
    float raceModifier = (1 + WIZARD_TO_KNIGHT_DRAIN_MODIFIER);
    float baseHP = Math.min(PROCENT3 * HP_K_MAX, knight.getCurrHp());
    float newbaseHP = calculateProcent1() * baseHP;
    super.setDmgwithoutmodifier1(dmgwithoutmodifiers(baseHP, calculateProcent1(), terrModifier));
    super.setDmgwithmodifier1(dmgwithmodifiers(newbaseHP, terrModifier, raceModifier));
  }

  /**
   * Implements DEFLECT against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getWizardModifier();
    float raceModifier = WIZARD_TO_KNIGHT_DEFLECT_MODIFIER;
    super.setDmgwithoutmodifier2(Math.round(calculateProcent2() * (knight.getDmgwithoutmodifier1()
        + knight.getDmgwithoutmodifier2()) * terrModifier));
    super.setDmgwithmodifier2(Math.round(calculateProcent2() * (knight.getDmgwithoutmodifier1()
        + knight.getDmgwithoutmodifier2()) * terrModifier * raceModifier));
  }
}

package main.hero;
import main.map.MapoFGame;

import static main.helpers.Constants.BASE_DAMAGE_EXECUTE;
import static main.helpers.Constants.BASE_DAMAGE_SLAM;
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


public final class Knight extends Hero {
  Knight() {
    super();
    super.setCurrHp(HP_K_MAX);
    super.setBonusLevel(HP_K_LEVEL);
    super.setHpMaximum(HP_K_MAX);
  }

  private int dmgwithoutmodifier(final float terrModifier) {
    int dmg = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    return Math.round(dmg * terrModifier);
  }

  private int dmgwithmodifier(final float terrModifier, final float raceModifier) {
    int dmg = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    return Math.round(dmg * terrModifier * raceModifier);
  }

  private int calculateHPlimit(final Hero hero) {
    return Math.round(0.2f * (hero.xpLevelUp() - 1) + 0.01f * hero.getLevel());
  }

  @Override
  public void setUpDoT(final Hero hero) {
    hero.getDot().setNumRounds(1);
    hero.getDot().setNumRoundsParalysis(1);
  }

  @Override
  public void isAttackedBy(final Hero hero, final MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }

  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_ROGUE_EXECUTE_MODIFIER;
    int hplimit = calculateHPlimit(rogue);
    if (rogue.getCurrHp() < hplimit)  {
      rogue.setState("dead");
      super.setXpWinner(rogue.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_ROGUE_SLAM_MODIFIER;
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(rogue);
  }

  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_WIZARD_EXECUTE_MODIFIER;
    int hplimit = calculateHPlimit(wizard);
    if (wizard.getCurrHp() < hplimit)  {
      wizard.setState("dead");
      super.setXpWinner(wizard.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(Math.round(dmgwithmodifier(terrModifier, raceModifier)));
    }
  }

  @Override
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_WIZARD_SLAM_MODIFIER;
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(wizard);
  }

  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_PYROMANCER_EXECUTE_MODIFIER;
    int hplimit = calculateHPlimit(pyromancer);
    if (pyromancer.getCurrHp() < hplimit)  {
      pyromancer.setState("dead");
      super.setXpWinner(pyromancer.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }
  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_PYROMANCER_SLAM_MODIFIER;
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(pyromancer);
  }

  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_KNIGHT_EXECUTE_MODIFIER;
    int hplimit = calculateHPlimit(knight);
    if (knight.getCurrHp() < hplimit)  {
      knight.setState("dead");
      super.setXpWinner(knight.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = KNIGHT_TO_KNIGHT_SLAM_MODIFIER;
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(knight);
  }
}

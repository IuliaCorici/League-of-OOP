package main.hero;

import main.map.MapoFGame;

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
  }

  private int dmgwithmodifiers(final float terrModifier, final float raceModifier) {
    int dmg = BASE_DAMAGE_IGNITE + getLevel() * LEVEL_DAMAGE_IGNITE;
    return Math.round(dmg * terrModifier * raceModifier);
  }

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


  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_ROGUE_FIREBLAST_MODIFIER;
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_ROGUE_IGNITE_MODIFIER;
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    rogue.getDot().setNumRounds(2);
    rogue.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + rogue.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier));
  }

  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_WIZARD_FIREBLAST_MODIFIER;
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  @Override
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_WIZARD_IGNITE_MODIFIER;
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(terrModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    wizard.getDot().setNumRounds(2);
    wizard.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + wizard.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier));
  }

  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_PYROMANCER_FIREBLAST_MODIFIER;
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_PYROMANCER_IGNITE_MODIFIER;
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    pyromancer.getDot().setNumRounds(2);
    pyromancer.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + pyromancer.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier));
  }

  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_KNIGHT_FIREBLAST_MODIFIER;
    int dmg = BASE_DAMAGE_FIREBLAST + LEVEL_DAMAGE_FIREBLAST * super.getLevel();
    super.setDmgwithoutmodifier1(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier1(Math.round(raceModifier * terrModifier * dmg));
  }

  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getPyromancerModifier();
    float raceModifier = PYROMANCER_TO_KNIGHT_IGNITE_MODIFIER;
    super.setDmgwithoutmodifier2(dmgwithoutmodifiers(raceModifier));
    super.setDmgwithmodifier2(dmgwithmodifiers(terrModifier, raceModifier));
    knight.getDot().setNumRounds(2);
    knight.getDot().setPerRoundDMG(Math.round((DOT_IGNITE_DAMAGE + knight.getLevel()
        * DOT_IGNITE_LEVEL) * raceModifier));
  }
}

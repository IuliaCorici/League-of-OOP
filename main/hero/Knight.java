package main.hero;
import main.angel.AngelVisitor;
import main.map.MapoFGame;
import main.strategies.KnightStrategy;

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
    setSurname("Knight");
    initRaceModifiers();
  }

  @Override
  public void chooseStrategy() {
    strategy = new KnightStrategy(this);
    strategy.prepareForBattle();
  }

  private void initRaceModifiers() {
    raceModifiers.add(KNIGHT_TO_ROGUE_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_ROGUE_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_WIZARD_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_WIZARD_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_PYROMANCER_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_PYROMANCER_SLAM_MODIFIER);
    raceModifiers.add(KNIGHT_TO_KNIGHT_EXECUTE_MODIFIER);
    raceModifiers.add(KNIGHT_TO_KNIGHT_SLAM_MODIFIER);
  }



  @Override
  public void accept(AngelVisitor v) {
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
    return Math.round(dmg * terrModifier * raceModifier);
  }

  private int calculateHPlimit(final Hero hero) {
    return Math.round(0.2f * (hero.xpLevelUp() - 1) + 0.01f * hero.getLevel());
  }

  @Override
  public void setUpDoT(final Hero hero) {
    hero.getDot().setNumRounds(1);
    hero.getDot().setNumRoundsParalysis(1);
    hero.getDot().setCurrRound(0);
    hero.getDot().setPerRoundDMG(0);
  }

  @Override
  public void isAttackedBy(final Hero hero, final MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);
  }

  /**
   * Implements EXECUTE against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability1(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = raceModifiers.get(0);
    int hplimit = calculateHPlimit(rogue);
    if (rogue.getCurrHp() < hplimit)  {
      rogue.setState("dead");
      //super.setXpWinner(rogue.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against ROGUE hero.
   * @param rogue
   * @param map
   */
  @Override
  public void ability2(final Rogue rogue, final MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = raceModifiers.get(1);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(rogue);
  }

  /**
   * Implements EXECUTE against WIZARD hero.
   * @param wizard
   * @param map
   */
  @Override
  public void ability1(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = raceModifiers.get(2);
    int hplimit = calculateHPlimit(wizard);
    System.out.println(hplimit + " limita");
    if (wizard.getCurrHp() < hplimit)  {
      wizard.setState("dead");
      //super.setXpWinner(wizard.getLevel());
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
  public void ability2(final Wizard wizard, final MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = raceModifiers.get(3);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(wizard);
  }

  /**
   * Implemnts EXECUTE against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability1(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = raceModifiers.get(4);
    System.out.println(raceModifier);

    int hplimit = calculateHPlimit(pyromancer);
    if (pyromancer.getCurrHp() < hplimit)  {
      pyromancer.setState("dead");
      //super.setXpWinner(pyromancer.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against PYROMANCER hero.
   * @param pyromancer
   * @param map
   */
  @Override
  public void ability2(final Pyromancer pyromancer, final MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = raceModifiers.get(5);
    System.out.println(raceModifier);

    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));
    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(pyromancer);
  }

  /**
   * Implements EXECUTE against Knight hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability1(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = raceModifiers.get(6);
    System.out.println(raceModifier);
    int hplimit = calculateHPlimit(knight);
    if (knight.getCurrHp() < hplimit)  {
      knight.setState("dead");
     // super.setXpWinner(knight.getLevel());
    } else {
      super.setDmgwithoutmodifier1(dmgwithoutmodifier(terrModifier));
      super.setDmgwithmodifier1(dmgwithmodifier(terrModifier, raceModifier));
    }
  }

  /**
   * Implements SLAM against KNIGHT hero.
   * @param knight
   * @param map
   */
  @Override
  public void ability2(final Knight knight, final MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = raceModifiers.get(7);
    System.out.println(" ab2" + raceModifier);
    int dmg = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDmgwithoutmodifier2(Math.round(terrModifier * dmg));

    super.setDmgwithmodifier2(Math.round(raceModifier * terrModifier * dmg));
    setUpDoT(knight);
  }
}

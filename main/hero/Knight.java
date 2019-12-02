package main.hero;
import main.map.MapoFGame;

import static main.helpers.Constants.*;



public final class Knight extends Hero {
  Knight() {
    super();
    super.setCURR_HP(HP_K_MAX);
    super.setBonusLevel(HP_K_LEVEL);
    super.setHP_MAXIMUM(HP_K_MAX);
  }

  private int DMGWithoutModifier(float terrModifier) {
    int DMG = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    System.out.println(terrModifier);

    return Math.round(DMG * terrModifier);
  }

  private int DMGwithModifier(float terrModifier, float raceModifier) {
    int DMG = BASE_DAMAGE_EXECUTE + LEVEL_DAMAGE_EXECUTE * super.getLevel();
    System.out.println(DMG);
    return Math.round(DMG * terrModifier * raceModifier);
  }

  private int calculateHPlimit(Hero hero) {
    return Math.round(0.2f * (hero.XP_LEVEL_UP() - 1) + 0.01f * hero.getLevel());
  }

  @Override
  public void setUpDoT(Hero hero) {
    hero.getDot().setNumRounds(1);
    hero.getDot().setNumRoundsParalysis(1);
  }

  @Override
  public void isAttackedBy(Hero hero, MapoFGame map) {
    hero.ability1(this, map);
    hero.ability2(this, map);

  }

  @Override
  public void ability1(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = knightToRogueExecuteModifier;
    int HPlimit = calculateHPlimit(rogue);
    if (rogue.getCURR_HP() < HPlimit)  {
      rogue.setState("dead");
      super.setXP_WINNER(rogue.getLevel());
    } else {
      super.setDMGwithoutModifier1(DMGWithoutModifier(terrModifier));
      super.setDMGwithModifier1(DMGwithModifier(terrModifier, raceModifier));
      System.out.println(super.getDMGwithModifier1());
    }
  }

  @Override
  public void ability2(Rogue rogue, MapoFGame map) {
    float terrModifier = terrain(rogue, map).getKnightModifier();
    float raceModifier = knightToRogueSlamModifier;
    int DMG = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
    setUpDoT(rogue);
  }

  @Override
  public void ability1(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = knightToWizardExecuteModifier;
    int HPlimit = calculateHPlimit(wizard);
    if (wizard.getCURR_HP() < HPlimit)  {
      wizard.setState("dead");
      super.setXP_WINNER(wizard.getLevel());
    } else {
      super.setDMGwithoutModifier1(DMGWithoutModifier(terrModifier));
      super.setDMGwithModifier1(Math.round(DMGwithModifier(terrModifier, raceModifier)));
      System.out.println("p1:" + super.getDMGwithoutModifier1());
    }
  }

  @Override
  public void ability2(Wizard wizard, MapoFGame map) {
    float terrModifier = terrain(wizard, map).getKnightModifier();
    float raceModifier = knightToWizardSlamModifier;
    int DMG = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    System.out.println(DMG * terrModifier);
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
    setUpDoT(wizard);
    System.out.println("p2:" + super.getDMGwithoutModifier2());

  }

  @Override
  public void ability1(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = knightToPyromancerExecuteModifier;
    int HPlimit = calculateHPlimit(pyromancer);
    if (pyromancer.getCURR_HP() < HPlimit)  {
      pyromancer.setState("dead");
      super.setXP_WINNER(pyromancer.getLevel());
    } else {
      super.setDMGwithoutModifier1(DMGWithoutModifier(terrModifier));
      super.setDMGwithModifier1(DMGwithModifier(terrModifier, raceModifier));
      System.out.println(super.getDMGwithModifier1());
    }
  }
  @Override
  public void ability2(Pyromancer pyromancer, MapoFGame map) {
    float terrModifier = terrain(pyromancer, map).getKnightModifier();
    float raceModifier = knightToPyromancerSlamModifier;
    int DMG = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
    setUpDoT(pyromancer);
  }

  @Override
  public void ability1(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = knightToKnightExecuteModifier;
    int HPlimit = calculateHPlimit(knight);
    if (knight.getCURR_HP() < HPlimit)  {
      knight.setState("dead");
      super.setXP_WINNER(knight.getLevel());
    } else {
      super.setDMGwithoutModifier1(DMGWithoutModifier(terrModifier));
      super.setDMGwithModifier1(DMGwithModifier(terrModifier, raceModifier));
      System.out.println(super.getDMGwithModifier1());
    }
  }

  @Override
  public void ability2(Knight knight, MapoFGame map) {
    float terrModifier = terrain(knight, map).getKnightModifier();
    float raceModifier = knightToKnightSlamModifier;
    int DMG = BASE_DAMAGE_SLAM + LEVEL_DAMAGE_SLAM * super.getLevel();
    super.setDMGwithoutModifier2(Math.round(terrModifier * DMG));
    super.setDMGwithModifier2(Math.round(raceModifier * terrModifier * DMG));
    setUpDoT(knight);
  }
}

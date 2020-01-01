package main.helpers;

/**
 * Class for declaring constants.
 */
public final class Constants {
  public static final float WIZARD_TO_ROGUE_DRAIN_MODIFIER = -0.2f;
  public static final float WIZARD_TO_WIZARD_DRAIN_MODIFIER = 0.05f;
  public static final float WIZARD_TO_KNIGHT_DRAIN_MODIFIER = 0.2f;
  public static final float WIZARD_TO_PYROMANCER_DRAIN_MODIFIER = -0.1f;
  public static final float WIZARD_TO_ROGUE_DEFLECT_MODIFIER = 1.2f;
  public static final float WIZARD_TO_WIZARD_DEFLECT_MODIFIER = 0.0f;
  public static final float WIZARD_TO_KNIGHT_DEFLECT_MODIFIER = 1.4f;
  public static final float WIZARD_TO_PYROMANCER_DEFLECT_MODIFIER = 1.3f;
  public static final float PYROMANCER_TO_PYROMANCER_FIREBLAST_MODIFIER = 0.9f;
  public static final float PYROMANCER_TO_WIZARD_FIREBLAST_MODIFIER = 1.05f;
  public static final float PYROMANCER_TO_KNIGHT_FIREBLAST_MODIFIER = 1.2f;
  public static final float PYROMANCER_TO_ROGUE_FIREBLAST_MODIFIER = 0.8f;
  public static final float PYROMANCER_TO_PYROMANCER_IGNITE_MODIFIER = 0.9f;
  public static final float PYROMANCER_TO_WIZARD_IGNITE_MODIFIER = 1.05f;
  public static final float PYROMANCER_TO_KNIGHT_IGNITE_MODIFIER = 1.2f;
  public static final float PYROMANCER_TO_ROGUE_IGNITE_MODIFIER = 0.8f;
  public static final float ROGUE_TO_PYROMANCER_BACKSTAB_MODIFIER = 1.25f;
  public static final float ROGUE_TO_WIZARD_BACKSTAB_MODIFIER = 1.25f;
  public static final float ROGUE_TO_KNIGHT_BACKSTAB_MODIFIER = 0.9f;
  public static final float ROGUE_TO_ROGUE_BACKSTAB_MODIFIER = 1.2f;
  public static final float ROGUE_TO_PYROMANCER_PARALYSIS_MODIFIER = 1.2f;
  public static final float ROGUE_TO_WIZARD_PARALYSIS_MODIFIER = 1.25f;
  public static final float ROGUE_TO_KNIGHT_PARALYSIS_MODIFIER = 0.8f;
  public static final float ROGUE_TO_ROGUE_PARALYSIS_MODIFIER = 0.9f;
  public static final float KNIGHT_TO_KNIGHT_EXECUTE_MODIFIER = 1.0f;
  public static final float KNIGHT_TO_ROGUE_EXECUTE_MODIFIER = 1.15f;
  public static final float KNIGHT_TO_PYROMANCER_EXECUTE_MODIFIER = 1.1f;
  public static final float KNIGHT_TO_WIZARD_EXECUTE_MODIFIER = 0.8f;
  public static final float KNIGHT_TO_KNIGHT_SLAM_MODIFIER = 1.2f;
  public static final float KNIGHT_TO_ROGUE_SLAM_MODIFIER = 0.8f;
  public static final float KNIGHT_TO_PYROMANCER_SLAM_MODIFIER = 0.9f;
  public static final float KNIGHT_TO_WIZARD_SLAM_MODIFIER = 1.05f;
  public static final int HP_P_MAX = 500;
  public static final int HP_P_LEVEL = 50;
  public static final int HP_K_MAX = 900;
  public static final int HP_K_LEVEL = 80;
  public static final int HP_W_MAX = 400;
  public static final int HP_W_LEVEL = 30;
  public static final int HP_R_MAX = 600;
  public static final int HP_R_LEVEL = 40;
  public static final int BASE_DAMAGE_EXECUTE = 200;
  public static final int LEVEL_DAMAGE_EXECUTE = 30;
  public static final int BASE_DAMAGE_SLAM = 100;
  public static final int LEVEL_DAMAGE_SLAM = 40;
  public static final int BASE_DAMAGE_FIREBLAST = 350;
  public static final int LEVEL_DAMAGE_FIREBLAST = 50;
  public static final int LEVEL_DAMAGE_IGNITE = 20;
  public static final int BASE_DAMAGE_IGNITE = 150;
  public static final float PROCENT1 = 0.2f;
  public static final float PROCENT_LEVEL1 = 0.05f;
  public static final float PROCENT2 = 0.35f;
  public static final float PROCENT_LEVEL2 = 0.02f;
  public static final int BASE_DAMAGE_BACKSTAB = 200;
  public static final int LEVEL_DAMAGE_BACKSTAB = 20;
  public static final int DAMAGE_PARALYSIS = 40;
  public static final int LEVEL_DAMAGE_PARALYSIS = 10;
  public static final int BASIC_ROUNDS_ROGUE = 3;
  public static final int WOODS_ROUNDS_ROGUE = 6;
  public static final int DOT_IGNITE_DAMAGE = 50;
  public static final int DOT_IGNITE_LEVEL = 30;
  public static final int THREE = 3;
  public static final int FORTY = 40;
  public static final int FIFTY = 50;
  public static final float COEFICIENT = 1.5f;
  public static final int BORDER_VALUE = 250;
  public static final float PROCENT3 = 0.3f;
  public static final int MINIM_POINTS = 200;
  public static final float DMG_ANGEL_KNIGHT = 0.15f;
  public static final float DMG_ANGEL_PYROMANCER = 0.2f;
  public static final float DMG_ANGEL_ROGUE = 0.3f;
  public static final float DMG_ANGEL_WIZARD = 0.4f;
  public static final int DARK_ANGEL_KNIGHT = 40;
  public static final int DARK_ANGEL_ROGUE = 10;
  public static final int DARK_ANGEL_PYROMANCER = 30;
  public static final int DARK_ANGEL_WIZARD = 20;
  public static final float DRACULA_DMG_KNIGHT = 0.2f;
  public static final float DRACULA_DMG_PYROMANCER = 0.3f;
  public static final float DRACULA_DMG_ROGUE = 0.1f;
  public static final float DRACULA_DMG_WIZARD =  0.4f;
  public static final int DRACULA_HP_KNIGHT = 60;
  public static final int DRACULA_HP_PYROMANCER = 40;
  public static final int DRACULA_HP_ROGUE = 35;
  public static final int DRACULA_HP_WIZARD =  20;
  public static final float GOOD_DMG_KNIGHT = 0.4f;
  public static final float GOOD_DMG_PYROMANCER = 0.5f;
  public static final float GOOD_DMG_ROGUE = 0.4f;
  public static final float GOOD_DMG_WIZARD =  0.3f;
  public static final int GOOD_HP_KNIGHT = -20;
  public static final int GOOD_HP_PYROMANCER = -30;
  public static final int GOOD_HP_ROGUE = -40;
  public static final int GOOD_HP_WIZARD =  -50;
  public static final int LIFE_HP_KNIGHT = 100;
  public static final int LIFE_HP_PYROMANCER = 80;
  public static final int LIFE_HP_ROGUE = 90;
  public static final int LIFE_HP_WIZARD =  120;
  public static final float LEVEL_DMG_KNIGHT = 0.1f;
  public static final float LEVEL_DMG_PYROMANCER = 0.2f;
  public static final float LEVEL_DMG_ROGUE = 0.15f;
  public static final float LEVEL_DMG_WIZARD =  0.25f;
  public static final float SMALL_DMG_KNIGHT = 0.1f;
  public static final float SMALL_DMG_PYROMANCER = 0.15f;
  public static final float SMALL_DMG_ROGUE = 0.05f;
  public static final float SMALL_DMG_WIZARD =  0.1f;
  public static final int SMALL_HP_KNIGHT = 10;
  public static final int SMALL_HP_PYROMANCER = 15;
  public static final int SMALL_HP_ROGUE = 20;
  public static final int SMALL_HP_WIZARD = 25;
  public static final int SPAWNER_HP_KNIGHT = 200;
  public static final int SPAWNER_HP_PYROMANCER = 150;
  public static final int SPAWNER_HP_ROGUE = 180;
  public static final int SPAWNER_HP_WIZARD = 120;
  public static final int XP_KNIGHT = 45;
  public static final int XP_PYROMANCER = 50;
  public static final int XP_ROGUE = 40;
  public static final int XP_WIZARD = 60;
  public static final int TEN = 10;
  public static final int SEVEN = 7;
  public static final int SIX = 6;
  public static final int FIVE = 5;
  public static final int FOUR = 4;
  public static final float P1_STRATEGY_PERCENTAGE = 0.7f;
  public static final float P2_STRATEGY_PERCENTAGE = -0.3f;
  public static final float K1_STRATEGY_PERCENTAGE = 0.5f;
  public static final float K2_STRATEGY_PERCENTAGE = -0.2f;
  public static final float R1_STRATEGY_PERCENTAGE = 0.4f;
  public static final float R2_STRATEGY_PERCENTAGE = -0.1f;
  public static final float W1_STRATEGY_PERCENTAGE = 0.6f;
  public static final float W2_STRATEGY_PERCENTAGE = -0.2f;
  public static final int UPPER_BOUND = 1300;






  private Constants() { }
}



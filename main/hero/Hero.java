package main.hero;

import main.map.MapoFGame;
import main.map.Terrain;
import main.map.TerrainFactory;

import static java.lang.Integer.max;

public abstract class Hero {
  private int CURR_HP;
  private int XP;
  private int HP_MAXIMUM;
  private boolean verified;
  private int DMGwithModifier1;
  private int DMGwithoutModifier1;
  private int DMGwithModifier2;
  private int DMGwithoutModifier2;
  private int DMGwithModifier;
  private int DMGwithoutModifier;
  private int bonusLevel;
  private Location location;
  private int id;
  private char name;
  private int level;
  private int XP_WINNER;
  private String state;
  private DamageOverTime dot;

  Hero() {
    id = -1;
    name = '-';
    CURR_HP = 0;
    XP = 0;
    location = new Location();
    level = 0;
    XP_WINNER = -1;
    state = "alive";
    dot = new DamageOverTime();
    verified = false;
    bonusLevel = 0;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setHero(Location location, int id, char name) {
    this.location = location;
    this.id = id;
    this.name = name;
  }

  public void setHP_MAXIMUM(int HP_MAXIMUM) {
    this.HP_MAXIMUM = HP_MAXIMUM;
  }

  public int getHP_MAXIMUM() {
    return HP_MAXIMUM;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setDMGwithoutModifier(int DMGwithoutModifier) {
    this.DMGwithoutModifier = DMGwithoutModifier;
  }

  public void setDMGwithModifier(int DMGwithModifier) {
    this.DMGwithModifier = DMGwithModifier;
  }

  public void setDMGwithModifier2(int DMGwithModifier2) {
    this.DMGwithModifier2 = DMGwithModifier2;
  }

  public void setDMGwithoutModifier2(int DMGwithoutModifier2) {
    this.DMGwithoutModifier2 = DMGwithoutModifier2;
  }

  public int getDMGwithoutModifier() {
    return DMGwithoutModifier;
  }

  public int getDMGwithModifier() {
    return DMGwithModifier;
  }

  public int getDMGwithModifier2() {
    return DMGwithModifier2;
  }

  public int getDMGwithoutModifier2() {
    return DMGwithoutModifier2;
  }

  public int getId() {
    return id;
  }

  public void setDMGwithoutModifier1(int DMGwithoutModifier1) {
    this.DMGwithoutModifier1 = DMGwithoutModifier1;
  }

  public int getDMGwithoutModifier1() {
    return DMGwithoutModifier1;
  }

  public void setDot(DamageOverTime dot) {
    this.dot = dot;
  }

  public DamageOverTime getDot() {
    return dot;
  }

  public void setDMGwithModifier1(int DMGwithModifier1) {
    this.DMGwithModifier1 = DMGwithModifier1;
  }

  public int getDMGwithModifier1() {
    return DMGwithModifier1;
  }

  public void setCURR_HP(int CURR_HP) {
    this.CURR_HP = CURR_HP;
  }

  public int getCURR_HP() {
    return CURR_HP;
  }

  public void setName(char name) {
    this.name = name;
  }

  public char getName() {
    return name;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Location getLocation() {
    return location;
  }

  public void setXP(int XP) {
    this.XP = XP;
  }

  public int getXP() {
    return XP;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setBonusLevel(int bonusLevel) {
    this.bonusLevel = bonusLevel;
  }

  public int getBonusLevel() {
    return bonusLevel;
  }

  public int getLevel() {
    return level;
  }

  public void setXP_WINNER(int level_loser) {
    this.XP = XP + max(0, (200 - (level - level_loser) * 40));
  }

  public int calculateLevelUp(int points) {
    if (points < 250)
      return  0;
    if ((float)((points - 250) % 50) >= 0)
      return ((points - 250) / 50) + 1;
    else
      return ((points - 250) / 50);
  }
  public int XP_LEVEL_UP() {
    int XP_LEVEL_UP;
    XP_LEVEL_UP = 250 + level * 50;
    return XP_LEVEL_UP;
  }

  public void modifyHP(int DMG) {
    CURR_HP -= DMG;
  }

  public Terrain terrain(Hero hero, MapoFGame map) {
    Terrain terrain = map.getPieceOfMap(hero.getLocation().getRow(), hero.getLocation().getCol());
    Character terrName = terrain.getName();
    return TerrainFactory.getInstance().getTerrainByChar(terrName);
  }
  public abstract void setUpDoT(Hero hero);

  public abstract void isAttackedBy(Hero hero, MapoFGame map);
  public abstract void ability1(Rogue rogue, MapoFGame map);
  public abstract void ability2(Rogue rogue, MapoFGame map);
  public abstract void ability1(Wizard wizard, MapoFGame map);
  public abstract void ability2(Wizard wizard, MapoFGame map);
  public abstract void ability1(Pyromancer pyromancer, MapoFGame map);
  public abstract void ability2(Pyromancer pyromancer, MapoFGame map);
  public abstract void ability1(Knight knight, MapoFGame map);
  public abstract void ability2(Knight knight, MapoFGame map);

}

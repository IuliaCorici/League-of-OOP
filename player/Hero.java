package player;

import static java.lang.Integer.max;

public abstract class Hero {
  private int HP;
  private int XP;
  private int DMG;
  private Location location;
  private int id;
  private int name;
  private int level;
  private int XP_WINNER;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setDMG(int DMG) {
    this.DMG = DMG;
  }

  public int getDMG() {
    return DMG;
  }

  public void setHP(int HP) {
    this.HP = HP;
  }

  public int getHP() {
    return HP;
  }

  public void setName(int name) {
    this.name = name;
  }

  public int getName() {
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

  public int getLevel() {
    return level;
  }

  public void setXP_WINNER(int level_loser) {
    this.XP_WINNER = XP_WINNER + max(0, (200 - (level - level_loser) * 40));
  }

  public int XP_LEVEL_UP () {
    int XP_LEVEL_UP;
    XP_LEVEL_UP = 250 + level * 50;
    return XP_LEVEL_UP;
  }
}

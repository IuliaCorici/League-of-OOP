package hero;

import static java.lang.Integer.max;

public abstract class Hero {
  private int CURR_HP;
  private int XP;
  private int DMG;
  private Location location;
  private int id;
  private char name;
  private int level;
  private int XP_WINNER;
  private String state;

  Hero() {
    id = -1;
    name = '-';
    CURR_HP = 0;
    XP = 0;
    location = null;
    level = -1;
    XP_WINNER = -1;
    state = "alive";
  }

  public void setHero(Location location, int id, char name) {
    this.location = location;
    this.id = id;
    this.name = name;
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

  public int getId() {
    return id;
  }

  public void setDMG(int DMG) {
    this.DMG = DMG;
  }

  public int getDMG() {
    return DMG;
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

  public abstract void isAttackedBy(Hero hero);
  public abstract void ability1(Rogue rogue);
  public abstract void ability2(Rogue rogue);
  public abstract void ability1(Wizard wizard);
  public abstract void ability2(Wizard wizard);
  public abstract void ability1(Pyromancer pyromancer);
  public abstract void ability2(Pyromancer pyromancer);
  public abstract void ability1(Knight knight);
  public abstract void ability2(Knight knight);

}

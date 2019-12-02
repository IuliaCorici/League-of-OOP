package main.hero;

import main.map.MapoFGame;
import main.map.Terrain;
import main.map.TerrainFactory;

import static java.lang.Integer.max;
import static main.helpers.Constants.BORDER_VALUE;
import static main.helpers.Constants.MINIM_POINTS;

public abstract class Hero {
  private int currHp;
  private int xp;
  private int hpMaximum;
  private boolean verified;
  private int dmgwithmodifier1;
  private int dmgwithoutmodifier1;
  private int dmgwithmodifier2;
  private int dmgwithoutmodifier2;
  private int bonusLevel;
  private Location location;
  private int id;
  private char name;
  private int level;
  private int xpWinner;
  private String state;
  private DamageOverTime dot;

  Hero() {
    id = -1;
    name = '-';
    currHp = 0;
    xp = 0;
    location = new Location();
    level = 0;
    xpWinner = -1;
    state = "alive";
    dot = new DamageOverTime();
    verified = false;
    bonusLevel = 0;
  }

  public final void setXpWinner(final int levelLoser) {
    this.xp = this.xp + max(0, (MINIM_POINTS - (level - levelLoser) * 40));
  }

  public final int calculateLevelUp(final int points) {
    if (points < BORDER_VALUE) {
      return 0;
    }
    if ((float) ((points - BORDER_VALUE) % 50) >= 0) {
      return ((points - BORDER_VALUE) / 50) + 1;
    } else {
      return ((points - BORDER_VALUE) / 50);
    }
  }
  public final int xpLevelUp() {
    int xplevelup;
    xplevelup = BORDER_VALUE + level * 50;
    return xplevelup;
  }

  public final void modifyHP(final int dmg) {
    currHp -= dmg;
  }

  public final Terrain terrain(final Hero hero, final MapoFGame map) {
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

  public final void setVerified(final boolean verified) {
    this.verified = verified;
  }

  public final boolean isVerified() {
    return verified;
  }

  public final void setHero(final Location location1, final int id1, final char name1) {
    this.location = location1;
    this.id = id1;
    this.name = name1;
  }

  public final void setHpMaximum(final int hpMaximum) {
    this.hpMaximum = hpMaximum;
  }

  public final int getHpMaximum() {
    return hpMaximum;
  }

  public final String getState() {
    return state;
  }
  public final void setState(final String state) {
    this.state = state;
  }

  public final void setDmgwithmodifier2(final int dmgwithmodifier2) {
    this.dmgwithmodifier2 = dmgwithmodifier2;
  }
  public final void setDmgwithoutmodifier2(final int dmgwithoutmodifier2) {
    this.dmgwithoutmodifier2 = dmgwithoutmodifier2;
  }

  public final int getDmgwithmodifier2() {
    return dmgwithmodifier2;
  }

  public final int getDmgwithoutmodifier2() {
    return dmgwithoutmodifier2;
  }

  public final int getId() {
    return id;
  }

  public final void setDmgwithoutmodifier1(final int dmgwithoutmodifier1) {
    this.dmgwithoutmodifier1 = dmgwithoutmodifier1;
  }

  public final int getDmgwithoutmodifier1() {
    return dmgwithoutmodifier1;
  }

  public final void setDot(final DamageOverTime dot) {
    this.dot = dot;
  }

  public final DamageOverTime getDot() {
    return dot;
  }

  public final void setDmgwithmodifier1(final int dmgwithmodifier1) {
    this.dmgwithmodifier1 = dmgwithmodifier1;
  }

  public final int getDmgwithmodifier1() {
    return dmgwithmodifier1;
  }

  public final void setCurrHp(final int currHp) {
    this.currHp = currHp;
  }

  public final int getCurrHp() {
    return currHp;
  }

  public final char getName() {
    return name;
  }

  public final Location getLocation() {
    return location;
  }

  public final int getXp() {
    return xp;
  }

  public final void setLevel(final int level) {
    this.level = level;
  }

  public final void setBonusLevel(final int bonusLevel) {
    this.bonusLevel = bonusLevel;
  }

  public final int getBonusLevel() {
    return bonusLevel;
  }

  public final int getLevel() {
    return level;
  }

}

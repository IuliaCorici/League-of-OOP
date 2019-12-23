package main.hero;

/**
 * Class created in order to keep track of the damage over time.
 * It is known the number of rounds the hero is going to be paralysed or extra damaged because of
 * other hero.
 */

public class DamageOverTime {
  private int numRoundsParalysis;
  private int numRounds;
  private int perRoundDMG;
  private int currRound;

  public final void setNumRounds(final int numRounds) {
    this.numRounds = numRounds;
  }

  public final int getNumRounds() {
    return numRounds;
  }

  public final int getNumRoundsParalysis() {
    return numRoundsParalysis;
  }

  public final void setNumRoundsParalysis(final int numRoundsParalysis) {
    this.numRoundsParalysis = numRoundsParalysis;
  }

  public final int getPerRoundDMG() {
    return perRoundDMG;
  }

  public final void setPerRoundDMG(final int perRoundDMG) {
    this.perRoundDMG = perRoundDMG;
  }

  public final int getCurrRound() {
    return currRound;
  }

  public final void setCurrRound(final int currRound) {
    this.currRound = currRound;
  }

  public DamageOverTime() {
    numRoundsParalysis = 0;
    perRoundDMG = 0;
    currRound = 0;
    numRounds = 0;
  }
}

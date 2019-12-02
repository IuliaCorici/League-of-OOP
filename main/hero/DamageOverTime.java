package main.hero;

public class DamageOverTime {
  private int numRoundsParalysis;
  private int numRounds;
  private int perRoundDMG;
  private int currRound;

  public void setNumRounds(int numRounds) {
    this.numRounds = numRounds;
  }

  public int getNumRounds() {
    return numRounds;
  }

  public int getNumRoundsParalysis() {
    return numRoundsParalysis;
  }

  public void setNumRoundsParalysis(int numRoundsParalysis) {
    this.numRoundsParalysis = numRoundsParalysis;
  }

  public int getPerRoundDMG() {
    return perRoundDMG;
  }

  public void setPerRoundDMG(int perRoundDMG) {
    this.perRoundDMG = perRoundDMG;
  }

  public void increaseDMG(int coeficient) {
    perRoundDMG += coeficient;
  }

  public int getCurrRound() {
    return currRound;
  }

  public void setCurrRound(int currRound) {
    this.currRound = currRound;
  }
  public DamageOverTime() {
    numRoundsParalysis = 0;
    perRoundDMG = 0;
    currRound = -1;
    numRounds = 0;
  }
  public DamageOverTime(int numRounds, int perRoundDMG) {
    this.numRounds = numRounds;
    this.perRoundDMG = perRoundDMG;
  }
}

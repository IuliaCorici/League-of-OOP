package player;

public class Location {
  private int row;
  private int col;
  private char terrain;

  Location initLocation(int row, int col, char )
  public char getTerrain() {
    return terrain;
  }

  public void setTerrain(char land) {
    this.terrain = land;
  }


  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }
}

package main.hero;

import java.util.Objects;

public class Location {
  private int row;
  private int col;

  public Location() {
    row = -1;
    col = -1;
  }

  public void initLocation(int row, int col) {
    this.col = col;
    this.row = row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return row == location.row &&
        col == location.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
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


  public void executeUpMove() {
    int oldRow = row;
    int oldcol = col;
    initLocation(oldRow - 1, oldcol);
  }

  public void executeDownMOve() {
    int oldRow = row;

    initLocation(oldRow + 1, col);
  }
  public void setMoveNoWhere() {

  }
  public void executeLeftMove() {
    int oldCol = col;
    initLocation(row, oldCol - 1 );
  }

  public void executeRightMove() {
    int oldCol = col;
    initLocation(row, oldCol + 1);
  }

  public void executeMove(final Character type) {
    switch (type) {
      case 'D':
        executeDownMOve();
        break;
      case 'R':
        executeRightMove();
        break;
      case 'U':
        executeUpMove();
        break;
      case 'L':
        executeLeftMove();
        break;
      case '_':
        setMoveNoWhere();
        break;
    }
  }
}

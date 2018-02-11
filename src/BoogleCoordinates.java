
class BoogleCoordinates {
  private final BoggleBoard board;
  private final int row;
  private final int col;

  public BoogleCoordinates(BoggleBoard board, int row, int col) {
    this.board = board;
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public static boolean hasDiagNorthEast(BoggleBoard board, int row2, int col2) {
    return row2 > 0 && col2 < board.cols() - 1;
  }

  public static boolean hasEast(BoggleBoard board, int row2, int col2) {
    return col2 < board.cols() - 1;
  }

  public static boolean hasDiagSouthEast(BoggleBoard board, int row2, int col2) {
    return row2 < board.rows() - 1 && col2 < board.cols() - 1;
  }

  public static boolean hasSouth(BoggleBoard board, int row2, int col2) {
    return row2 < board.rows() - 1;
  }

  public static boolean hasDiagSouthWest(BoggleBoard board, int row2, int col2) {
    return row2 < board.rows() - 1 && col2 > 0;
  }

  public static boolean hasWest(BoggleBoard board, int row2, int col2) {
    return col2 > 0;
  }

  public static boolean hasDiagNorthWest(BoggleBoard board, int row2, int col2) {
    return row2 > 0 && col2 > 0;
  }

  public static boolean hasNorth(BoggleBoard board, int row2, int col2) {
    return row2 > 0;
  }

  @Override
  public String toString() {
    return "{" + getLetter() + "}";
    // StringBuilder sb = new StringBuilder();
    // sb.append("( row: " + this.getRow() + " col: " + this.getCol() + " )");
    // return sb.toString();
  }

  public char getLetter() {
    return board.getLetter(row, col);
  }
}
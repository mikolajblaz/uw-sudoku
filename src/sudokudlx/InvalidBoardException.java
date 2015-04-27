package sudokudlx;

/** Exception thrown in case of inconsistent sudoku board state. */
public class InvalidBoardException extends Exception {
    public int row = 0;
    public int col = 0;

    public InvalidBoardException(String s) {
        super(s);
    }

    public InvalidBoardException(int row, int col) {
        super("Conflict in row: " + String.valueOf(row) +
              ", column: " + String.valueOf(col));
        this.row = row;
        this.col = col;
    }
}

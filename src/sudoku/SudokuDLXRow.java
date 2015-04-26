package sudoku;

/**
 * Class representing one row in a coincidence matrix corresponding to an
 * exact cover of a sudoku problem. Each row in this matrix has format: R_C_#_,
 * where _ is a digit 1-9 and letters are: R - row, C - column, # - number
 * and corresponds to this specific row and column being filled with that number
 */
public class SudokuDLXRow {
    protected int row;
    protected int col;
    protected int number;

    protected boolean selected;  // whether the cell is already filled
    protected boolean deleted;   // whether the row is conflicting with filled numbers
}

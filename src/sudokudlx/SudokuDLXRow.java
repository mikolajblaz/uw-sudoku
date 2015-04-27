package sudokudlx;

/**
 * Class representing one row in a coincidence matrix corresponding to an
 * exact cover of a sudoku problem. Each row in this matrix has format: R_C_#_,
 * where _ is a digit 0-8 (!!!) and letters are: R - row, C - column, # - digit
 * and corresponds to this specific row and column being filled with that digit
 *
 * CAUTION! unlike in SudokuBoard, digit is a number between 0 and 8 !
 */
class SudokuDLXRow {
    protected int row;          // 0-8 !
    protected int col;          // 0-8 !
    protected int digit;        // 0-8 !

    protected boolean selected;  // whether the cell is already filled
    protected boolean deleted;   // whether the row is conflicting with filled numbers

    public SudokuDLXRow(int row, int col, int digit, boolean selected) {
        this.row = row;
        this.col = col;
        this.digit = digit;
        this.selected = selected;
        this.deleted = selected;        // initially selected == deleted
    }

    public void select() {
        selected = true;
        deleted = true;
    }

    public void exclude() {
        deleted = true;
    }

}

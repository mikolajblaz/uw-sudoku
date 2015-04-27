package sudokudlx;

/**
 * Class representing one row in a coincidence matrix corresponding to an
 * exact cover of a sudoku problem. Each row in this matrix has format: R_C_#_,
 * where _ is a digit 0-8 (!!!) and letters are: R - row, C - column, # - digit
 * and corresponds to this specific row and column being filled with that digit
 *
 * CAUTION! unlike in SudokuBoard, digit is a number between 0 and 8 !
 */
public class SudokuDLXRow {
    protected int row;          // 0-8 !
    protected int col;          // 0-8 !
    protected int digit;        // 0-8 !

    protected boolean selected;  // whether the cell is already filled
    protected boolean deleted;   // whether the row is conflicting with filled numbers

    SudokuDLXRow(int row, int col, int digit, boolean selected) {
        this.row = row;
        this.col = col;
        this.digit = digit;
        this.selected = selected;
        this.deleted = selected;        // initially selected == deleted
    }

    void select() {
        selected = true;
        deleted = true;
    }

    void exclude() {
        deleted = true;
    }

    public int getRow()   { return row; }
    public int getCol()   { return col; }
    public int getDigit() { return digit; }

    @Override
    public String toString() {
        return "R" + String.valueOf(row) + "C" + String.valueOf(col) + "#" + String.valueOf(digit) +
                "   sel: [" + selected + "], del: " + deleted + "]";
    }

    public String toShortString() {
        return "R" + String.valueOf(row) + "C" + String.valueOf(col) + "#" + String.valueOf(digit);
    }
}

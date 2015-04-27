package sudokudlx;

/**
 * Class representing one choice: row, column, number
 * CAUTION! unlike in SudokuDLXRow, number is between 1 and 9 !
 */
public class SudokuChoice {
    protected int row;           // 1-9 !
    protected int col;           // 1-9 !
    protected int number;        // 1-9 !

    SudokuChoice(SudokuDLXRow sudokuDLXRow) {
        this.row = sudokuDLXRow.row + 1;    // different numeration!
        this.col = sudokuDLXRow.col + 1;
        this.number = sudokuDLXRow.number + 1;
    }

    @Override
    public String toString() {
        return "R" + String.valueOf(row) + "C" + String.valueOf(col) + "#" + String.valueOf(number);
    }
}

package sudoku;

/**
 * Class representing sudoku board.
 *
 * CAUTION! Rows and columns are indexed from 0 to size-1.
 * Cells are filled with numbers from 1 to 9.
 */
public class SudokuBoard {
    final int size;
    int[][] board;

    public SudokuBoard(int[][] board) {
        this.board = board;
        this.size = board.length;
    }
}

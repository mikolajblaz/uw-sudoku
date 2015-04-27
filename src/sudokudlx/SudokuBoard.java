package sudokudlx;

/**
 * Class representing sudoku board.
 *
 * CAUTION! Rows and columns are indexed from 0 to size-1.
 * Cells are filled with numbers from 1 to 9.
 */
class SudokuBoard {
    protected final int size;
    protected int[][] board;

    public SudokuBoard(String str) throws InvalidBoardException {
        this(boardFromString(str));
    }

    public SudokuBoard(int[][] board) {
        this.board = board;
        this.size = board.length;
    }

    public int getSize() {
        return size;
    }

    public int[][] getBoard() {
        return board;
    }

    private static int[][] boardFromString(String str)
            throws InvalidBoardException {
        char[] digits;
        String[] rows = str.split("\\s+");
        int sudokuSize = rows.length;
        int[][] result = new int[sudokuSize][sudokuSize];

        for (int row = 0; row < rows.length; ++row) {
            digits = rows[row].toCharArray();
            if (digits.length != sudokuSize) {
                throw new InvalidBoardException("Invalid string format");
            }
            for (int i = 0; i < sudokuSize; ++i) {
                if(Character.isDigit(digits[i])) {
                    result[row][i] = Character.digit(digits[i], 10);
                } else {
                    throw new InvalidBoardException("Invalid string characters");
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                result.append(num);
            }
            result.append("\n");
        }
        return result.toString();
    }
}

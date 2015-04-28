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
        this(boardFromLine(str));
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

    /**
     * Create board given string in format : ROW ROW ROW ... ROW
     * Suitable for sudoku of any size, also in invalid format
     */
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

    /**
     * Create board given string in format : ROWROWROW...ROW
     * Suitable only for valid 9x9 sudoku
     */
    private static int[][] boardFromLine(String str) {
        int[][] result = new int[9][9];
        char[] line = str.toCharArray();
        int pos = 0;
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                result[row][col] = Character.digit(line[pos], 10);
                pos++;
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

    /** Validate 9x9 sudoku */
    public boolean isValid() {
        boolean valid = true;
        for (int i = 0; i < 9; ++i) {
            if (!(validRow(i) && validCol(i) && validBox(i))) {
                valid = false;
                break;
            }
        }
        return valid;
    }


    private boolean validCol(int col) {
        boolean valid = true;
        boolean numbers[] = new boolean[9];
        for (int row = 0; row < 9; ++row)
            numbers[board[row][col] - 1] = true;

        for (int num = 0; num < 9; ++num)
            if (!numbers[num])
                valid = false;
        return valid;
    }

    private boolean validRow(int row) {
        boolean valid = true;
        boolean numbers[] = new boolean[9];
        for (int col = 0; col < 9; ++col)
            numbers[board[row][col] - 1] = true;

        for (int num = 0; num < 9; ++num)
            if (!numbers[num])
                valid = false;
        return valid;
    }

    private boolean validBox(int box) {
        boolean valid = true;
        boolean numbers[] = new boolean[9];
        int initRow = 3 * (box / 3);
        int initCol = 3 * (box % 3);
        for (int row = initRow; row < initRow + 3; ++row)
            for (int col = initCol; col < initCol + 3; ++col)
                numbers[board[row][col] - 1] = true;

        for (int num = 0; num < 9; ++num)
            if (!numbers[num])
                valid = false;
        return valid;
    }

    /** Checks if 'this' is derived from 'oldBoard' (only 9x9) */
    public boolean isDerivedFrom(SudokuBoard oldSudokuBoard) {
        int[][] oldBoard = oldSudokuBoard.board;
        boolean valid = true;

        for (int row = 0; row < 9 && valid; ++row) {
            for (int col = 0; col < 9; ++col) {
                if (oldBoard[row][col] != 0 && oldBoard[row][col] != board[row][col])
                    valid = false;
            }
        }
        return valid;
    }
}

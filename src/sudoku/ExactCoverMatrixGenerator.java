package sudoku;

/** Class used to generate an exact cover matrix given sudoku board. */
class ExactCoverMatrixGenerator {
    private final int constraints = 4;  // number of constraints in exact cover
    private int[][] sudoku;
    private final int sudokuSize;       // typically 9
    private boolean[][] matrix;           // exact cover matrix
    private int width;                  // width of 'matrix'
    private int height;                 // height of 'matrix'
    private SudokuDLXRow[] DLXRows;

    public ExactCoverMatrixGenerator(SudokuBoard sudoku)
            throws InvalidBoardException {
        this.sudoku = sudoku.board;
        this.sudokuSize = sudoku.size;
        this.height = sudokuSize * sudokuSize * sudokuSize;
        this.width = sudokuSize * sudokuSize * constraints;
        this.DLXRows = new SudokuDLXRow[height];
        this.matrix = new boolean[width][height];

        fillDLXRows();
        generateMatrix();
    }

    private void fillDLXRows() {
        int i = 0;
        for (int row = 0; row < sudokuSize; ++row)
            for (int col = 0; col < sudokuSize; ++col)
                for (int digit = 0; digit < sudokuSize; ++digit)
                    if (sudoku[row][col] == digit + 1)   // different numeration
                        DLXRows[i++] = new SudokuDLXRow(row, col, digit, true);
                    else
                        DLXRows[i++] = new SudokuDLXRow(row, col, digit, false);
    }


    private void generateMatrix() throws InvalidBoardException {
        RowIterator rowIt = new RowIterator();
        int nextCol = 0;
        for (int i = 0; i < width; ++i) {
            nextCol = generateColumn(nextCol, rowIt);
        }

    }

    private int generateColumn(int col, RowIterator rowIt)
            throws InvalidBoardException {
        boolean alreadyHit = false;   // whether column 'col' was hit by any row
        int row;

        for (int i = 0; i < sudokuSize; ++i) {
            row = rowIt.next();
            if (DLXRows[row].selected) {
                if (alreadyHit)
                    throw new InvalidBoardException();
                alreadyHit = true;
            } else if (DLXRows[row].excluded) {
                if (alreadyHit)
                    throw new InvalidBoardException();
            }
        }
        /* now rowIt will be looping over the same rows as before: */
        if (alreadyHit) {   // exclude column and delete all rows with 1 in that column
            for (int i = 0; i < sudokuSize; ++i) {
                row = rowIt.next();
                DLXRows[row].exclude();
            }
        } else {            // include column to matrix
            for (int i = 0; i < sudokuSize; ++i) {
                row = rowIt.next();
                matrix[row][col] = true;
            }
            col += 1;       // column is included
        }

        return col;
    }
}

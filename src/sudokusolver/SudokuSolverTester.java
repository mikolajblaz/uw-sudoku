package sudokusolver;

/** Klasa do testowania klasy SudouSolver. */
class SudokuSolverTester extends SudokuSolver {
    public SudokuSolverTester(String sudokuStr) throws InvalidBoardException {
        super(new SudokuBoardTester(sudokuStr));
    }

    public SudokuBoardTester getSolvedSudoku() throws NoSolutionException {
        getSolvedBoard();
        return new SudokuBoardTester(sudokuBoard.board);
    }

    public String getSolvedString() throws NoSolutionException {
        getSolvedBoard();
        return sudokuBoard.toString();
    }
}

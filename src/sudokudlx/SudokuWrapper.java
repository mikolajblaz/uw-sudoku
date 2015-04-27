package sudokudlx;

import dancinglinks.DancingLinks;
import dancinglinks.Solution;

import java.util.LinkedList;
import java.util.List;

/** A class which allows to use 'sudokudlx' package with ease. */
public class SudokuWrapper {
    private SudokuBoard sudokuBoard;
    private List<SudokuSolution> solutions = new LinkedList<>();

    public SudokuWrapper(String sudokuStr) throws InvalidBoardException {
        this.sudokuBoard = new SudokuBoard(sudokuStr);
    }

    /** Solves sudoku and returns all solutions. */
    public List<SudokuSolution> solve() throws InvalidBoardException {
        ExactCoverMatrixGenerator generator = new ExactCoverMatrixGenerator(sudokuBoard);
        boolean[][] matrix = generator.getExactCoverMatrix();
        SudokuDLXRow[] labels = generator.getDancingLinksLabels();

        DancingLinks dl = new DancingLinks(matrix);
        List<Solution> raw_solutions = dl.exactCover();

        /* Translate raw solutions to SudokuSoltions: */
        for (Solution raw_sol : raw_solutions) {
            solutions.add(new SudokuSolution(raw_sol, labels));
        }
        return solutions;
    }


    public int[][] getSolvedArray() throws NoSolutionException {
        if (solutions.isEmpty())
            throw new NoSolutionException();

        int[][] board = sudokuBoard.board;
        for (SudokuChoice choice : solutions.get(0).getChoices()) {
            board[choice.row - 1][choice.col - 1] = choice.number;
        }

        return board;
    }

    public SudokuBoard getSolvedBoard() throws NoSolutionException {
        getSolvedArray();
        return sudokuBoard;
    }

    public String getSolvedString() throws NoSolutionException {
        return getSolvedBoard().toString();
    }
}

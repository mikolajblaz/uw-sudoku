package sudokudlx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/** Test usage of sudokudlx package, i.e. solving sudoku. */
public class Tester {
    /** For each argument in 'args' solve all sudokus in file */
    public static void main(String[] args) {
        for (String file : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    solveSudoku(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void solveSudoku(String sudokuLine) {
        try {
            SudokuWrapper wrapper = new SudokuWrapper(sudokuLine);
            wrapper.solve();
            SudokuBoard oldBoard = new SudokuBoard(sudokuLine);
            SudokuBoard solvedBoard = wrapper.getSolvedBoard();

            System.out.println(solvedBoard.toString());
            System.out.println();

            if (!(solvedBoard.isValid() && solvedBoard.isDerivedFrom(oldBoard))) {
                System.err.println("ERROR in solving! Original board:");
                System.err.println(oldBoard.toString());
                System.err.println("Solved board:");
                System.err.println(solvedBoard.toString());
                System.err.println();
                System.err.println();
            }


        } catch (InvalidBoardException | NoSolutionException e) {
            e.printStackTrace();
            System.err.println("ERROR in line:");
            System.err.println(sudokuLine);
            System.err.println();
            System.err.println();
        }
    }





    public static void manual() {
        String sudoku = "100000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000";

        String sudoku2 =    "1320 " +
                "0010 " +
                "4030 " +
                "0001 ";

        String sudoku3 =    "020000000" +
                "000600003" +
                "074080000" +
                "000003002" +
                "080040010" +
                "600500000" +
                "000010780" +
                "500009000" +
                "000000040";

        try {
            SudokuWrapper wrapper = new SudokuWrapper(sudoku3);
            List<SudokuSolution> solutions = wrapper.solve();

            System.out.println(solutions);
            System.out.println(wrapper.getSolvedString());


        } catch (InvalidBoardException | NoSolutionException e) {
            e.printStackTrace();
        }
    }
    
}

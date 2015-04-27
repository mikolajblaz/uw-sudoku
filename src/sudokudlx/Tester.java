package sudokudlx;

import java.util.List;

public class Tester {

    public static void main(String[] args) {
        String sudoku = "100000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 " +
                        "000000000 ";

        String sudoku2 =    "1320 " +
                            "0010 " +
                            "4030 " +
                            "0001 ";

        try {
            SudokuWrapper wrapper = new SudokuWrapper(sudoku2);
            List<SudokuSolution> solutions = wrapper.solve();

            System.out.println(solutions);
            System.out.println(wrapper.getSolvedString());


        } catch (InvalidBoardException e) {
            e.printStackTrace();
        } catch (NoSolutionException e) {
            e.printStackTrace();
        }
    }
    
}

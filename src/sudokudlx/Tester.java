package sudokudlx;

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
                            "0210 " +
                            "4030 " +
                            "0001 ";

        try {
            SudokuBoard sudokuBoard = new SudokuBoard(sudoku2);
            ExactCoverMatrixGenerator cover = new ExactCoverMatrixGenerator(sudokuBoard);

            boolean[][] matrix = cover.getExactCoverMatrix();
            SudokuDLXRow[] labels = cover.getDLXRows();
            for (int i = 0; i < matrix.length; ++i) {
                System.out.print(labels[i].toShortString());
                System.out.print('[');
                for (boolean b : matrix[i])
                    System.out.print(b ? "1" : "0");
                System.out.println(']');
            }

        } catch (InvalidBoardException e) {
            e.printStackTrace();
        }
    }
    
}

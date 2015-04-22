package sudoku;

public class Solver {

    public static void main(String[] args) {
        int[][] data = {
                {0, 0, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        int[][] data2 = {
                {0, 0, 1, 1, 0},
                {0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        MatrixZeroOne matrix = new MatrixZeroOne(data);
        Header sparse = matrix.toSparse();

        System.out.println("#################################");

        MatrixZeroOne matrix2 = new MatrixZeroOne(data2);
        Header sparse2 = matrix2.toSparse();

    }
}

package sudoku;

public class Solver {

    public static void main(String[] args) {
        int[][] data = {
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        int[][] data2 = {
                {0}
                /*{0, 0, 1, 1, 0},
                {0, 0, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1},*/
        };

        MatrixZeroOne matrix = new MatrixZeroOne(data);
        Header sparse = matrix.toSparse();

        System.out.println("#################################");

        MatrixZeroOne matrix2 = new MatrixZeroOne(data2);
        Header sparse2 = matrix2.toSparse();

        DancingLinks dlx = new DancingLinks(sparse);
        dlx.step();
        System.out.println("#################################");
        System.out.println(dlx.getSolutions().toString());

        DancingLinks dlx2 = new DancingLinks(sparse2);
        dlx2.step();
        System.out.println("#################################");
        System.out.println(dlx2.getSolutions().toString());

    }
}

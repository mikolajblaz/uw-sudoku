package sudoku;

import dancinglinks.DancingLinks;

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

        System.out.println("#################################");

        DancingLinks dlx = new DancingLinks(data);
        System.out.println(dlx.exactCover().toString());

        System.out.println("#################################");

        DancingLinks dlx2 = new DancingLinks(data2);
        System.out.println(dlx2.exactCover().toString());

    }
}

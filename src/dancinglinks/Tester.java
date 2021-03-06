package dancinglinks;

/**
 * Test package DancingLinks
 */
public class Tester {
    public static void main(String[] args) {
        manual();
    }


    public static void manual() {
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
        System.out.println(dlx.exactCover(0).toString());

        System.out.println("#################################");

        DancingLinks dlx2 = new DancingLinks(data2);
        System.out.println(dlx2.exactCover(0).toString());

    }
}

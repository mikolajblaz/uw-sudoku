package sudoku;

/** Class generating sparse matrix representation given a matrix of 0's and 1's */
public class MatrixZeroOne {
    private boolean[][] data;
    private int width;
    private int height;

    private Cell[][] sparseData;
    private Header[] headers;

    public MatrixZeroOne(boolean[][] array) {
        data = array;
        height = array.length;
        width = array[0].length;
    }

    /** Generate sparse matrix representation of a matrix.
     *  'Array' is arranged rowwise. Returns reference to "superHead". */
    Header toSparse() {
        if (height == 0 || width == 0) {
            return null;
        } else {
            sparseData = new Cell[height][width];
            headers = new Header[width + 1];
            Header superHead = headers[width + 1];

            /* Linking everything: */
            link_headers();
            for (int row = 0; row < height; ++row) {
                link_horizontally(row);
            }
            for (int col = 0; col < width; ++col) {
                link_vertically(col);
            }

            superHead.down = superHead.up = superHead.head = superHead;
            return superHead;
        }
    }

    /** Sets 'left' and 'right' attribute of each header. */
    private void link_headers() {
        for (int col = 0; col < width; ++col) {
            headers[col].right = headers[col + 1];
            headers[col + 1].left = headers[col];
        }
        headers[width].right = headers[0];
        headers[0].left = headers[width];
    }

    /** Sets 'left' and 'right' attribute of each cell in a row 'row'. */
    private void link_horizontally(int row) {
        int l, r;       // two neighbouring cells of 1 moving right
        int first = first_horizontal(row);

        if (first >= 0) {   // non-empty row
            r = first;

            do {
                l = r;
                r = next_horizontal(row, r);
                sparseData[row][l].right = sparseData[row][r];
                sparseData[row][r].left = sparseData[row][l];
            } while (r != first);
        }
    }

    /** Returns index of first non-empty column in row 'row' or -1 if it's empty. */
    private int first_horizontal(int row) {
        int j = 0;
        while (j < width && !data[row][j]) {
            ++j;
        }
        return j == width ? -1 : j;
    }
    /**
     * Returns index of first non-empty column in row 'row'
     * starting from column 'col' + 1. We assume that row 'row' is not empty.
     */
    private int next_horizontal(int row, int col) {
        do {
            col = (col + 1) % width;
        } while (!data[row][col]);
        return col;
    }



    /**
     * Sets 'up', 'down', 'head' attribute of each cell in a column 'col'
     * and a header of this column.
     */
    private void link_vertically(int col) {
        int u, d;       // two neighbouring cells of 1 moving down
        int first = first_vertical(col);

        Header colHead = headers[col];
        colHead.head = colHead;             // self-reference

        if (first == -1) {    // empty column
            colHead.down = colHead;
            colHead.up = colHead;
        } else {        // non-empty column
            u = first;
            d = next_vertical(col, u);

            while (d != first) {
                sparseData[col][u].down = sparseData[col][d];
                sparseData[col][d].up = sparseData[col][u];
                /* extra assignment w.r.t. link_horizontally: */
                sparseData[col][u].head = colHead;

                u = d;
                d = next_vertical(col, d);
            }
            // now 'd == first' and 'u' is the last non-empty cell in 'col'
            sparseData[col][u].head = colHead;
            sparseData[col][u].down = colHead;
            sparseData[col][first].up = colHead;
            colHead.down = sparseData[col][first];
            colHead.up = sparseData[col][u];
        }
    }

    /** Returns index of first non-empty row in column 'col' or -1 if it's empty. */
    private int first_vertical(int col) {
        int i = 0;
        while (i < height && !data[i][col]) {
            ++i;
        }
        return i == height ? -1 : i;
    }
    /**
     * Returns index of first non-empty row in column 'col'
     * starting from row 'row' + 1. We assume that column 'col' is not empty.
     */
    private int next_vertical(int row, int col) {
        do {
            row = (row + 1) % height;
        } while (!data[row][col]);
        return row;
    }

}

package sudoku;

/** This class implements Donald Knuth's Dancing Links algorithm. */
public class DancingLinks {
    private Solution solution = new Solution();
    @SuppressWarnings("FieldCanBeLocal")
    private Header column;
    @SuppressWarnings("FieldCanBeLocal")
    private Cell row;
    @SuppressWarnings("FieldCanBeLocal")
    private Cell col;

    private Header superHead;

    /** DancingLinks needs only a link to 'superHead' of sparse matrix. */
    public DancingLinks(Header superHead) {
        this.superHead = superHead;
    }

    /** Recursively add one row to solution at each step. */
    public void step() {
        column = choose_min_column();
        if (column == null) {              // success
            solution.save();
        } else if (column.count() > 0) {  // else solution doesn't exist

            column.remove();

            for (row = column.down; row != column; row = row.down) {
                solution.include(row);
                for (col = row.right; col != row; col = col.right) {
                    col.head.remove();
                }

                step();

                row = solution.remove();
                column = row.head;
                for (col = row.left; col != row; col = col.left) {
                    col.head.restore();
                }
            }

            column.restore();
        }

    }

    /** Chooses a column with smallest number of ones. (Knuth's heuristic) */
    private Header choose_min_column() {
        if (superHead == superHead.right) {     // empty matrix
            return null;
        } else {                                // choose min column
            int min = superHead.right.count;
            Header minH = superHead.right;
            for (Header h = superHead.right; h != superHead; h = h.right) {
                if (h.count < min) {
                    min = h.count;
                    minH = h;
                }
            }
            return minH;
        }
    }
}

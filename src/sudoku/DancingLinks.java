package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class DancingLinks {
    private Solution solution;
    private Header column;
    private Cell row;
    private Cell col;

    public void step() {
        column = choose_min_column();
        if (column == null) {              // success
            solution.save();
        } else if (column.count() == 0) {  // solution doesn't exist
            // KONIEC
        } else {                        // continue recursion
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

    private Header choose_min_column() {
        return null;
    }
}

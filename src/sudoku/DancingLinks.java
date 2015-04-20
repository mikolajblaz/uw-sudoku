package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class DancingLinks {
    private Solution solution;
    private Header column;
    private Cell row;
    private Cell col;

    public void step(int level) {
        Header column = choose_min_column();
        if (column == null) {              // success
            solution.save();
        } else if (column.count() == 0) {  // solution doesn't exist
            // KONIEC
        } else {                        // continue recursion
            column.remove_col_row();

            for (Cell row = column.down; row != column; row = row.down) {
                solution.include(row);
                for (Cell col = row.right; col != row; col = col.right) {
                    col.head.remove_col_row();
                }
                step(level + 1);
                for (Cell col = row.left; col != row; col = col.left) {
                    col.head.restore_col_row();
                }
                solution.remove(row);
            }

            column.restore_col_row();
        }

    }

    private Header choose_min_column() {
        return null;
    }
}

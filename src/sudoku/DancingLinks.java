package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class DancingLinks {
    Solution solution;
    public void step(int level) {
        Header col = choose_min_column();
        if (col == null) {              // success
            solution.save();
        } else if (col.count() == 0) {  // solution doesn't exist
            // KONIEC
        } else {                        // continue recursion
            col.remove_col_row();

            Cell firstRow = col.first();
            Cell row = firstRow;
            do {
                solution.include(row);
                row.remove_row_col_row();
                step(level + 1);
                row.recover_row_col_row();
                solution.remove(row);

                row = row.down();
            } while (row != firstRow);  // pełny obieg

            col.restore_col_row();
        }

    }

    private Header choose_min_column() {
        return null;
    }
}

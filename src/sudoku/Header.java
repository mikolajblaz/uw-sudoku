package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class Header {
    private Header left;
    private Header right;
    private Cell first;
    private int count = 0;

    public int count() {
        return count;
    }

    public Cell first() {
        return first;
    }

    public void hideOne() {
        count -= 1;
    }

    public void showOne() {
        count += 1;
    }


    public void remove_col_row() {////nininuygftrdhtrdhgrd
        if (count > 0) {
            Cell firstRow = first();        //// ????????
            Cell row = firstRow;
            do {
                row.remove_row();

                row = row.down();
            } while (row != firstRow);
        }

        hide();
    }

    public void restore_col_row() {
        if (count > 0) {
            Cell firstRow = first();        //// ????????
            Cell row = firstRow;
            do {
                row.restore_row();

                row = row.up();
            } while (row != firstRow);
        }

        show();
    }

    private void hide() {
        left.right = right;
        right.left = left;
    }

    private void show() {
        left.right = this;
        right.left = this;
    }
}

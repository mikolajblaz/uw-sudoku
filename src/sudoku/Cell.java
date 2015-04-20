package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class Cell {
    protected Cell left;
    protected Cell right;
    protected Cell up;
    protected Cell down;
    protected Header head;

    public Cell(Cell left, Cell right, Cell up, Cell down, Header head) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.head = head;
    }

    public Cell left() {
        return left;
    }
    public Cell right() {
        return right;
    }
    public Cell down() {
        return down;
    }
    public Cell up() {
        return up;
    }
    public Header head() {
        return head;
    }

    public void remove_row_col_row() {
        Cell firstCol = this;
        Cell col = firstCol;
        do {
            col.head.remove_col_row();

            col = col.right;
        } while (col != firstCol);
    }

    public void recover_row_col_row() {
        Cell firstCol = this;               ////// this.left()????????????? TODO
        Cell col = firstCol;
        do {
            col.head.restore_col_row();

            col = col.left;
        } while (col != firstCol);
    }


    public void hide_from_row() {
        left.right = right;
        right.left = left;
    }
    public void hide_from_col() {
        up.down = down;
        down.up = up;
        head.hideOne();
    }
    public void hide() {
        hide_from_row();
        hide_from_col();
    }


    public void show_to_row() {
        left.right = this;
        right.left = this;
    }
    public void show_to_col() {
        head.showOne();
        up.down = this;
        down.up = this;
    }
    public void show() {
        show_to_row();
        show_to_col();
    }
}

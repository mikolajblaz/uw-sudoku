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


    public void hide() {
        up.down = down;
        down.up = up;
        head.hideOne();
    }

    public void show() {
        head.showOne();
        up.down = this;
        down.up = this;
    }
}

package sudoku;

/**
 * Represents a cell contaning 1 in a sparse matrix representation.
 * Each cell has links to 4 neighbouring cells containing ones.
 * Cells are arranged in doubly-linked lists.
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

    /** Detaches cell from a column list. */
    public void hide() {
        up.down = down;
        down.up = up;
        head.hideOne();
    }

    /** Attaches cell back to a column list. */
    public void show() {
        head.showOne();
        up.down = this;
        down.up = this;
    }
}

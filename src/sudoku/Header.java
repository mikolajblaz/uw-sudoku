package sudoku;

/** Represents a header of each column in matrix. */
public class Header extends Cell {
    protected Header left;
    protected Header right;
    protected int count = 0;

    public Header(int rowNum, Header head) {
        super(rowNum, head);
    }

    public int count()    { return count; }
    public void hideOne() { count -= 1; }
    public void showOne() { count += 1; }

    /** Removes whole column along with rows having one in that column. */
    public void remove() {
        hide();
        for (Cell row = down; row != this; row = row.down) {
            for (Cell col = row.right; col != row; col = col.right) {
                col.hide();
            }
        }
    }

    /** Restores whole column along with rows having one in that column. */
    public void restore() {
        for (Cell row = up; row != this; row = row.up) {
            for (Cell col = row.left; col != row; col = col.left) {
                col.show();
            }
        }
        show();
    }

    /** Removes column from the list of columns. */
    public void hide() {
        left.right = right;
        right.left = left;
    }

    /** Adds column back to the list of columns. */
    public void show() {
        left.right = this;
        right.left = this;
    }
}

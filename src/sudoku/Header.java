package sudoku;

/**
 * Created by mikib on 20.04.15.
 */
public class Header extends Cell {
    protected Header left;
    protected Header right;
    protected int count = 0;

    public Header(Header left, Header right, Cell up, Cell down, Header head) {
        super(left, right, up, down, head);
        this.left = left;
        this.right = right;
    }

    public int count() {
        return count;
    }

    public void hideOne() {
        count -= 1;
    }

    public void showOne() {
        count += 1;
    }


    public void remove() {
        hide();
        for (Cell row = down; row != this; row = row.down) {
            for (Cell col = row.right; col != row; col = col.right) {
                col.hide();
            }
        }
    }

    public void restore() {
        for (Cell row = up; row != this; row = row.up) {
            for (Cell col = row.left; col != row; col = col.left) {
                col.show();
            }
        }
        show();
    }


    public void hide() {
        left.right = right;
        right.left = left;
    }

    public void show() {
        left.right = this;
        right.left = this;
    }
}

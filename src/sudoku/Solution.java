package sudoku;

import java.util.Stack;

/** Class storing solution to an exact cover problem. Contains a stack
  * with rows selected to form a solution to a problem. */
public class Solution {
    private Stack<Cell> rows = new Stack<>();

    public void save() {
    }

    public void include(Cell row) {
        rows.push(row);
    }

    public Cell remove() {
        return rows.pop();
    }
}

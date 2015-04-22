package sudoku;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** One solution to an exact cover problem as a list of rows in matrix. */
public class Solution {
    public List<Integer> rows = new LinkedList<>();

    public Solution(Stack<Cell> rowStack) {
        for (Cell c : rowStack) {
            rows.add(c.rowNum);
        }
    }
}
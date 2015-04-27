package sudoku;

/**
 * This class enables looping over rows while generating an exact cover matrix.
 * Looping schema:
 * for each column:
 *   for 0 to 1:
 *      for 0 to 'boardSize' - 1:
 *          return next row, for which matrix[row][col] should be one.
 *
 * see when matrix[row][col] should be 1:
 * http://en.wikipedia.org/wiki/Exact_cover#Sudoku
 */
class RowIterator {
    int next() {

        return 0;
    }
}

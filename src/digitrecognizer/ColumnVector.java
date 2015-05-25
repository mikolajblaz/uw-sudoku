package digitrecognizer;

/**
 * Specific matrix - a column vector. It doesn't extend Matrix class due to
 * efficiency reason.
 */
class ColumnVector {
    protected double[] data;
    protected int height;

    public ColumnVector(double[] data) {
        this.data = data;
        height = data.length;
    }

    /** Applies unary function f to all vector elements. */
    public void applyFunction(Function f) {
        for (int i = 0; i < height; i++) {
            data[i] = f.apply((data[i]));
        }
    }

    /** Adds 1 as a first element to the vector. */
    public void extendByOne() {
        //TODO
    }
}

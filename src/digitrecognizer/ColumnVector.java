package digitrecognizer;

/** Specific matrix - a column vector */
public class ColumnVector extends Matrix {
    protected double[] row_data;

    public ColumnVector(double[] row_data) {
        this.row_data = row_data;
        height = row_data.length;
        width = 1;
        this.data = new double[height][1];
        for (int i = 0; i < height; i++)
            data[i][0] = row_data[i];
    }
}

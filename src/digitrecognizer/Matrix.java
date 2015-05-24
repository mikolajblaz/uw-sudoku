package digitrecognizer;

import java.io.InputStream;
import java.util.Scanner;

/** Class representing matrices. */
public class Matrix {
    protected double[][] data;
    protected int width;
    protected int height;

    public Matrix(double[][] data) {
        this.data = data;
        height = data.length;
        width = data[0].length;
    }

    public Matrix(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        height = (new Double(in.nextDouble())).intValue();
        width = (new Double(in.nextDouble())).intValue();

        for (int i = 0 ; i < height; i++)
            for (int j = 0; j < width; j++)
                data[i][j] = in.nextDouble();
    }

    public double[][] getData() { return data; }

    public Matrix multiply(Matrix B) {
        if (width != B.height) {
            return null;
        } else {
            int sum;
            double C[][] = new double[height][B.width];
            {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < B.width; j++) {
                        sum = 0;
                        for (int k = 0; k < width; k++)
                            sum += data[i][k] * B.data[k][j];
                        C[i][j] = sum;
                    }
                }

            }
            return new Matrix(C);
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0 ; i < height; i++) {
            for (int j = 0; j < width; j++) {
                str.append(String.valueOf(data[i][j])).append("\t");
            }
            str.append("\n");
        }
        return str.toString();
    }
}

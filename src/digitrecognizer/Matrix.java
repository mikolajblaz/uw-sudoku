package digitrecognizer;

import java.io.BufferedReader;
import java.io.IOException;
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

    /** Reads a matrix from InputStream using Scanner. */
    public Matrix(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        height = (new Double(in.nextDouble())).intValue();
        width = (new Double(in.nextDouble())).intValue();

        for (int i = 0 ; i < height; i++)
            for (int j = 0; j < width; j++)
                data[i][j] = in.nextDouble();
    }

    /** Reads a matrix from an open BufferedReader without closing it. */
    public Matrix(BufferedReader reader)
            throws InvalidFileFormatException, IOException {
        String line;
        String line2;
        line = reader.readLine();
        line2 = reader.readLine();
        if (line == null || line2 == null) {
            throw new InvalidFileFormatException();
        } else {
            height = Double.valueOf(line).intValue();
            width = Double.valueOf(line2).intValue();

            for (int i = 0; i < height; i++) {
                line = reader.readLine();
                if (line == null) {
                    throw new InvalidFileFormatException();
                } else {
                    String[] numbers = line.split("\\s");
                    for (int j = 0; j < width; j++) {
                        data[i][j] = Double.valueOf(numbers[j]);
                    }
                }
            }
        }
    }

    public double[][] getData() { return data; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /** Multiplies itself by matrix B. Returns A*B. */
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

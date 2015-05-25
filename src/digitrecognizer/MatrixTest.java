package digitrecognizer;

import org.junit.Test;

import static org.junit.Assert.*;

/** Test Matrix class. */
public class MatrixTest {

    @Test
    public void testMultiply() throws Exception {
        double[][] a = {{1, 2},
                        {3, 4}};
        double[][] b = {{1, 1}};
        double[][] id = {{1, 0},
                        {0, 1}};

        double[][] c = {{4, 6}};   // == b * a

        Matrix A = new Matrix(a);
        Matrix B = new Matrix(b);
        Matrix C = new Matrix(c);
        Matrix Id = new Matrix(id);

        assertTrue(B.multiply(A).equals(C));
        assertTrue(B.multiply(Id).equals(B));
        assertTrue(C.multiply(Id).equals(C));
        assertTrue(A.multiply(Id).equals(A));
        assertTrue(Id.multiply(A).equals(A));
        assertTrue(Id.multiply(Id).equals(Id));
    }

    @Test
    public void testMultiply1() throws Exception {
        double[][] a = {{1, 2},
                        {3, 4}};
        double[][] id = {{1, 0},
                        {0, 1}};

        double[] v = {1, 1};
        double[] c = {3, 7};   // == a * v

        Matrix A = new Matrix(a);
        Matrix Id = new Matrix(id);
        ColumnVector V = new ColumnVector(v);
        ColumnVector C = new ColumnVector(c);


        assertTrue(A.multiply(V).equals(C));
        assertTrue(A.multiply(Id).equals(A));
        assertTrue(Id.multiply(C).equals(C));
        assertTrue(Id.multiply(V).equals(V));
        assertTrue(Id.multiply(A).equals(A));
        assertTrue(Id.multiply(Id).equals(Id));
    }

    @Test
    public void testApplyFunction() throws Exception {
        Function g = new SigmoidFunction();
        double[] probe = {-1, -0.5, 0, 0.5, 1};
        ColumnVector v = new ColumnVector(probe);
        v.applyFunction(g);
        double[] data = v.data;
        double[] result = {0.268941421, 0.377540669, 0.5, 0.622459331, 0.731058579};
        double epsilon = 0.0000001;
        for (int i = 0; i < result.length; i++)
            assertTrue(Math.abs(data[i] - result[i]) < epsilon);
    }

    @Test
    public void testExtend() throws Exception {
        double[] x = {-1, -0.5, 0, 0.5, 2};
        double[] ex = {1, -1, -0.5, 0, 0.5, 2};
        ColumnVector v = new ColumnVector(x);
        ColumnVector ev = new ColumnVector(ex);
        v.extendByOne();
        assertTrue(v.equals(ev));
    }
}
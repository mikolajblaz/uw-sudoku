package digitrecognizer;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/** Test Core class. */
public class CoreTest {
    private Core core;

    @Test
    public void testCoreConstructor() throws IOException, InvalidFileFormatException {
        core = new Core(new File("octave/network.txt"));
    }

    private void testOctaveCore(Core core) throws Exception {
        assertTrue(core.networkSize == 2);
        assertTrue(core.network[0].height == 25);
        assertTrue(core.network[0].width == 401);
        assertTrue(core.network[1].height == 10);
        assertTrue(core.network[1].width == 26);

        assertTrue(core.network[0].data[24][400] == -0.05846619554852237);
        assertTrue(core.network[1].data[9][25] == -2.378822418638552);
    }

    @Test
    public void testCore() throws Exception {
        testCoreConstructor();
        testOctaveCore(core);

    }

    @Test
    public void testFeedForward() throws Exception {
        Matrix X = new Matrix(new FileInputStream(new File("octave/data.txt")));
        Matrix y = new Matrix(new FileInputStream(new File("octave/results.txt")));
        int width = X.width;
        int height = X.height;
        assertTrue(width == 400 && height == 25);
        assertTrue(y.width == 1 && y.height == height);
        if (core == null)
            testCoreConstructor();

        ColumnVector[] inputs = new ColumnVector[height];
        int[] outputs = new int[height];
        int[] correct_outputs = new int[height];


        for (int i = 0; i < height; i++) {
            inputs[i] = new ColumnVector(X.data[i]);
            outputs[i] = core.chooseDigit(core.feedForward(inputs[i]), 0);
            correct_outputs[i] = (int) y.data[i][0];
            assertTrue(correct_outputs[i] == y.data[i][0] && correct_outputs[i] > 0);
            if (correct_outputs[i] == 10)
                correct_outputs[i] = 0;
        }

        int failures = 0;
        double max_failures_proc = 0.1;
        for (int i = 0; i < height; i++) {
            if (correct_outputs[i] != outputs[i])
                failures++;
        }
        assertTrue(failures / height < max_failures_proc);

    }
}
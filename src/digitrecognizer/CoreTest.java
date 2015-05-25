package digitrecognizer;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/** Test Core class. */
public class CoreTest {

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
        Core core = new Core(new File("../octave.txt"));
        testOctaveCore(core);

    }

    @Test
    public void testRecognizeDigit() throws Exception {
        assertTrue(false);
    }
}
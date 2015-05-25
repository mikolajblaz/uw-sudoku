package digitrecognizer;

import java.io.*;

/**
 * A core class of digit recognizer. Contains a neural network (read from file)
 * responsible for recognizing digits on the basis of 'size'x'size' images.
 */
public class Core {
    protected int imageSize;
    protected int networkSize;
    protected Matrix[] network;
    protected Function g = new SigmoidFunction();

    public Core(File file, int imageSize)
            throws InvalidFileFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            this.imageSize = imageSize;
            this.network = new Matrix[networkSize];
            String firstLine = reader.readLine();
            if (firstLine == null)
                throw new InvalidFileFormatException();
            networkSize = Double.valueOf(firstLine).intValue();

            /* read matrices */
            for (int i = 0; i < networkSize; i++) {
                network[i] = new Matrix(reader);
            }
            /* check file correctness: */
            if (network[0].height != imageSize * imageSize)
                throw new InvalidFileFormatException();
            /* check matrices dimensions */
            for (int i = 0; i < networkSize; i++) {
                if (network[0].width != network[1].height)
                    throw new InvalidFileFormatException();
            }
        }
    }

}

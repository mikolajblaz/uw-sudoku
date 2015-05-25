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
    protected Function activationFunction = new SigmoidFunction();

    /** Constructs neural network saved in a file 'file'. */
    public Core(File file) throws InvalidFileFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            loadMatrices(reader);
        }
    }

    /** Constructs neural network from InputStream. */
    public Core(InputStream stream) throws IOException, InvalidFileFormatException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            loadMatrices(reader);
        }
    }

    /** Constructs neural network from BufferedReader. */
    public Core(BufferedReader reader) throws IOException, InvalidFileFormatException {
        loadMatrices(reader);
    }

    /** Loads all matrices from the given BufferdReader. */
    private void loadMatrices(BufferedReader reader)
            throws IOException, InvalidFileFormatException {
        this.network = new Matrix[networkSize];
        String firstLine = reader.readLine();
        if (firstLine == null)
            throw new InvalidFileFormatException();
        networkSize = Double.valueOf(firstLine).intValue();

            /* read matrices */
        for (int i = 0; i < networkSize; i++) {
            network[i] = new Matrix(reader);
        }

            /* check matrices dimensions */
        for (int i = 0; i < networkSize; i++) {
            if (network[0].width != network[1].height)
                throw new InvalidFileFormatException();
        }

        this.imageSize = (int) Math.sqrt(network[0].height);
        if (imageSize * imageSize != network[0].height)
            throw new InvalidFileFormatException();
    }

    /**
     * Recognizes a digit given an array of pixel intensities between 0 and 1.
     * If network confidence is lower than 'threshold', returns -1 (unrecognized digit).
     * CAUTION! data must be an image saved in a format, where
     * data[0][0]       is an upper-left corner,
     * data[0][size]    is an upper-right corner,
     * data[size][0]    is a bottom-left corner,
     * data[size][size] is bottom-right corner
     */
    public int recognizeDigit(double[][] data, double threshold) throws InvalidImageFormatException {
        ColumnVector input = new ColumnVector(unrollInput(data));
        ColumnVector output = feedForward(input);

        return chooseDigit(output, threshold);
    }

    /**
     * Recognizes a digit given an array of pixel intensities between 0 and 1.
     * CAUTION! data must be an image saved in a format, where
     * data[0][0]       is an upper-left corner,
     * data[0][size]    is an upper-right corner,
     * data[size][0]    is a bottom-left corner,
     * data[size][size] is bottom-right corner
     */
    public int recognizeDigit(double[][] data) throws InvalidImageFormatException {
        return recognizeDigit(data, 0);
    }

    /**
     * Feeds forward input and returns the output of the network.
     * CAUTION! 'input' is modified!
     */
    private ColumnVector feedForward(ColumnVector input) {
        if (input.height != network[0].width) {
            return null;
        } else {
            for (int layer = 0; layer < networkSize; layer++) {
                input.extendByOne();
                input = network[layer].multiply(input);
                input.applyFunction(activationFunction);
            }
            return input;
        }
    }

    /** Unrolls 2D array to 1D array and checks image format. */
    private double[] unrollInput(double[][] data) throws InvalidImageFormatException {
        int width = data[0].length;
        if (width != imageSize)
            throw new InvalidImageFormatException();        // improper size

        double[] result = new double[data.length * width];
        int plain = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].length != width)
                throw new InvalidImageFormatException();    // irregular image
            for (int j = 0; j < width; j++) {
                result[plain++] = data[i][j];
            }
        }
        return result;
    }

    /**
     * Chooses a digit basing on the output of the neural network.
     *
     * @param output    neural network output
     * @param threshold number from range [0,1]. If network's confidence is
     *                  lower than threshold, this function returns -1
     * @return recognized digit or -1 if confidence is lower than threshold.
     */
    private int chooseDigit(ColumnVector output, double threshold) {
        double[] raw_output = output.data;
        int maxDigit = -1;
        double maxValue = threshold;
        for (int digit = 0; digit < output.height; digit++) {
            if (raw_output[digit] > maxValue) {
                maxDigit = digit;
                maxValue = raw_output[digit];
            }
        }
        return maxDigit;
    }
}
package plastic.services;

import java.util.Objects;
import java.util.Random;

public class Randomize {

    private final static String EMPTY_STRING = "";

    private final static int RANDOM_LOWER_BOUND = 0;

    private final Random r;

    public Randomize() {
        this.r = new Random();
    }

    private int[] randomize(int size, int upperBound) {
        return r.ints(size, RANDOM_LOWER_BOUND, upperBound).toArray();
    }

    private char [] fillArrayWithRandomChars(char [] output, int[] randomizedPositions, char [] availableValues) {
        for (int i = 0; i < output.length; i++ ) {
            output[i] = availableValues[randomizedPositions[i]];
        }
        return output;
    }

    private char [] randomCharArray(int size, char [] availableValues) {
        int[] randomizedPositions = randomize(size, availableValues.length);
        char [] output = new char [size];
        return fillArrayWithRandomChars(output, randomizedPositions, availableValues);
    }

    public String randomString(int size, char [] availableValues) {
        if (size <= 0 || Objects.isNull(availableValues) || availableValues.length == 0) {
            return EMPTY_STRING;
        }
        return new String(randomCharArray(size, availableValues));
    }

    public <T> T randomOf(T [] options) {
        return options[r.nextInt(options.length)];
    }
    
}

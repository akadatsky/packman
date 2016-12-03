package sample.util;

import java.util.Random;

public class Randomizer {

    private static final Random rand = new Random();

    public static synchronized int nextInt(int bound) {
        return rand.nextInt(bound);
    }

}

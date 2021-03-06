package PopulationSimulator.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Const class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 * <hr>
 * <h2>This class is the holder of all constants fields and methods that are helpfull for the whole project as utils static methods</h2>
 */
public class Const {
    private static final Random random = new Random();

    /**
     * <hr>
     * <h2>Generates a random int between 2 values</h2>
     * <hr>
     *
     * @param lowBound  Included, lower bound of the random number
     * @param highBound Excluded, higer bound of the random number
     * @return A random int between lowBound (included) and highBound (excluded)
     */
    public static int randBetween(int lowBound, int highBound) {
        if (highBound <= lowBound) throw new IllegalArgumentException("Forbidden params : highBound) <= lowBound");

        return random.nextInt(highBound - lowBound) + lowBound;
    }

    /**
     * <hr>
     * <h2>Generates a random int between center +/- delta  </h2>
     * <hr>
     *
     * @param center Central value, middle of the rand numbers generated
     * @param delta  Min and max bounds around the center value
     * @return A random int between center +/- delta
     */
    public static int randDelta(int center, int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta param is negative");

        return randBetween(center - delta, center + delta);
    }

    /**
     * <hr>
     * <h2>Tests if [string] can be parsed into an Integer</h2>
     * <hr>
     *
     * @param string String to test
     * @return True if [string] can be parsed into an Integer
     */
    public static boolean isInteger(@NotNull String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

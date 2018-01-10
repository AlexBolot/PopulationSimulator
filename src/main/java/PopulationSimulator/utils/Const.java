package PopulationSimulator.utils;

import java.util.Random;

public class Const
{
    private static final Random random = new Random();

    /**
     <hr>
     <h2>Generates a random int between lowBound (included) and highBound (excluded)</h2>
     <h3>randNum is in range of : [lowBound : highBound[</h3>
     <hr>

     @param lowBound  Included lower bound of the random number
     @param highBound Excluded higer bound of the random number
     @return A random int between lowBound (included) and highBound (excluded)
     */
    public static int randBetween (int lowBound, int highBound)
    {
        return random.nextInt(highBound - lowBound) + lowBound;
    }
}

package PopulationSimulator.utils;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Const class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 22:03
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>This class is the holder of all constants fields and methods that are helpfull for the whole project as utils static methods</h2>
 */
public class Const
{
    private static final Random random = new Random();

    /**
     <hr>
     <h2>Generates a random int between 2 values</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param lowBound  Included, lower bound of the random number
     @param highBound Excluded, higer bound of the random number
     @return A random int between lowBound (included) and highBound (excluded)
     */
    public static int randBetween (int lowBound, int highBound)
    {
        if (highBound <= lowBound) throw new IllegalArgumentException("Forbidden params : highBound) <= lowBound");

        return random.nextInt(highBound - lowBound) + lowBound;
    }

    /**
     <hr>
     <h2>Generates a random int between center +/- delta  </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 13/01 <br>
     Modified : Alexandre Bolot 13/01
     </h3>
     <hr>

     @param center Central value, middle of the rand numbers generated
     @param delta  Min and max bounds around the center value
     @return A random int between center +/- delta
     */
    public static int randDelta (int center, int delta)
    {
        if (delta < 0) throw new IllegalArgumentException("Delta param is negative");

        return randBetween(center - delta, center + delta);
    }
}

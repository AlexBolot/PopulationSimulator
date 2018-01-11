package PopulationSimulator.utils;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Const class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 23:28
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
        return random.nextInt(highBound - lowBound) + lowBound;
    }
}

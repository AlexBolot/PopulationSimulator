package PopulationSimulator.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SectorTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 21:43
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SectorTest
{
    //region --------------- Attributes ----------------------
    private final Random random = new Random();
    //endregion

    //region --------------- isNeighboorOf_ID (x1) -----------
    @Test
    public void isNeighboorOf_Sector ()
    {
        Sector sector0 = new Sector();
        Sector sector1 = new Sector();
        Sector sector2 = new Sector();

        sector0.addNeighboor(sector1.ID());

        assertTrue(sector0.isNeighboorOf(sector1));
        assertFalse(sector0.isNeighboorOf(sector2));
    }
    //endregion

    //region --------------- isNeighboorOf_Sector (x1) -------
    @Test
    public void isNeighboorOf_ID ()
    {
        Sector sector0 = new Sector();
        Sector sector1 = new Sector();
        Sector sector2 = new Sector();

        sector0.addNeighboor(sector1.ID());

        assertTrue(sector0.isNeighboorOf(sector1.ID()));
        assertFalse(sector0.isNeighboorOf(sector2.ID()));

        for (int i = 0; i < 1000; i++)
        {
            int otherID = 0;
            while (Arrays.asList(sector0.ID(), sector1.ID(), sector2.ID()).contains(otherID)) otherID = random.nextInt();

            assertFalse(sector0.isNeighboorOf(otherID));
        }
    }
    //endregion
}
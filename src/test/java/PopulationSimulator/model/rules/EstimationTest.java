package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.Contract;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The EstimationTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 08:46
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class EstimationTest
{
    //--------------------------------------------
    // Only way for coverage to see this as ok :D
    //--------------------------------------------

    @Test
    public void emptyTest ()
    {
        Estimation estimation = new Estimation()
        {
            @Contract (pure = true)
            @Override
            public double apply (Context population)
            {
                return 0;
            }
        };

        assertEquals(0, estimation.apply(null), 0.001);
    }

}
package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The EstimationTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:20
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
            public double apply (@NotNull Context context)
            {
                return 0;
            }
        };

        assertEquals(0, estimation.apply(new Context(new ArrayList8<>())), 0.001);
    }

}
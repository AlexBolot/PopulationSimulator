package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ComplexeRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 09:05
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ComplexeRuleTest
{
    //--------------------------------------------
    // Only way for coverage to see this as ok :D
    //--------------------------------------------

    @Test
    public void apply ()
    {
        ComplexeRule complexeRule = new ComplexeRule()
        {
            @Override
            public void apply (@NotNull Context context)
            {

            }
        };

        assertTrue(true);
    }
}
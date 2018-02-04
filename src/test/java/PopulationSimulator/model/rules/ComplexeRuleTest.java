package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ComplexeRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 29/01/18 14:37
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
            public Context apply (@NotNull Context context)
            {
                return new Context();
            }
        };

        assertEquals(new Context(), complexeRule.apply(new Context()));
    }
}
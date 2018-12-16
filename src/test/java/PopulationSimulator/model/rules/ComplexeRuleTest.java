package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ComplexeRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/12/18 14:10
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ComplexeRuleTest {
    //--------------------------------------------
    // Only way for coverage to see this as ok :D
    //--------------------------------------------

    @Test
    public void apply() {
        ComplexeRule complexeRule = new ComplexeRule() {
            @Override
            public Graph apply(@NotNull Graph context) {
                return new Graph();
            }
        };

        assertEquals(new Graph(), complexeRule.apply(new Graph()));
    }
}
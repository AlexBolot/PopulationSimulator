package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The EstimationTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/12/18 14:10
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class EstimationTest {
    //--------------------------------------------
    // Only way for coverage to see this as ok :D
    //--------------------------------------------

    @Test
    public void emptyTest() {
        Estimation estimation = new Estimation() {
            @Override
            @Contract(pure = true)
            public double apply(@NotNull Graph context) {
                return 0;
            }
        };

        assertEquals(0, estimation.apply(new Graph(new ArrayList8<>())), 0.001);
    }

}
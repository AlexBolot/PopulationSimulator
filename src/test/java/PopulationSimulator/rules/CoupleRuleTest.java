package PopulationSimulator.rules;

import PopulationSimulator.utils.Const;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 22:22
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class CoupleRuleTest
{
    private Random random = new Random();
    private int minimumAge;

    @Before
    public void before ()
    {
        minimumAge = Const.randBetween(18, 25);
    }

    @Test
    public void t ()
    {

    }
}
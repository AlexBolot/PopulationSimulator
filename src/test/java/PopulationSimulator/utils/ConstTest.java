package PopulationSimulator.utils;

import org.jetbrains.annotations.Contract;
import org.junit.Test;

import java.util.Random;

import static PopulationSimulator.utils.Const.randBetween;
import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ConstTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/12/18 14:10
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ConstTest {
    //region --------------- Attributes ----------------------
    private final Random random = new Random();
    //endregion

    //region --------------- randBetween (x3) ----------------
    @Test
    public void randBetween_Right() {
        for (int i = 0; i < 1000; i++) {
            int minBound = random.nextInt() / 3;
            int maxBound = random.nextInt() / 3;

            while (maxBound <= minBound) maxBound = random.nextInt() / 3;

            int randVal = randBetween(minBound, maxBound);

            assertTrue(minBound <= randVal);
            assertTrue(randVal < maxBound);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void randBetween_InvertedBounds() {
        for (int i = 0; i < 1000; i++) {
            int minBound = random.nextInt() / 3;
            int maxBound = random.nextInt() / 3;

            while (maxBound >= minBound) maxBound = random.nextInt() / 3;

            randBetween(minBound, maxBound);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void randBetween_EqualsBounds() {
        for (int i = 0; i < 1000; i++) {
            int minBound = random.nextInt() / 3;
            randBetween(minBound, minBound);
        }
    }
    //endregion

    //region --------------- randDelta (x3) ------------------
    @Test
    public void randDelta_Right() {
        for (int i = 0; i < 1000; i++) {
            int center = random.nextInt() / 3;
            int delta = Math.abs(random.nextInt() / 3);

            int randVal = randDelta(center, delta);

            assertTrue(center - delta <= randVal);
            assertTrue(randVal < center + delta);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void randDelta_NegativeDelta() {
        for (int i = 0; i < 1000; i++) {
            int center = random.nextInt() / 3;
            int delta = negativeOf(random.nextInt() / 3);

            randDelta(center, delta);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void randDelta_ZeroCenterAndDelta() {
        int center = 0;
        int delta = 0;

        randDelta(center, delta);
    }
    //endregion

    //region --------------- OtherMethods --------------------
    @Contract(pure = true)
    private int negativeOf(int val) {
        return (val >= 0) ? -val : val;
    }
    //endregion
}
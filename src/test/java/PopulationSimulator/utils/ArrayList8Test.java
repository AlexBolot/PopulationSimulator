package PopulationSimulator.utils;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randDelta;
import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ArrayList8Test class was coded by : Alexandre BOLOT
 .
 . Last modified : 29/01/18 00:13
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ({"Duplicates", "MismatchedQueryAndUpdateOfCollection", "ResultOfMethodCallIgnored"})
public class ArrayList8Test
{
    //region --------------- Attributes ----------------------
    private Predicate<TestObject> positive = testObject -> testObject.val1 > 0 && testObject.val2 > 0 && testObject.val3 > 0;

    private Comparator<TestObject> comparator = (testObject1, testObject2) -> {
        if (testObject1.val1 > testObject2.val1) return 1;
        if (testObject1.val1 < testObject2.val1) return -1;

        if (testObject1.val2 > testObject2.val2) return 1;
        if (testObject1.val2 < testObject2.val2) return -1;

        return Integer.compare(testObject1.val3, testObject2.val3);
    };
    //endregion

    //region --------------- getRandom (x2) ------------------
    @Test
    public void getRandom_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            assertTrue(list.contains(list.getRandom()));

            list.clear();
        }

        //----------------------------//

        list.clear();

        TestObject testObject1 = randTestObject();
        list.add(testObject1);

        assertEquals(testObject1, list.getRandom());

        //----------------------------//

        TestObject testObject2 = randTestObject();
        list.add(testObject2);

        TestObject random = list.getRandom();

        boolean got1 = random.equals(testObject1);
        boolean got2 = random.equals(testObject2);

        assertTrue(got1 || got2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getRandom_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.getRandom();
    }
    //endregion

    //region --------------- removeRandom (x2) ---------------
    @Test
    public void removeRandom_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);
            }

            int size = list.size();
            TestObject testObject = list.removeRandom();

            assertFalse(list.contains(testObject));
            assertEquals(size - 1, list.size());

            list.clear();
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeRandom_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.removeRandom();
    }
    //endregion

    //region --------------- merge (x3) ----------------------
    @Test
    public void merge_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            ArrayList8<TestObject> list1 = new ArrayList8<TestObject>()
            {{
                IntStream.range(0, randDelta(30, 10)).forEach(i -> add(randTestObject()));
            }};

            ArrayList8<TestObject> list2 = new ArrayList8<TestObject>()
            {{
                IntStream.range(0, randDelta(30, 10)).forEach(i -> add(randTestObject()));
            }};

            int prevSize1 = list1.size();
            int prevSize2 = list2.size();

            ArrayList8<TestObject> merge = list1.merge(list2);

            assertEquals(list1, merge);
            assertEquals(prevSize1 + prevSize2, list1.size());
            assertEquals(prevSize1 + prevSize2, merge.size());
        }
    }

    @Test
    public void merge_Empty ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();
        ArrayList8<TestObject> list2 = new ArrayList8<>();
        ArrayList8<TestObject> merge = list1.merge(list2);

        assertTrue(merge.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void merge_Null ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();
        list1.merge(null);
    }
    //endregion

    //region --------------- addIf (x4) ----------------------
    @Test
    public void addIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.addIf(testObject, positive);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.size());

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.countIf(positive));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addIf_NullValue ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(null, positive);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(randTestObject(), null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addIf_NullBoth ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addIf(null, null);
    }
    //endregion

    //region --------------- addAllIf (x4) -------------------
    @Test

    public void addAllIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        ArrayList8<TestObject> tmpList = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 50; j++)
            {
                for (int k = 0; k < 10; k++)
                {
                    TestObject testObject = randTestObject();
                    tmpList.add(testObject);
                    if (positive.test(testObject)) count++;
                }

                list.addAllIf(tmpList, positive);

                tmpList.clear();
            }

            assertEquals(count, list.size());

            list.clear();
            count = 0;
        }

        list.clear();
        list.addAllIf(new ArrayList8<>(), positive);
        assertEquals(0, list.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAllIf_NullValue ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(null, positive);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAllIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(new ArrayList8<>(), null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAllIf_NullBoth ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.addAllIf(null, null);
    }
    //endregion

    //region --------------- contains (x3) -------------------
    @Test
    public void contains_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        boolean foundPositive = false;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                list.add(testObject);

                if (positive.test(testObject)) foundPositive = true;
            }

            assertEquals(foundPositive, list.contains(positive));

            list.clear();
        }
    }

    @Test
    public void contains_Empty ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        assertFalse(list.contains(positive));
    }

    @Test (expected = IllegalArgumentException.class)
    public void contains_NullPredicate ()
    {
        Predicate<TestObject> predicate = null;

        new ArrayList8<TestObject>().contains(predicate);
    }
    //endregion

    //region --------------- countIf (x2) --------------------
    @Test
    public void countIf_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.countIf(positive));

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.countIf(positive));
    }

    @Test (expected = IllegalArgumentException.class)
    public void countIf_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.countIf(null);
    }
    //endregion

    //region --------------- sublist (x2) --------------------
    @Test
    public void sublist_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        int count = 0;

        for (int i = 0; i < 2000; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();
                list.add(testObject);

                if (positive.test(testObject)) count++;
            }

            assertEquals(count, list.subList(positive).size());

            list.clear();
            count = 0;
        }

        list.clear();
        assertEquals(0, list.subList(positive).size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void sublist_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.subList(null);
    }
    //endregion

    //region --------------- findAny (x2) --------------------
    @Test
    public void findAny_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            IntStream.range(0, 500).mapToObj(j -> randTestObject()).forEach(list::add);

            Optional<TestObject> any1 = list.stream().filter(positive).findAny();
            Optional<TestObject> any2 = list.findAny(positive);

            if (any1.isPresent() && any2.isPresent()) assertEquals(any1.get(), any2.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void findAny_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.findAny(null);
    }
    //endregion

    //region --------------- findFirst (x2) ------------------
    @Test
    public void findFirst_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            IntStream.range(0, 500).mapToObj(j -> randTestObject()).forEach(list::add);

            Optional<TestObject> first1 = list.stream().filter(positive).findFirst();
            Optional<TestObject> first2 = list.findFirst(positive);

            if (first1.isPresent() && first2.isPresent()) assertEquals(first1.get(), first2.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void findFirst_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.findFirst(null);
    }
    //endregion

    //region --------------- max (x2) ------------------------
    @Test
    public void max_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            TestObject max = null;

            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                if (max == null || comparator.compare(testObject, max) > 0) max = testObject;

                list.add(testObject);
            }

            Optional<TestObject> optMax = list.max(comparator);

            if (optMax.isPresent()) assertEquals(max, optMax.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void max_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.max(null);
    }
    //endregion

    //region --------------- min (x2) ------------------------
    @Test
    public void min_Right ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();

        for (int i = 0; i < 2000; i++)
        {
            TestObject min = null;

            for (int j = 0; j < 500; j++)
            {
                TestObject testObject = randTestObject();

                if (min == null || comparator.compare(testObject, min) < 0) min = testObject;

                list.add(testObject);
            }

            Optional<TestObject> optMin = list.min(comparator);

            if (optMin.isPresent()) assertEquals(min, optMin.get());
            else fail("any not found");

            list.clear();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void min_NullPredicate ()
    {
        ArrayList8<TestObject> list = new ArrayList8<>();
        list.min(null);
    }
    //endregion

    //region --------------- reduce (x3) ---------------------
    @Test
    public void reduce_Right ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<TestObject>()
        {
            {
                IntStream.range(0, randDelta(10, 5)).forEach(i -> add(randTestObject()));
            }
        };

        int sumVal1 = list1.stream().mapToInt(t -> t.val1).sum();

        Optional<TestObject> opt = list1.reduce((o1, o2) -> new TestObject(o1.val1 + o2.val1, 0, 0));

        assertTrue(opt.isPresent());

        assertEquals(sumVal1, opt.get().val1);
    }

    @Test
    public void reduce_Empty ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();

        Optional<TestObject> opt = list1.reduce((o1, o2) -> new TestObject(o1.val1 + o2.val1, o1.val2 + o2.val2, o1.val3 + o2.val3));

        assertFalse(opt.isPresent());
    }

    @Test (expected = IllegalArgumentException.class)
    public void reduce_Null ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();

        list1.reduce(null);
    }
    //endregion

    //region --------------- map (x3) ------------------------
    @Test
    public void map_Right ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<TestObject>()
        {{
            IntStream.range(0, randDelta(10, 5)).forEach(i -> add(randTestObject()));
        }};

        ArrayList8<Integer> collect = list1.map(testObject -> testObject.val1).collect(toCollection(ArrayList8::new));

        IntStream.range(0, list1.size()).forEach(i -> assertEquals(list1.get(i).val1, collect.get(i), 0.0001));
    }

    @Test
    public void map_Empty ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();

        ArrayList8<Integer> collect = list1.map(testObject -> testObject.val1).collect(toCollection(ArrayList8::new));

        IntStream.range(0, list1.size()).forEach(i -> assertEquals(list1.get(i).val1, collect.get(i), 0.0001));
    }

    @Test (expected = IllegalArgumentException.class)
    public void map_Null ()
    {
        ArrayList8<TestObject> list1 = new ArrayList8<>();

        list1.map(null);
    }
    //endregion

    //region --------------- OtherMethods --------------------
    @NotNull
    private TestObject randTestObject ()
    {
        int val1 = ThreadLocalRandom.current().nextInt();
        int val2 = ThreadLocalRandom.current().nextInt();
        int val3 = ThreadLocalRandom.current().nextInt();

        return new TestObject(val1, val2, val3);
    }
    //endregion

    //region --------------- Private classes -----------------
    private class TestObject
    {
        int val1;
        int val2;
        int val3;

        TestObject (int val1, int val2, int val3)
        {
            this.val1 = val1;
            this.val2 = val2;
            this.val3 = val3;
        }
    }
    //endregion
}
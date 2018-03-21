package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import PopulationSimulator.utils.Const;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.model.rules.RulesTestingUtils.createPerson;
import static PopulationSimulator.utils.Const.randBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 20/03/18 18:38
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class CoupleRuleTest
{
    //region --------------- Attributes ----------------------
    private int                minimumAge;
    private CoupleRule         coupleRule;
    private ArrayList8<Person> people;
    //endregion

    //region --------------- SetUps --------------------------

    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        minimumAge = Const.randBetween(18, 25);
        coupleRule = new CoupleRule(minimumAge);
    }
    //endregion

    //region --------------- apply (x3) ----------------------

    @Test
    public void apply_Right ()
    {
        int tooYoung = minimumAge - randBetween(1, 5);
        int oldEnough = minimumAge + randBetween(1, 5);

        people = new ArrayList8<Person>()
        {{
            IntStream.range(0, 10).forEach(j -> {
                //Will generate 60 couples
                add(createPerson(oldEnough, Male, Bi));
                add(createPerson(oldEnough, Male, Homo));
                add(createPerson(oldEnough, Male, Hetero));
                add(createPerson(oldEnough, Female, Hetero));
                add(createPerson(oldEnough, Female, Homo));
                add(createPerson(oldEnough, Female, Bi));

                //Will generate 0 couples
                add(createPerson(tooYoung, Male, Bi));
                add(createPerson(tooYoung, Male, Homo));
                add(createPerson(tooYoung, Male, Hetero));
                add(createPerson(tooYoung, Female, Bi));
                add(createPerson(tooYoung, Female, Homo));
                add(createPerson(tooYoung, Female, Hetero));
            });
        }};

        Graph context = new Graph(people.mapAndCollect(Node::new), new ArrayList8<>());

        int newSize = context.merge(coupleRule.apply(context)).edges().size();

        assertEquals(60, newSize);
    }

    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        Graph context = new Graph(people.mapAndCollect(Node::new));

        int newSize = context.merge(coupleRule.apply(context)).edges().size();

        assertEquals(0, newSize);
    }

    @Test (expected = IllegalArgumentException.class)
    public void apply_Null ()
    {
        Graph context = null;

        coupleRule.apply(context);
    }
    //endregion
}
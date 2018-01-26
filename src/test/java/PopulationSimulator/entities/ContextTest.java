package PopulationSimulator.entities;

import PopulationSimulator.model.factories.PersonFactory;
import PopulationSimulator.model.rules.CoupleRule;
import PopulationSimulator.utils.ArrayList8;
import org.junit.Test;

import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ContextTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 09:34
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ContextTest
{
    @Test
    public void merge_Right ()
    {
        ArrayList8<Person> people1 = new ArrayList8<Person>()
        {{
            IntStream.range(0, randDelta(30, 10)).mapToObj(i -> PersonFactory.createPerson()).forEach(this::add);
        }};
        ArrayList8<Person> people2 = new ArrayList8<Person>()
        {{
            IntStream.range(0, randDelta(30, 10)).mapToObj(i -> PersonFactory.createPerson()).forEach(this::add);
        }};

        Context context1 = new Context(people1);
        Context context2 = new Context(people2);

        CoupleRule coupleRule = new CoupleRule();
        coupleRule.apply(context1);
        coupleRule.apply(context2);

        int countPeople1 = context1.people().size();
        int countPeople2 = context2.people().size();

        int countRelations1 = context1.relations().size();
        int countRelations2 = context2.relations().size();

        context1.merge(context2);

        assertEquals(countPeople1 + countPeople2, context1.people().size());
        assertEquals(countRelations1 + countRelations2, context1.relations().size());
    }
}
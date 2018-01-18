package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;

import java.util.LinkedHashSet;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface PersonFinder
{
    LinkedHashSet<Person> find (Person person, Context context);

    LinkedHashSet<Person> merge (LinkedHashSet<Person> people);
}

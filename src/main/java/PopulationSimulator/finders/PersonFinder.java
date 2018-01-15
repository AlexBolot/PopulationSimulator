package PopulationSimulator.finders;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Population;

import java.util.LinkedHashSet;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 23:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface PersonFinder
{
    LinkedHashSet<Person> find (Person person, Population population);

    LinkedHashSet<Person> merge (LinkedHashSet<Person> people);
}

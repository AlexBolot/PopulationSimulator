package PopulationSimulator.finders;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.Relation;

import java.util.LinkedHashSet;
import java.util.Optional;

import static PopulationSimulator.entities.enums.RelationType.Couple;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 23:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PartenerFinder implements PersonFinder
{
    private LinkedHashSet<Person> hashSet = new LinkedHashSet<>();

    @Override
    public LinkedHashSet<Person> find (Person person, Population population)
    {
        for (Relation relation : population.relations())
        {
            if (!relation.type().equals(Couple)) continue;

            Optional<Person> otherPerson = relation.getOther(person);

            if (!otherPerson.isPresent()) continue;

            hashSet.add(otherPerson.get());

            return hashSet;
        }

        return hashSet;
    }

    @Override
    public LinkedHashSet<Person> merge (LinkedHashSet<Person> people)
    {
        hashSet.addAll(people);

        return hashSet;
    }
}

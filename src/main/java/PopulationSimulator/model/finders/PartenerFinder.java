package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.Relation;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

import static PopulationSimulator.entities.enums.RelationType.Couple;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 17/01/18 00:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PartenerFinder implements PersonFinder
{
    private LinkedHashSet<Person> hashSet = new LinkedHashSet<>();

    public LinkedHashSet<Person> getHashSet () { return hashSet; }

    @Override
    public LinkedHashSet<Person> find (Person person, Population population)
    {
        Objects.requireNonNull(person, "Person param is null");
        Objects.requireNonNull(population, "Population param is null");

        hashSet = new LinkedHashSet<>();

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
        Objects.requireNonNull(people, "People param is null");

        hashSet.addAll(people);

        return hashSet;
    }
}

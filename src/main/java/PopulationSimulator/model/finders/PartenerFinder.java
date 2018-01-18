package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
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
 . Last modified : 18/01/18 22:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PartenerFinder implements PersonFinder
{
    private LinkedHashSet<Person> hashSet = new LinkedHashSet<>();

    public LinkedHashSet<Person> getHashSet () { return hashSet; }

    @Override
    public LinkedHashSet<Person> find (Person person, Context context)
    {
        Objects.requireNonNull(person, "Person param is null");
        Objects.requireNonNull(context, "Context param is null");

        hashSet = new LinkedHashSet<>();

        for (Relation relation : context.relations())
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

package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.ArrayList8;

import java.util.Objects;
import java.util.Optional;

import static PopulationSimulator.entities.enums.RelationType.Couple;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 21:57
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PartenerFinder implements PersonFinder
{
    //region --------------- Attributes ----------------------
    private ArrayList8<Person> people = new ArrayList8<>();

    public ArrayList8<Person> people () { return people; }
    //endregion

    //region --------------- Methods -------------------------
    @Override
    public ArrayList8<Person> find (Person person, Context context)
    {
        Objects.requireNonNull(person, "Person param is null");
        Objects.requireNonNull(context, "Context param is null");

        people = new ArrayList8<>();

        for (Relation relation : context.relations())
        {
            if (!relation.type().equals(Couple)) continue;

            Optional<Person> otherPerson = relation.getOther(person);

            if (!otherPerson.isPresent()) continue;

            people.add(otherPerson.get());

            return people;
        }

        return people;
    }

    @Override
    public ArrayList8<Person> merge (ArrayList8<Person> people)
    {
        Objects.requireNonNull(people, "People param is null");

        this.people.addAll(people);

        return this.people;
    }
    //endregion
}

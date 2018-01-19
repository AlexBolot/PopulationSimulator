package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

import static PopulationSimulator.entities.enums.RelationType.Couple;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PartenerFinder implements PersonFinder
{
    //region --------------- Attributes ----------------------
    private ArrayList8<Person> people = new ArrayList8<>();

    public ArrayList8<Person> people () { return people; }
    //endregion

    //region --------------- Methods -------------------------
    @Override
    public ArrayList8<Person> find (@NotNull Person person, @NotNull Context context)
    {
        //region --> Check params
        if (person == null) throw new IllegalArgumentException("Person param is null");
        if (context == null) throw new IllegalArgumentException("Context param is null");
        //endregion

        people = new ArrayList8<>();

        for (Relation relation : context.relations().subList(r -> r.type() == Couple))
        {
            Optional<Person> otherPerson = relation.getOther(person);

            if (!otherPerson.isPresent()) continue;

            people.add(otherPerson.get());

            return people;
        }

        return people;
    }

    @Override
    public ArrayList8<Person> merge (@NotNull ArrayList8<Person> people)
    {
        //region --> Check params
        Objects.requireNonNull(people, "People param is null");
        //endregion

        this.people.addAll(people);

        return this.people;
    }
    //endregion
}

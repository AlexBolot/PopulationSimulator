package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static PopulationSimulator.entities.enums.RelationType.Couple;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:15
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PartenerFinder implements PersonFinder
{
    //region --------------- Attributes ----------------------
    private ArrayList8<Person> people = new ArrayList8<>();
    //endregion

    //region --------------- Getters and Setters -------------
    @NotNull
    public ArrayList8<Person> people () { return people; }
    //endregion

    //region --------------- Methods -------------------------
    @Override
    @NotNull
    public ArrayList8<Person> find (@NotNull Person person, @NotNull Context context)
    {
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
    @NotNull
    public ArrayList8<Person> merge (@NotNull ArrayList8<Person> people)
    {
        this.people.addAll(people);

        return this.people;
    }
    //endregion
}

package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface PersonFinder
{
    ArrayList8<Person> find (@NotNull Person person, @NotNull Context context);

    ArrayList8<Person> merge (@NotNull ArrayList8<Person> people);
}

package PopulationSimulator.model.finders;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonFinder class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface PersonFinder
{
    @NotNull ArrayList8<Person> find (@NotNull Person person, @NotNull Context context);

    @NotNull ArrayList8<Person> merge (@NotNull ArrayList8<Person> people);
}

package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ApplyableDouble class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:20
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface ApplyableDouble
{
    double apply (@NotNull Context context);
}

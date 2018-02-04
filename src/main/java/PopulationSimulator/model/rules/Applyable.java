package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Applyable class was coded by : Alexandre BOLOT
 .
 . Last modified : 29/01/18 14:27
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface Applyable
{
    Context apply (@NotNull Context context);
}

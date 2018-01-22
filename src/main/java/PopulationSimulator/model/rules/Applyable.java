package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Applyable class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface Applyable
{
    void apply (@NotNull Context context);
}

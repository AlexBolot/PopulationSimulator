package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ApplyableDouble class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/03/18 17:01
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface ApplyableDouble
{
    double apply (@NotNull Graph context);
}

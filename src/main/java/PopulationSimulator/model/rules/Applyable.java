package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Applyable class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/03/18 15:32
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface Applyable
{
    Graph apply (@NotNull Graph context);
}

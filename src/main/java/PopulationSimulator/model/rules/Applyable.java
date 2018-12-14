package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Applyable class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public interface Applyable {
    Graph apply(@NotNull Graph context);
}

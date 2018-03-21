package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ComplexeRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/03/18 15:32
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public abstract class ComplexeRule implements Applyable {

    public abstract Graph apply (@NotNull Graph context);
}

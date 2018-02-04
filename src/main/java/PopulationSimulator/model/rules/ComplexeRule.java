package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ComplexeRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/01/18 02:29
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public abstract class ComplexeRule implements Applyable {

    public abstract Context apply (@NotNull Context context);
}

package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*
 * ................................................................................................................................
 *  . Copyright (c)
 *  .
 *  . The ComplexeRule class was coded by : Gregoire PELTIER
 *  .
 *  . Last modified : ComplexeRule.java
 *  .
 *  . Contact : idevedit@gmail.com
 *  ...............................................................................................................................
 *
 */

public abstract class ComplexeRule implements Applyable {

    public abstract void apply(@NotNull Context context);
}

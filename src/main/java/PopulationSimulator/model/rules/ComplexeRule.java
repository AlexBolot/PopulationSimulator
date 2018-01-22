package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Population;

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
    public abstract void apply(Population population);
}

package PopulationSimulator.rules;

import PopulationSimulator.entities.Population;

import java.util.Objects;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LifespanRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 03:23
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Removes the Poeple from the population if they reached a certain age</h2>
 <hr>
 */
public class LifespanRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private int maxLifespan;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of LifespanRule : sets maxLifespan at 80</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    public LifespanRule () { this(80); }

    /**
     <hr>
     <h2>Constructor of LifespanRule using maxLifespan param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param maxLifespan Maximum that a Perso can reach before death
     */
    public LifespanRule (int maxLifespan) { this.maxLifespan = maxLifespan; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Population param</h2>
     <h3>A Person dies if it's age is over or equals to maxLifespan <br>
     </h3>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param population Population to apply this rule onto
     */
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        population.people().removeIf(person -> person.data().age() >= maxLifespan);

        population.relations().removeIf(relation -> {
            boolean notContainsP1 = !population.people().contains(relation.person1());
            boolean notContainsP2 = !population.people().contains(relation.person2());

            return notContainsP1 || notContainsP2;
        });
    }
    //endregion
}

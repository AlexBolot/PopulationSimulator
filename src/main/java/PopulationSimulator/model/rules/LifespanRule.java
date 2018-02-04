package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LifespanRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 04/02/18 22:33
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Removes the Poeple from the context if they reached a certain age</h2>
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
     */
    public LifespanRule () { this(80); }

    /**
     <hr>
     <h2>Constructor of LifespanRule using maxLifespan param</h2>
     <hr>

     @param maxLifespan Maximum that a Perso can reach before death
     */
    public LifespanRule (int maxLifespan)
    {
        //region --> Check params
        if (maxLifespan <= 0) throw new IllegalArgumentException("MaxLifespan can't be negative or zero");
        //endregion

        this.maxLifespan = maxLifespan;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Context param</h2>
     <h3>A Person dies if it's age is over or equals to maxLifespan <br>
     </h3>
     <hr>

     @param context Context to apply this rule onto
     */
    public Context apply (@NotNull Context context)
    {
        context.people().removeIf(person -> person.data().age() >= maxLifespan);

        context.relations().removeIf(relation -> {
            boolean notContainsP1 = !context.people().contains(relation.person1());
            boolean notContainsP2 = !context.people().contains(relation.person2());

            return notContainsP1 || notContainsP2;
        });

        //return empty context since we added no item
        //TODO : find a way to take note of the people + relations removed
        return new Context();
    }
    //endregion
}

package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LifespanRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Removes the Poeple from the context if they reached a certain age</h2>
 <h3>After the person reaches [maxLifespan], it has more and more chances to die every year until reaching [maxLifespan]+[shadin]</h3>
 <hr>
 */
public class LifespanRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private int maxLifespan;
    private int shading;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of LifespanRule : sets maxLifespan at 80</h2>
     <hr>
     */
    public LifespanRule() {
        this(80, 20);
    }

    /**
     <hr>
     <h2>Constructor of LifespanRule using maxLifespan param</h2>
     <hr>

     @param maxLifespan Age after which the chances of death start appearing and increasing
     @param shading     Delay from [maxLifespan] to a 100% chance of death
     */
    public LifespanRule(int maxLifespan, int shading)
    {
        if (maxLifespan <= 0) throw new IllegalArgumentException("MaxLifespan can't be negative or zero");
        if (shading < 0) throw new IllegalArgumentException("Shaing can't be negative");

        this.maxLifespan = maxLifespan;
        this.shading = shading;
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
    public Graph apply (@NotNull Graph context)
    {
        ArrayList8<Node<Person>> oldPeople = context.getNodesContaining(Person.class)
                .mapAndCollect(node -> new Node<>((Person) node.value()))
                .subList(node -> node.value().getAge() >= maxLifespan);

        oldPeople.forEach(node -> {
            int age = node.value().getAge();
            int chances = ((age - maxLifespan) * 50 / shading) + 50;
            int score = new Random().nextInt(100);

            if (score < chances) context.remove(node);
        });

        //return empty context since we added no item
        //TODO : find a way to take note of the people + relations removed
        return new Graph();
    }
    //endregion
}
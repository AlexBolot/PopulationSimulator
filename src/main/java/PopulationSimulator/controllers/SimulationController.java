package PopulationSimulator.controllers;

import PopulationSimulator.entities.Context;
import PopulationSimulator.model.rules.Applyable;
import org.jetbrains.annotations.Contract;

import java.util.HashSet;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController
{
    //region --------------- Attributes ----------------------
    private static int currentTime = 0;

    private Context            context;
    private HashSet<Applyable> rules;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Simple constructor of SimulationController</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 11/01
     </h3>
     <hr>

     @param context Context (people + relations) to work on for the simulation
     @param rules      Rules to apply on the context when to simulation starts
     */
    public SimulationController (Context context, HashSet<Applyable> rules)
    {
        this.context = context;
        this.rules = rules;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @Contract (pure = true)
    public static int currentTime () { return currentTime; }

    public Context population () { return context; }

    public HashSet<Applyable> rules () { return rules; }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Will apply every Rule on the Context as many times as they are ticks</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>

     @param ticks Numbers of "turns" to do before stopping the simulation
     */
    public void simulate (int ticks)
    {
        System.out.println("---------- year " + currentTime + " ----------\n");

        while (currentTime != ticks)
        {
            rules.forEach(rule -> rule.apply(context));

            boolean hasPeople = !context.people().isEmpty();
            boolean hasRelations = !context.relations().isEmpty();

            if (hasPeople)
            {
                context.people().forEach(System.out::println);
                System.out.println();
            }

            if (hasRelations)
            {
                context.relations().forEach(System.out::println);
                System.out.println();
            }

            if (hasPeople || hasRelations)
            {
                System.out.println("---------- year " + currentTime + " ----------");
                System.out.println();
            }

            currentTime++;
        }
    }
    //endregion
}

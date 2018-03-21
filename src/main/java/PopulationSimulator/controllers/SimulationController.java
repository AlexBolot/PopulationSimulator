package PopulationSimulator.controllers;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.rules.Applyable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/03/18 21:18
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController
{
    //region --------------- Attributes ----------------------
    private static int currentTime = 0;

    private Graph                 context;
    private ArrayList8<Applyable> rules;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Simple constructor of SimulationController</h2>
     <hr>

     @param rules      Applyables to apply on each tick of simulation
     @param context      Graph with People and their connections, to start with
     */
    public SimulationController (@NotNull ArrayList8<Applyable> rules, @NotNull Graph context)
    {
        this.rules = rules;
        this.context = context;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @Contract (pure = true)
    public static int currentTime () { return currentTime; }

    @NotNull
    public Graph graph () { return context; }

    @NotNull
    public ArrayList8<Applyable> rules () { return rules; }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Will apply every Rule on the Context as many times as they are ticks</h2>
     <hr>

     @param ticks Numbers of "turns" to do before stopping the simulation
     */
    public void simulate (int ticks)
    {
        /*while (currentTime != ticks)
        {
            Graph tmpGraph = new Graph();

            rules.forEach(rule -> tmpGraph.merge(rule.apply(context)));

            visualizer.addTurn(tmpGraph);

            String turnTitle = "---------- Turn " + currentTime + "----------";

            if (context.hasPeople())
            {
                log(turnTitle, PeopeLogFile);
                context.people().forEach(p -> log(p.toString(), PeopeLogFile));
            }

            if (context.hasRelations())
            {
                log(turnTitle, RelationsLogFile);
                context.relations().forEach(r -> log(r.toString(), RelationsLogFile));
            }

            if (context.hasLocations())
            {
                log(turnTitle, SectorsLogFile);
                log(context.locations().toString(), SectorsLogFile);
            }

            currentTime++;
        }

        visualizer.printStats();*/
    }
    //endregion
}

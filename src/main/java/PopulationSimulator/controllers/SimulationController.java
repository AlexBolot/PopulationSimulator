package PopulationSimulator.controllers;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Context;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.visualizer.cli.Visualizer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.visualizer.Logger.LogFile.*;
import static PopulationSimulator.visualizer.Logger.log;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController
{
    //region --------------- Attributes ----------------------
    private static int currentTime = 0;

    private Context               context;
    private Visualizer            visualizer;
    private ArrayList8<Applyable> rules;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Simple constructor of SimulationController</h2>
     <hr>

     @param rules      Applyables to apply on each tick of simulation
     @param context    Context (people, relations, sectors) to start with
     @param visualizer Visualizer to feed with data on each tick, to print stats in the end
     */
    public SimulationController (@NotNull ArrayList8<Applyable> rules, @NotNull Context context, @NotNull Visualizer visualizer)
    {
        this.rules = rules;
        this.context = context;
        this.visualizer = visualizer;

        visualizer.addTurn(new Context().merge(context));
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @Contract (pure = true)
    public static int currentTime () { return currentTime; }

    @NotNull
    public Context context () { return context; }

    @NotNull
    public ArrayList8<Applyable> rules () { return rules; }

    @NotNull
    public Visualizer visualizer () { return visualizer; }
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
        while (currentTime != ticks)
        {
            Context tmpContext = new Context();

            rules.forEach(rule -> tmpContext.merge(rule.apply(context)));

            visualizer.addTurn(tmpContext);

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

        visualizer.printStats();
    }
    //endregion
}

package PopulationSimulator.controllers;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.visualizer.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.visualizer.Logger.LogFile.EdgesLogFile;
import static PopulationSimulator.visualizer.Logger.LogFile.NodesLogFile;
import static PopulationSimulator.visualizer.Logger.clearLogs;
import static PopulationSimulator.visualizer.Logger.log;
import static java.lang.System.currentTimeMillis;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:23
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

     @param rules   Applyables to apply on each tick of simulation
     @param context Graph with People and their connections, to start with
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
        clearLogs(Logger.LogFile.values());

        long millis = currentTimeMillis();

        while (currentTime != ticks)
        {
            Graph tmpGraph = new Graph();

            rules.forEach(rule -> tmpGraph.merge(rule.apply(context)));

            String turnTitle = "---------- Turn " + currentTime + "\t" + ((currentTimeMillis() - millis)) + "----------";

            millis = currentTimeMillis();

            System.out.println(turnTitle);

            if (!context.nodes().isEmpty())
            {
                log(turnTitle, NodesLogFile);
                context.nodes().forEach(node -> log(node.toString(), NodesLogFile));
                context.nodes().forEach(node -> log(node.toString(), NodesLogFile));
            }

            if (!context.edges().isEmpty())
            {
                log(turnTitle, EdgesLogFile);
                context.edges().forEach(edge -> log(edge.toString(), EdgesLogFile));
            }

            currentTime++;
        }
    }
    //endregion
}

package PopulationSimulator.controllers;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.enums.EdgeType;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.visualizer.DotGenerator;
import PopulationSimulator.visualizer.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static PopulationSimulator.visualizer.Logger.clearLogs;
import static java.lang.System.currentTimeMillis;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 13:17
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController {
    //region --------------- Attributes ----------------------
    private static int currentTime = 0;

    private Graph context;
    private ArrayList8<Applyable> rules;
    private DotGenerator generator;

    //endregion

    //region --------------- Constructors --------------------

    /**
     * <hr>
     * <h2>Simple constructor of SimulationController</h2>
     * <hr>
     *
     * @param rules   Applyables to apply on each tick of simulation
     * @param context Graph with People and their connections, to start with
     */
    public SimulationController(@NotNull ArrayList8<Applyable> rules, @NotNull Graph context) {
        this.rules = rules;
        this.context = context;

        try {
            this.generator = new DotGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @Contract(pure = true)
    public static int currentTime() {
        return currentTime;
    }

    @NotNull
    public Graph graph() {
        return context;
    }

    @NotNull
    public ArrayList8<Applyable> rules() {
        return rules;
    }
    //endregion

    //region --------------- Methods -------------------------

    /**
     * <hr>
     * <h2>Will apply every Rule on the Context as many times as they are ticks</h2>
     * <hr>
     *
     * @param ticks Numbers of "turns" to do before stopping the simulation
     */
    public void simulate(int ticks, EdgeType type) throws IOException, InterruptedException {
        clearLogs(Logger.LogFile.values());

        long millis = currentTimeMillis();

        while (currentTime != ticks) {
            Graph tmpGraph = new Graph();

            rules.forEach(rule -> tmpGraph.merge(rule.apply(context)));

            String turnTitle = "---------- Turn " + currentTime + "\t" + ((currentTimeMillis() - millis)) + "----------";

            millis = currentTimeMillis();

            System.out.println(turnTitle);

            generator.produceFrom(tmpGraph, type);
            generator.generate(String.valueOf(currentTime), false);

            currentTime++;
        }
    }
    //endregion
}

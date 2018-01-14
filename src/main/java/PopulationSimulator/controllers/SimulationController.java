package PopulationSimulator.controllers;

import PopulationSimulator.entities.Population;
import PopulationSimulator.rules.Applyable;

import java.util.HashSet;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 03:32
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController
{
    //region --------------- Attributes ----------------------
    private static int currentTime = 0;

    private Population         population;
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

     @param population Population (people + relations) to work on for the simulation
     @param rules      Rules to apply on the population when to simulation starts
     */
    public SimulationController (Population population, HashSet<Applyable> rules)
    {
        this.population = population;
        this.rules = rules;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public static int currentTime ()
    {
        return currentTime;
    }

    public Population population ()
    {
        return population;
    }

    public HashSet<Applyable> rules ()
    {
        return rules;
    }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Will apply every Rule on the Population as many times as they are ticks</h2>
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
            rules.forEach(rule -> rule.apply(population));

            boolean hasPeople = !population.people().isEmpty();
            boolean hasRelations = !population.relations().isEmpty();

            if (hasPeople)
            {
                population.people().forEach(System.out::println);
                System.out.println();
            }

            if (hasRelations)
            {
                population.relations().forEach(System.out::println);
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

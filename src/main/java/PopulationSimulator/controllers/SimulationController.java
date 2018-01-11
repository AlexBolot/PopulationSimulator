package PopulationSimulator.controllers;

import PopulationSimulator.entities.Population;
import PopulationSimulator.rules.Applyable;

import java.util.HashSet;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SimulationController class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 23:34
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class SimulationController
{
    //region --------------- Attributes ----------------------
    public static int currentTime = 0;

    private Population         population;
    private HashSet<Applyable> rules;
    //endregion

    //region --------------- Constructors --------------------
    public SimulationController (Population population, HashSet<Applyable> rules)
    {
        this.population = population;
        this.rules = rules;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Population getPopulation ()
    {
        return population;
    }

    public HashSet<Applyable> getRules ()
    {
        return rules;
    }
    //endregion

    //region --------------- Methods -------------------------
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

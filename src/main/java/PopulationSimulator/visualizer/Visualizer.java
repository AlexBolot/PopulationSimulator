package PopulationSimulator.visualizer;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.RelationType;
import PopulationSimulator.utils.ArrayList8;
import PopulationSimulator.visualizer.stats.PeopleStats;
import PopulationSimulator.visualizer.stats.RelationsStats;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Visualizer class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:15
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Visualizer
{
    private PeopleStats         peopleStats;
    private RelationsStats      relationsStats;
    private ArrayList8<Context> turns;

    public Visualizer ()
    {
        this.turns = new ArrayList8<>();
        this.peopleStats = new PeopleStats();
        this.relationsStats = new RelationsStats();
    }

    public void addTurn (@NotNull Context context)
    {
        turns.add(context);
        peopleStats.add(context.people());
        relationsStats.add(context.relations());
    }

    @NotNull
    public PeopleStats peopleStats ()
    {
        return peopleStats;
    }

    @NotNull
    public RelationsStats relationsStats ()
    {
        return relationsStats;
    }

    @NotNull
    public ArrayList8<Relation> getAllRelations ()
    {
        return turns.map(Context::relations).reduce(ArrayList8::merge).orElse(new ArrayList8<>());
    }

    @NotNull
    public ArrayList8<Relation> getRelationByType (@NotNull RelationType type)
    {
        return getAllRelations().subList(relation -> relation.type() == type);
    }
}
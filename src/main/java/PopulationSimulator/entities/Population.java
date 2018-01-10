package PopulationSimulator.entities;

import java.util.LinkedHashSet;

public class Population
{
    //region --------------- Attributes ----------------------
    private LinkedHashSet<Person>   people;
    private LinkedHashSet<Relation> relations;
    //endregion

    //region --------------- Constructors --------------------
    public Population (LinkedHashSet<Person> people)
    {
        this.people = people;
        this.relations = new LinkedHashSet<>();
    }

    public Population (LinkedHashSet<Person> people, LinkedHashSet<Relation> relations)
    {
        this.people = people;
        this.relations = relations;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public LinkedHashSet<Person> people ()
    {
        return people;
    }

    public void setPeople (LinkedHashSet<Person> people)
    {
        this.people = people;
    }

    public LinkedHashSet<Relation> relations ()
    {
        return relations;
    }

    public void setRelations (LinkedHashSet<Relation> relations)
    {
        this.relations = relations;
    }
    //endregion
}

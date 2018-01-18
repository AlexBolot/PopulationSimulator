package PopulationSimulator.entities;

import PopulationSimulator.model.Sector;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Population class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:33
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Population
{
    //region --------------- Attributes ----------------------
    private final LinkedHashSet<Person>         people;
    private final LinkedHashSet<Sector>         sectors;
    private final LinkedHashSet<Relation>       relations;
    private final LinkedHashMap<Person, Sector> locations;
    //endregion

    //region --------------- Constructors --------------------
    public Population (LinkedHashSet<Person> people)
    {
        this(people, new LinkedHashSet<>());
    }

    public Population (LinkedHashSet<Person> people, LinkedHashSet<Relation> relations)
    {
        this.people = people;
        this.relations = relations;

        Sector sector = new Sector();

        this.sectors = new LinkedHashSet<>(Collections.singletonList(sector));
        this.locations = new LinkedHashMap<>();

        this.people.forEach(person -> locations.put(person, sector));
    }

    public Population (LinkedHashSet<Person> people, LinkedHashSet<Relation> relations, LinkedHashSet<Sector> sectors,
                       LinkedHashMap<Person, Sector> locations)
    {
        this.people = people;
        this.relations = relations;
        this.sectors = sectors;
        this.locations = locations;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public LinkedHashSet<Person> people () { return people; }

    public LinkedHashSet<Relation> relations () { return relations; }

    public LinkedHashMap<Person, Sector> locations () { return locations; }

    public LinkedHashSet<Sector> sectors () { return sectors; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Population + foreach(Person) in people + foreach(Relation) in relations</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Relation + person1.ID + person2.ID + type + duration
     */
    @Override
    public String toString ()
    {
        StringBuilder str = new StringBuilder();

        str.append(getClass().getSimpleName()).append(" :\n");
        str.append("  People : ");

        people.forEach(person -> str.append("    ").append(person).append("\n"));

        str.append("\n  Relations : ");

        relations.forEach(relation -> str.append("    ").append(relation).append("\n"));

        return str.toString();
    }

    /**
     <hr>
     <h2>Compares : people and relations</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param obj The Object to compare with this
     @return True if obj is equal to this, False otherwise
     */
    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        Population popCmp = (Population) obj;

        return people.equals(popCmp.people) && relations.equals(popCmp.relations);
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Unique HashCode for this Population instance <br>
     Based on : people, relations
     */
    @Override
    public int hashCode () { return Objects.hash(people, relations); }

    //endregion
}

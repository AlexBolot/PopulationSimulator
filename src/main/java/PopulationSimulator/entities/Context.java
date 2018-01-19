package PopulationSimulator.entities;

import PopulationSimulator.model.Sector;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Context class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:28
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class Context
{
    //region --------------- Attributes ----------------------
    private final ArrayList8<Person>            people;
    private final ArrayList8<Sector>            sectors;
    private final ArrayList8<Relation>          relations;
    private final LinkedHashMap<Person, Sector> locations;
    //endregion

    //region --------------- Constructors --------------------
    public Context (@NotNull ArrayList8<Person> people)
    {
        this(people, new ArrayList8<>());
    }

    public Context (@NotNull ArrayList8<Person> people, @NotNull ArrayList8<Relation> relations)
    {
        //region --> Check params
        if (people == null) throw new IllegalArgumentException("People param is null");
        if (relations == null) throw new IllegalArgumentException("Relations param is null");
        //endregion

        this.people = people;
        this.relations = relations;

        Sector sector = new Sector();

        this.sectors = new ArrayList8<>(Collections.singletonList(sector));
        this.locations = new LinkedHashMap<>();

        this.people.forEach(person -> locations.put(person, sector));
    }

    public Context (@NotNull ArrayList8<Person> people, @NotNull ArrayList8<Relation> relations, @NotNull ArrayList8<Sector> sectors,
                    @NotNull LinkedHashMap<Person, Sector> locations)
    {
        //region --> Check params
        if (people == null) throw new IllegalArgumentException("People param is null");
        if (sectors == null) throw new IllegalArgumentException("Sectors param is null");
        if (relations == null) throw new IllegalArgumentException("Relations param is null");
        if (locations == null) throw new IllegalArgumentException("Locations param is null");
        //endregion

        this.people = people;
        this.sectors = sectors;
        this.relations = relations;
        this.locations = locations;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public ArrayList8<Person> people () { return people; }

    public ArrayList8<Relation> relations () { return relations; }

    public LinkedHashMap<Person, Sector> locations () { return locations; }

    public ArrayList8<Sector> sectors () { return sectors; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Context + foreach(Person) in people + foreach(Relation) in relations</h2>
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

        Context popCmp = (Context) obj;

        return people.equals(popCmp.people) && relations.equals(popCmp.relations);
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Unique HashCode for this Context instance <br>
     Based on : people, relations
     */
    @Override
    public int hashCode () { return Objects.hash(people, relations); }
    //endregion
}

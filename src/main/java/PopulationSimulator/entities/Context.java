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
 . Last modified : 04/02/18 22:26
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

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
        this.people = people;
        this.sectors = sectors;
        this.relations = relations;
        this.locations = locations;
    }

    public Context ()
    {
        this(new ArrayList8<>(), new ArrayList8<>());
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @NotNull
    public ArrayList8<Person> people () { return people; }

    @NotNull
    public ArrayList8<Relation> relations () { return relations; }

    @NotNull
    public LinkedHashMap<Person, Sector> locations () { return locations; }

    @NotNull
    public ArrayList8<Sector> sectors () { return sectors; }

    public boolean hasPeople () { return !people.isEmpty(); }

    public boolean hasRelations () { return !relations.isEmpty(); }

    public boolean hasLocations () { return !locations.isEmpty(); }

    public boolean hasSectors () { return !sectors.isEmpty(); }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Merging the context param to this one</h2>
     <hr>

     @param context Other context to merge with this
     */
    @NotNull
    public Context merge (@NotNull Context context)
    {
        people.addAllIf(context.people, p -> !people.contains(p));
        sectors.addAllIf(context.sectors, s -> !sectors.contains(s));
        relations.addAllIf(context.relations, r -> !relations.contains(r));
        locations.putAll(context.locations);

        return this;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Context + foreach(Person) in people + foreach(Relation) in relations</h2>
     <hr>

     @return Relation + person1.ID + person2.ID + type + duration
     */
    @Override
    @NotNull
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

     @param obj The Object to compare with this
     @return True if obj is equal to this, False otherwise
     */
    @Override
    public boolean equals (@NotNull Object obj)
    {
        if (!getClass().isInstance(obj)) return false;

        Context popCmp = (Context) obj;

        return people.equals(popCmp.people) && relations.equals(popCmp.relations);
    }

    /**
     <hr>
     <h2>Returns a unique HashCode for this Context instance <br>
     Based on : people, relations</h2>
     <hr>

     @return A unique HashCode for this Context instance <br>
     Based on : people, relations
     */
    @Override
    public int hashCode () { return Objects.hash(people, relations); }
    //endregion
}

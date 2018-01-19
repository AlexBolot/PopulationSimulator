package PopulationSimulator.entities;

import PopulationSimulator.entities.enums.RelationType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

import static PopulationSimulator.controllers.SimulationController.currentTime;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Relation class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class Relation
{
    //region --------------- Attributes ----------------------
    private int          beginning;
    private Person       person1;
    private Person       person2;
    private RelationType type;
    //endregion

    //region --------------- Constructors --------------------
    public Relation (Person person1, Person person2, RelationType type)
    {
        this(person1, person2, type, currentTime());
    }

    public Relation (@NotNull Person person1, @NotNull Person person2, @NotNull RelationType type, int beginning)
    {
        //region --> Check params
        if (person1 == null) throw new IllegalArgumentException("Person1 param is null");
        if (person2 == null) throw new IllegalArgumentException("Person2 param is null");
        if (type == null) throw new IllegalArgumentException("Type param is null");
        //endregion

        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
        this.beginning = beginning;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Person person1 () { return person1; }

    public Person person2 () { return person2; }

    public boolean involves (@NotNull Person person)
    {
        //region --> Check params
        if (person == null) throw new IllegalArgumentException("Person param is null");
        //endregion

        return person1.equals(person) || person2.equals(person);
    }

    public RelationType type () { return type; }

    public int beginning () { return beginning; }

    public int getDuration () { return currentTime() - beginning; }

    public Optional<Person> getOther (@NotNull Person person)
    {
        //region --> Check params
        if (person == null) throw new IllegalArgumentException("Person param is null");
        //endregion

        if (person.equals(person1)) return Optional.of(person2);
        if (person.equals(person2)) return Optional.of(person1);
        return Optional.empty();
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Relation + person1.ID + person2.ID + type + duration</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Relation + person1.ID + person2.ID + type + duration
     */
    @Override
    public String toString ()
    {
        return String.format("Relation {%s-%s}{type:%s}{duration:%d}", person1.ID(), person2.ID(), type(), getDuration());
    }

    /**
     <hr>
     <h2>Compares : type, beginning, person1 and person2 (symetrics included)</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
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

        Relation relCmp = (Relation) obj;

        if (!type.equals(relCmp.type)) return false;
        if (beginning != relCmp.beginning) return false;

        boolean straightCompare = person1.equals(relCmp.person1) && person2.equals(relCmp.person2);
        boolean crossedCompare = person1.equals(relCmp.person2) && person2.equals(relCmp.person1);

        return straightCompare || crossedCompare;
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Unique HashCode for this Relation instance <br>
     Based on : person1, person2, type, beginning
     */
    @Override
    public int hashCode () { return Objects.hash(person1, person2, type, beginning); }
    //endregion
}

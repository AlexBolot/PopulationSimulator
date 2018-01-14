package PopulationSimulator.entities;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.enums.RelationType;

import java.util.Objects;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Relation class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 03:00
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

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
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
        this.beginning = SimulationController.currentTime();
    }

    public Relation (Person person1, Person person2, RelationType type, int beginning)
    {
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
        this.beginning = beginning;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Person person1 () { return person1; }

    public Person person2 () { return person2; }

    public boolean involves (Person person) { return person1.equals(person) || person2.equals(person); }

    public RelationType type () { return type; }

    public int beginning () { return beginning; }

    public int getDuration () { return SimulationController.currentTime() - beginning; }
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

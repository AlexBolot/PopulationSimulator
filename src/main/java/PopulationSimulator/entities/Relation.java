package PopulationSimulator.entities;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.enums.RelationType;

import java.util.Objects;

public class Relation
{
    //region --------------- Attributes ----------------------
    private int          beginnig;
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
        this.beginnig = SimulationController.currentTime;
    }

    public Relation (Person person1, Person person2, RelationType type, int beginnig)
    {
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
        this.beginnig = beginnig;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Person person1 () { return person1; }

    public Person person2 () { return person2; }

    public boolean involves (Person person) { return person1.equals(person) || person2.equals(person); }

    public RelationType getType () { return type; }

    public int getBeginnig () { return beginnig; }

    public int getDuration () { return SimulationController.currentTime - beginnig; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return String.format("Relation {%s-%s}{type:%s}{duration:%d}",
                             person1.data().getName(),
                             person2.data().getName(),
                             getType(),
                             getDuration());
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        Relation relCmp = (Relation) obj;

        if (!type.equals(relCmp.type)) return false;
        if (beginnig != relCmp.beginnig) return false;

        boolean straightCompare = person1.equals(relCmp.person1) && person2.equals(relCmp.person2);
        boolean crossedCompare = person1.equals(relCmp.person2) && person2.equals(relCmp.person1);

        return straightCompare || crossedCompare;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hashCode(person1) + Objects.hashCode(person2) + Objects.hashCode(type) + Objects.hashCode(beginnig);
    }

    //endregion
}

package PopulationSimulator.entities;

import PopulationSimulator.controllers.Controller;
import PopulationSimulator.entities.enums.RelationType;

public class Relation
{
    //region --------------- Attributes ----------------------
    private Person       person1;
    private Person       person2;
    private RelationType type;
    private int          beginnig;
    //endregion

    //region --------------- Constructors --------------------
    public Relation (Person person1, Person person2, RelationType type)
    {
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
        this.beginnig = Controller.currentTime;
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
    public Person getPerson1 () { return person1; }

    public void setPerson1 (Person person1) { this.person1 = person1; }

    public Person getPerson2 () { return person2; }

    public void setPerson2 (Person person2) { this.person2 = person2; }

    public boolean involves (Person person) { return person1.equals(person) || person2.equals(person); }

    public RelationType getType () { return type; }

    public void setType (RelationType type) { this.type = type; }

    public int getBeginnig () { return beginnig; }

    public void setBeginnig (int beginnig) { this.beginnig = beginnig; }

    public int getDuration () { return Controller.currentTime - beginnig; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return String.format("Relation {%s-%s}{type:%s}}{duration:%d",
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

    //endregion
}

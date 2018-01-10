package PopulationSimulator.rules;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;

import java.util.Objects;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.RelationType.Couple;
import static PopulationSimulator.entities.enums.SexualOrientation.*;

public class CoupleRule extends SimpleRule
{
    private static final int anyAge = -1;
    //region --------------- Attributes ----------------------
    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------
    public CoupleRule () { this(anyAge); }

    public CoupleRule (int minimumAge) { this.minimumAge = minimumAge; }
    //endregion

    //region --------------- Override ------------------------
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        for (Person person1 : population.people())
        {
            if (minimumAge != anyAge && person1.data().getAge() < minimumAge) continue;
            if (population.relations().stream().anyMatch(relation -> relation.involves(person1))) continue;

            for (Person person2 : population.people())
            {
                if (person2.equals(person1)) continue;
                if (minimumAge != anyAge && person2.data().getAge() < minimumAge) continue;
                if (population.relations().stream().anyMatch(relation -> relation.involves(person2))) continue;

                if (isMatch(person1, person2))
                {
                    Relation relation = new Relation(person1, person2, Couple, SimulationController.currentTime);
                    population.relations().add(relation);
                    break;
                }
            }
        }
    }
    //endregion

    //region --------------- Methods -------------------------
    private boolean isMatch (Person p1, Person p2)
    {
        SexualOrientation ori1 = p1.data().getOrientation();
        SexualOrientation ori2 = p2.data().getOrientation();

        Gender gen1 = p1.data().getGender();
        Gender gen2 = p2.data().getGender();

        if (ori1.equals(Bi))
        {
            if (ori2.equals(Bi)) return true;

            if (ori2.equals(Hetero)) return gen1.equals(Male) && gen2.equals(Female) || gen1.equals(Female) && gen2.equals(Male);
            if (ori2.equals(Homo)) return gen1.equals(Male) && gen2.equals(Male) || gen1.equals(Female) && gen2.equals(Female);
        }

        if (ori1.equals(Hetero) && gen1.equals(Male)) return gen2.equals(Female) && (ori2.equals(Hetero) || ori2.equals(Bi));
        if (ori1.equals(Hetero) && gen1.equals(Female)) return gen2.equals(Male) && (ori2.equals(Hetero) || ori2.equals(Bi));

        if (ori1.equals(Homo) && gen1.equals(Male)) return gen2.equals(Male) && (ori2.equals(Homo) || ori2.equals(Bi));
        if (ori1.equals(Homo) && gen1.equals(Female)) return gen2.equals(Female) && (ori2.equals(Homo) || ori2.equals(Bi));

        return false;
    }
    //endregion
}

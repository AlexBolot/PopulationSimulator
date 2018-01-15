package PopulationSimulator.model.rules;

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

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 15/01/18 13:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Creates Couple relationships within the population</h2>
 <hr>
 */
public class CoupleRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of CoupleRule : sets minimumAge at -1</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    public CoupleRule () { this(anyAge); }

    /**
     <hr>
     <h2>Constructor of CoupleRule using minimumAge param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param minimumAge Minimum age to create a couple relationship with any other Person
     */
    public CoupleRule (int minimumAge) { this.minimumAge = minimumAge; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Population param</h2>
     <h3>
     — A Person that didn't reach minium age can't get in couple <br>
     — A Person that already is in couple, can't get in couple with someone else <br>
     — 2 People have to <code>match</code> to get in couple <br>
     See {@link CoupleRule#isMatch(Person, Person)}</h3>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param population Population to apply this rule onto
     */
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        for (Person person1 : population.people())
        {
            if (minimumAge != anyAge && person1.data().age() < minimumAge) continue;
            if (population.relations().stream().anyMatch(relation -> relation.involves(person1))) continue;

            for (Person person2 : population.people())
            {
                if (person2.equals(person1)) continue;
                if (minimumAge != anyAge && person2.data().age() < minimumAge) continue;
                if (population.relations().stream().anyMatch(relation -> relation.involves(person2))) continue;

                if (isMatch(person1, person2))
                {
                    Relation relation = new Relation(person1, person2, Couple, SimulationController.currentTime());
                    population.relations().add(relation);
                    break;
                }
            }
        }
    }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Checks if 2 people match each other</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param p1 Person to match with p2
     @param p2 Person to match with p1
     @return True if p1 matches p2 (and reciprocally), False otherwise
     */
    private boolean isMatch (Person p1, Person p2)
    {
        Objects.requireNonNull(p1, "Person1 param is null");
        Objects.requireNonNull(p2, "Person2 param is null");

        SexualOrientation ori1 = p1.data().orientation();
        SexualOrientation ori2 = p2.data().orientation();

        Gender gen1 = p1.data().gender();
        Gender gen2 = p2.data().gender();

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

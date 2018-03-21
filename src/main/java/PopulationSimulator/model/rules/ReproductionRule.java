package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.model.graph.Edge;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static PopulationSimulator.model.graph.EdgeType.Couple;
import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 21/03/18 07:41
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Creates a new Person if both mebers of the couple are in age and opposite gender</h2>
 <hr>
 */
@SuppressWarnings ("unused")
public class ReproductionRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of ReproductionRule : sets minimumAge at -1</h2>
     <hr>
     */
    public ReproductionRule () { this(anyAge); }

    /**
     <hr>
     <h2>Constructor of ReproductionRule using minimumAge param</h2>
     <hr>

     @param minimumAge Minimum age to reach to have kids
     */
    public ReproductionRule (int minimumAge)
    {
        if (minimumAge < 0 && minimumAge != anyAge) throw new IllegalArgumentException("MinimumAge param can't be negative");

        this.minimumAge = minimumAge;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Context param</h2>
     <h3>A new Person is created if
     - Both members of a Couple relationship have reached the minimumAge <br>
     - They have opposite Gender (Female X Male or Male X Female) <br>
     <br>
     Creates a new Person with random Gender and SexualOrientation
     </h3>
     <hr>

     @param context Context to apply this rule onto
     */
    public Graph apply (@NotNull Graph context)
    {
        ArrayList8<Node> visited = new ArrayList8<>();

        for (Edge edge : context.edges().subList(edge -> edge.type() == Couple))
        {
            if (visited.containsAny(edge.from(), edge.towards())) continue;

            Person person1 = (Person) edge.from().value();
            Person person2 = (Person) edge.towards().value();

            PersonalData dataP1 = person1.data();
            PersonalData dataP2 = person2.data();

            Gender gender1 = dataP1.gender();
            Gender gender2 = dataP2.gender();

            int age1 = dataP1.age();
            int age2 = dataP2.age();

            if (gender1.equals(gender2)) continue;
            if (minimumAge != anyAge && age1 < minimumAge) continue;
            if (minimumAge != anyAge && age2 < minimumAge) continue;

            visited.add(edge.from());
            visited.add(edge.towards());

            int age = currentTime();
            Gender gender = Gender.values()[randBetween(0, Gender.values().length)];
            SexualOrientation orientation = SexualOrientation.values()[randBetween(0, SexualOrientation.values().length)];

            Person newPerson = new Person(new PersonalData(age, gender, orientation));

            context.addNode(new Node<>(newPerson));
        }

        return context;
    }
    //endregion
}

package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.entities.PersonalData;
import PopulationSimulator.model.enums.Gender;
import PopulationSimulator.model.factories.PersonFactory;
import PopulationSimulator.model.graph.Edge;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.model.enums.EdgeType.*;
import static PopulationSimulator.model.enums.Gender.Male;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 13:17
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 * <hr>
 * <h2>Creates a new Person if both mebers of the couple are in age and opposite gender</h2>
 * <hr>
 */
@SuppressWarnings("unused")
public class ReproductionRule extends SimpleRule {
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     * <hr>
     * <h2>Constructor of ReproductionRule : sets minimumAge at -1</h2>
     * <hr>
     */
    public ReproductionRule() {
        this(anyAge);
    }

    /**
     * <hr>
     * <h2>Constructor of ReproductionRule using minimumAge param</h2>
     * <hr>
     *
     * @param minimumAge Minimum age to reach to have kids
     */
    public ReproductionRule(int minimumAge) {
        if (minimumAge < 0 && minimumAge != anyAge)
            throw new IllegalArgumentException("MinimumAge param can't be negative");

        this.minimumAge = minimumAge;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     * <hr>
     * <h2>Applies this Rule on the Context param</h2>
     * <h3>A new Person is created if
     * - Both members of a Couple relationship have reached the minimumAge <br>
     * - They have opposite Gender (Female X Male or Male X Female) <br>
     * <br>
     * Creates a new Person with random Gender and SexualOrientation
     * </h3>
     * <hr>
     *
     * @param context Context to apply this rule onto
     */
    @SuppressWarnings("unchecked")
    @Override
    public Graph apply(@NotNull Graph context) {
        ArrayList8<Node> visited = new ArrayList8<>();

        for (Edge edge : context.edges().subList(edge -> edge.type().isSameAs(Couple))) {
            if (visited.containsAny(edge.from(), edge.towards())) continue;
            if (!haveOppositeGender(edge.from(), edge.towards())) continue;

            Node<Person> fatherNode = (edge.type().isSameAs(Husband)) ? edge.from() : edge.towards();
            Node<Person> motherNode = (edge.type().isSameAs(Wife)) ? edge.from() : edge.towards();

            if (minimumAge != anyAge && fatherNode.value().getAge() < minimumAge) continue;
            if (minimumAge != anyAge && motherNode.value().getAge() < minimumAge) continue;
            if (context.getNeighboorNodes(fatherNode, Parent).size() >= 3) continue;
            if (context.getNeighboorNodes(motherNode, Parent).size() >= 3) continue;

            visited.add(edge.from());
            visited.add(edge.towards());

            Node<Person> childNode = new Node<>(PersonFactory.createPerson());

            context.addNode(childNode);

            for (Node sibling : context.getNeighboorNodes(fatherNode, Parent)) {
                context.addEdge(childNode, sibling, isMale(childNode) ? Brother : Sister);
                context.addEdge(sibling, childNode, isMale(childNode) ? Brother : Sister);
            }

            context.addEdge(fatherNode, childNode, Father);
            context.addEdge(motherNode, childNode, Mother);

            context.addEdge(childNode, fatherNode, isMale(childNode) ? Son : Daughter);
            context.addEdge(childNode, motherNode, isMale(childNode) ? Son : Daughter);
        }

        return context;
    }
    //endregion

    //region --------------- Private methods -----------------
    private boolean haveOppositeGender(Node node1, Node node2) {
        Person person1 = (Person) node1.value();
        Person person2 = (Person) node2.value();

        PersonalData dataP1 = person1.data();
        PersonalData dataP2 = person2.data();

        Gender gender1 = dataP1.gender();
        Gender gender2 = dataP2.gender();

        return gender1 != gender2;
    }

    /**
     * Simple util method to increase code readability
     *
     * @param node Node<Person> of wich's gender is to test
     * @return True if value() of [node] is Male. False otherwise.
     */
    private boolean isMale(@NotNull Node<Person> node) {
        return node.value().data().gender() == Male;
    }
    //endregion
}

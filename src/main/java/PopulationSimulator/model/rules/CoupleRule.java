package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.enums.Gender;
import PopulationSimulator.model.enums.SexualOrientation;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.model.enums.EdgeType.*;
import static PopulationSimulator.model.enums.Gender.Male;
import static PopulationSimulator.model.enums.SexualOrientation.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 11:52
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 * <hr>
 * <h2>Creates Couple relationships within the context</h2>
 * <hr>
 */
public class CoupleRule extends SimpleRule {
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     * <hr>
     * <h2>Constructor of CoupleRule : sets minimumAge at -1</h2>
     * <hr>
     */
    public CoupleRule() {
        this(anyAge);
    }

    /**
     * <hr>
     * <h2>Constructor of CoupleRule using minimumAge param</h2>
     * <hr>
     *
     * @param minimumAge Minimum age to create a couple relationship with any other Person
     */
    public CoupleRule(int minimumAge) {
        if (minimumAge < 0 && minimumAge != anyAge)
            throw new IllegalArgumentException("MinimumAge param can't be negative");

        this.minimumAge = minimumAge;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     * <hr>
     * <h2>Applies this Rule on the Context param</h2>
     * <h3>
     * — A Person that didn't reach minium age can't get in couple <br>
     * — A Person that already is in couple, can't get in couple with someone else <br>
     * — 2 People have to <code>match</code> to get in couple <br>
     * See {@link CoupleRule#isMatch(Person, Person)}</h3>
     * <hr>
     *
     * @param context Context to apply this rule onto
     */
    public Graph apply(@NotNull Graph context) {
        //noinspection unchecked
        ArrayList8<Node<Person>> tmpPeople = context.getNodesContaining(Person.class).mapAndCollect(node -> (Node<Person>) node).subList(
                node -> {
                    boolean oldEnough = node.value().data().age() >= minimumAge;
                    boolean single = !context.getEdgesFrom(node).contains(edge -> edge.type().isSameAs(Couple));
                    return oldEnough && single;
                });

        for (Node<Person> node1 : tmpPeople) {
            if (context.getEdgesFrom(node1).contains(edge -> edge.type().isSameAs(Couple))) continue;

            for (Node<Person> node2 : tmpPeople) {
                if (context.getEdgesFrom(node2).contains(edge -> edge.type().isSameAs(Couple))) continue;

                if (node1.equals(node2)) continue;
                if (!isMatch(node1.value(), node2.value())) continue;

                context.addEdge(node1, node2, isMale(node1) ? Husband : Wife);
                context.addEdge(node2, node1, isMale(node2) ? Husband : Wife);

                break;
            }
        }

        return context;
    }
    //endregion

    //region --------------- Methods -------------------------

    /**
     * <hr>
     * <h2>Checks if 2 people match each other</h2>
     * <hr>
     *
     * @param p1 Person to match with p2
     * @param p2 Person to match with p1
     * @return True if p1 matches p2 (and reciprocally), False otherwise
     */
    private boolean isMatch(@NotNull Person p1, @NotNull Person p2) {
        SexualOrientation ori1 = p1.data().orientation();
        SexualOrientation ori2 = p2.data().orientation();

        Gender gen1 = p1.data().gender();
        Gender gen2 = p2.data().gender();

        if (ori1 == Bi) {
            if (ori2 == Bi) return true;
            if (ori2 == Hetero) return gen1 == Gender.getOpposite(gen2);
            if (ori2 == Homo) return gen1 == gen2;
        }

        if (ori1 == Hetero) return gen1 == Gender.getOpposite(gen2) && (ori2 == Hetero || ori2 == Bi);
        if (ori1 == Homo) return gen1 == gen2 && (ori2 == Homo || ori2 == Bi);

        return false;
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

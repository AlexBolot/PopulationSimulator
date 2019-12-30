package PopulationSimulator.model.rules;

import PopulationSimulator.model.graph.Graph;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The MeetingRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/12/2019 15:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 * <hr>
 * <h2>Creates a new Person if both mebers of the couple are in age and opposite gender</h2>
 * <hr>
 */
public class MeetingRule extends SimpleRule {

    /**
     * <hr>
     * <h2>Constructor of MeetingRule</h2>
     * <hr>
     */
    public MeetingRule() {
    }

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
    @Override
    public Graph apply(@NotNull Graph context) {
        return context;
    }

}

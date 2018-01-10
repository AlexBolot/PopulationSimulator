package PopulationSimulator.rules;

import PopulationSimulator.entities.Population;

import java.util.Objects;

public class LifespanRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private int maxLifespan;
    //endregion

    //region --------------- Constructors --------------------
    public LifespanRule () { this(80); }

    public LifespanRule (int maxLifespan) { this.maxLifespan = maxLifespan; }
    //endregion

    //region --------------- Override ------------------------
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        population.people().removeIf(person -> person.data().getAge() >= maxLifespan);

        population.relations().removeIf(relation -> {
            boolean notContainsP1 = !population.people().contains(relation.person1());
            boolean notContainsP2 = !population.people().contains(relation.person2());

            return notContainsP1 || notContainsP2;
        });
    }
    //endregion
}

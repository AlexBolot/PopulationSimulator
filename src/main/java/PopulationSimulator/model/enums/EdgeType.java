package PopulationSimulator.model.enums;

import org.jetbrains.annotations.Contract;

/*................................................................................................................................
 . Copyright (c)
 .
 . The EdgeType class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 13:17
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public enum EdgeType {
    InterPerson(),

    HasMet(InterPerson),

    Family(HasMet),
    Couple(Family),

    Husband(Couple),
    Wife(Couple),

    Parent(Family),
    Father(Parent),
    Mother(Parent),

    Sibling(Family),
    Brother(Sibling),
    Sister(Sibling),

    Child(Family),
    Son(Child),
    Daughter(Child);

    private EdgeType _super;

    EdgeType() {
        this._super = null;
    }

    EdgeType(EdgeType _super) {
        this._super = _super;
    }

    @Contract(pure = true)
    public EdgeType getSuper() {
        return _super;
    }

    public boolean isSameAs(EdgeType other) {
        return isSameAs(this, other);
    }

    private boolean isSameAs(EdgeType type, EdgeType other) {
        return type == other || (type.getSuper() != null && isSameAs(type.getSuper(), other));
    }
}

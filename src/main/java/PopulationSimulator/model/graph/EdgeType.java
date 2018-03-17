package PopulationSimulator.model.graph;

import org.jetbrains.annotations.Contract;

/*................................................................................................................................
 . Copyright (c)
 .
 . The EdgeType class was coded by : Alexandre BOLOT
 .
 . Last modified : 17/03/18 02:11
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public enum EdgeType
{
    Couple(),
    Family(),
    Father(Family),
    Mother(Family),
    Child(Family);

    private EdgeType _super;

    EdgeType () { this._super = null; }

    EdgeType (EdgeType _super) { this._super = _super; }

    @Contract (pure = true)
    public EdgeType getSuper () { return _super; }

    public boolean isSame (EdgeType other) { return isSame(this, other); }

    private boolean isSame (EdgeType type, EdgeType other)
    {
        return type == other || (type.getSuper() != null && isSame(type.getSuper(), other));
    }
}

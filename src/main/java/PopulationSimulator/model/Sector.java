package PopulationSimulator.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Sector class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:59
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class Sector
{
    //region --------------- Attributes ----------------------
    private static int IDCounter = 0;

    private final int                ID;
    private final ArrayList<Integer> neighboors;
    //endregion

    //region --------------- Constructors --------------------
    public Sector (@NotNull int... neighboors)
    {
        this();

        //region --> Check params
        if (neighboors == null) throw new IllegalArgumentException("Neighboors param is null");
        //endregion

        Arrays.stream(neighboors).forEach(this.neighboors::add);
    }

    public Sector ()
    {
        ID = IDCounter++;
        this.neighboors = new ArrayList<>();
    }
    //endregion

    //region --------------- Getters and Setters -------------
    public int ID () { return ID; }

    public ArrayList<Integer> neighboors () { return neighboors; }
    //endregion

    //region --------------- Methods -------------------------
    public void addNeighboor (int sectorID) { if (!neighboors.contains(sectorID) || sectorID == ID) neighboors.add(sectorID); }

    public void addNeighboors (@NotNull int... neighboors)
    {
        //region --> Check params
        if (neighboors == null) throw new IllegalArgumentException("Neighboors param is null");
        //endregion

        Arrays.stream(neighboors).forEach(this::addNeighboor);
    }

    public boolean isNeighboorOf (@NotNull Sector sector)
    {
        //region --> Check params
        if (sector == null) throw new IllegalArgumentException("Sector param is null");
        //endregion

        return isNeighboorOf(sector.ID());
    }

    public boolean isNeighboorOf (int sectorID) { return neighboors.contains(sectorID); }
    //endregion

    //region --------------- Overrides -----------------------
    @Override
    public boolean equals (Object o)
    {
        if (o == null) return false;
        if (!(o instanceof Sector)) return false;

        Sector sector = (Sector) o;

        return ID == sector.ID;
    }

    @Override
    public int hashCode ()
    {
        return ID;
    }
    //endregion
}

package PopulationSimulator.model;

import java.util.ArrayList;
import java.util.Arrays;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Sector class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:00
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Sector
{
    private static int IDCounter = 0;

    private final int ID;

    private final ArrayList<Integer> neighboors;

    public Sector (int... neighboors)
    {
        this();
        Arrays.stream(neighboors).forEach(this.neighboors::add);
    }

    public Sector ()
    {
        ID = IDCounter++;
        this.neighboors = new ArrayList<>();
    }

    public int ID () { return ID; }

    public ArrayList<Integer> neighboors () { return neighboors; }

    public void addNeighboor (int sectorID) { if (!neighboors.contains(sectorID)) neighboors.add(sectorID); }

    public void addNeighboors (int... neighboors) { Arrays.stream(neighboors).forEach(this::addNeighboor); }

    public boolean isNeighboorOf (Sector sector) { return neighboors.contains(sector.ID); }

    public boolean isNeighboorOf (int sectorID) { return neighboors.contains(sectorID); }

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
}

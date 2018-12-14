package PopulationSimulator.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Sector class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("unused")
public class Sector {
    //region --------------- Attributes ----------------------
    private static int IDCounter = 0;

    private final int ID;
    private final ArrayList<Integer> neighboors;
    //endregion

    //region --------------- Constructors --------------------
    public Sector(@NotNull int... neighboors) {
        this();

        Arrays.stream(neighboors).forEach(this.neighboors::add);
    }

    public Sector() {
        ID = IDCounter++;
        this.neighboors = new ArrayList<>();
    }
    //endregion

    //region --------------- Getters and Setters -------------
    public int ID() {
        return ID;
    }

    @NotNull
    public ArrayList<Integer> neighboors() {
        return neighboors;
    }
    //endregion

    //region --------------- Methods -------------------------
    public void addNeighboor(int sectorID) {
        if (!neighboors.contains(sectorID) || sectorID == ID) neighboors.add(sectorID);
    }

    public void addNeighboors(@NotNull int... neighboors) {
        Arrays.stream(neighboors).forEach(this::addNeighboor);
    }

    public boolean isNeighboorOf(@NotNull Sector sector) {
        return isNeighboorOf(sector.ID());
    }

    public boolean isNeighboorOf(int sectorID) {
        return neighboors.contains(sectorID);
    }
    //endregion

    //region --------------- Overrides -----------------------
    @Override
    public boolean equals(@NotNull Object obj) {
        if (!(obj instanceof Sector)) return false;

        Sector sector = (Sector) obj;

        return ID == sector.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }
    //endregion
}

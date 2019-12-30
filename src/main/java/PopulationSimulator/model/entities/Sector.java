package PopulationSimulator.model.entities;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.graph.Node;
import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Sector class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/12/2019 15:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Sector {

    private static int IDCounter = 0;
    private final int ID;

    private ArrayList8<Node<Person>> inhabitants;

    public Sector() {
        ID = IDCounter++;
        this.inhabitants = new ArrayList8<>();
    }

    public Sector addInhabitant(Node<Person> personNode) {
        this.inhabitants.add(personNode);
        return this;
    }

    public boolean has(Node<Person> personNode) {
        return this.inhabitants.contains(personNode);
    }

    public int ID() {
        return ID;
    }

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

    public boolean isNeighboorOf(Sector sector2) {
        return false;
    }

    public boolean isNeighboorOf(int ID) {
        return false;
    }
}

package PopulationSimulator.visualizer.stats;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.RelationType;
import org.jetbrains.annotations.NotNull;

import static java.util.stream.Collectors.toCollection;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationsStats class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ({"WeakerAccess", "unused"})
public class RelationsStats extends ArrayList8<ArrayList8<Relation>>
{
    //region --------------- Attributes ---------------------
    private ArrayList8<Relation> all;
    //endregion

    //region --------------- Constructors -------------------
    public RelationsStats () { all = new ArrayList8<>(); }
    //endregion

    //region --------------- Adder --------------------------

    /**
     <hr>
     <h2>Adds the [relations] to the total group of relations</h2>
     <hr>

     @param relations List of Relations to add to this
     @return True if the adding went well
     */
    @Override
    public boolean add (ArrayList8<Relation> relations)
    {
        return all.addAll(relations) && super.add(relations);
    }
    //endregion

    //region --------------- Global accessors ---------------

    /**
     <hr>
     <h2>List of all Relations</h2>
     <hr>

     @return List of all Relations
     */
    @NotNull
    public ArrayList8<Relation> getAll () { return all; }

    /**
     <hr>
     <h2>List of amounts of new Relation each turn</h2>
     <hr>

     @return List of amounts of new Relation each turn
     */
    @NotNull
    public ArrayList8<Integer> countByTurn () { return map(ArrayList8::size).collect(toCollection(ArrayList8::new)); }

    /**
     <hr>
     <h2>Average amount of new Relations by turn</h2>
     <hr>

     @return Average amount of new Relations by turn
     */
    public double averageAmount () { return stream().mapToInt(ArrayList8::size).average().orElse(0); }

    /**
     <hr>
     <h2>Longest relation duration</h2>
     <hr>

     @return Longest relation duration
     */
    public int getLongest () { return all.map(Relation::getDuration).max(Integer::compareTo).orElse(-1); }

    /**
     <hr>
     <h2>Shortest relation duration</h2>
     <hr>

     @return Shortest relation duration
     */
    public int getShortest () { return all.map(Relation::getDuration).min(Integer::compareTo).orElse(-1); }

    /**
     <hr>
     <h2>Biggest age delta in a relation</h2>
     <hr>

     @return Biggest age delta in a relation
     */
    public int getBiggestDelta () { return all.map(Relation::getDeltaAge).max(Integer::compareTo).orElse(-1); }

    /**
     <hr>
     <h2>Average age delta in a relation</h2>
     <hr>

     @return Average age delta in a relation
     */
    public int getAverageDelta () { return (int) all.stream().mapToInt(Relation::getDeltaAge).average().orElse(-1); }

    /**
     <hr>
     <h2>Average age delta in a relation</h2>
     <hr>

     @return Average age delta in a relation
     */
    public int getAverageDuration () { return (int) all.stream().mapToInt(Relation::getDuration).average().orElse(-1); }
    //endregion

    //region --------------- Count --------------------------

    /**
     <hr>
     <h2>Amount of relations with 1 [gender1] and 1 [gender2]</h2>
     <hr>

     @param gender1 Gender of one of the 2 memebers of the Relation
     @param gender2 Gender of one of the 2 memebers of the Relation
     @return Amount of relations with 1 [gender1] and 1 [gender2]
     */
    public int countByGender (@NotNull Gender gender1, @NotNull Gender gender2) { return getByGender(gender1, gender2).size(); }

    /**
     <hr>
     <h2>Amount of relations with an age delta smaller or bigger than [delta], depending on [smallerThan]</h2>
     <hr>

     @param delta       Age delta to be looked for
     @param smallerThan True if looking for : actual delta < [delta], False otherwise
     @return Amount of relations with an age delta smaller or bigger than [delta], depending on [smallerThan]
     */
    public int countByAgeDelta (int delta, boolean smallerThan) { return getByAgeDelta(delta, smallerThan).size(); }

    /**
     <hr>
     <h2>Amount of relations of [type] type</h2>
     <hr>

     @param type RelationType to look for
     @return Amount of relations of [type] type
     */
    public int countByType (@NotNull RelationType type) { return getByType(type).size(); }

    /**
     <hr>
     <h2>Amount of relations of duration [duration]</h2>
     <hr>

     @param duration    Duration to look for
     @param smallerThan True if looking for : actual duration < [duration], False otherwise
     @return Amount of relations of duration [duration]
     */
    public int countByDuration (int duration, boolean smallerThan) { return getByDuration(duration, smallerThan).size(); }
    //endregion

    //region --------------- Get by -------------------------

    /**
     <hr>
     <h2>List of relations with 1 [gender1] and 1 [gender2]</h2>
     <hr>

     @param gender1 Gender of one of the 2 memebers of the Relation
     @param gender2 Gender of one of the 2 memebers of the Relation
     @return List of relations with 1 [gender1] and 1 [gender2]
     */
    @NotNull
    public ArrayList8<Relation> getByGender (@NotNull Gender gender1, @NotNull Gender gender2)
    {
        return all.subList(r -> {
            Gender g1 = r.person1().data().gender();
            Gender g2 = r.person2().data().gender();

            return ((g1 == gender1) && (g2 == gender2)) || ((g1 == gender2) || (g2 == gender1));
        });
    }

    /**
     <hr>
     <h2>List of relations with an age delta smaller or bigger than [delta], depending on [smallerThan]</h2>
     <hr>

     @param delta       Age delta to be looked for
     @param smallerThan True if looking for : actual delta < [delta], False otherwise
     @return List of relations with an age delta smaller or bigger than [delta], depending on [smallerThan]
     */
    @NotNull
    public ArrayList8<Relation> getByAgeDelta (int delta, boolean smallerThan)
    {
        return all.subList(r -> (smallerThan) ? r.getDeltaAge() < delta : r.getDeltaAge() > delta);
    }

    /**
     <hr>
     <h2>List of relations of [type] type</h2>
     <hr>

     @param type RelationType to look for
     @return List of relations of [type] type
     */
    @NotNull
    public ArrayList8<Relation> getByType (@NotNull RelationType type) { return all.subList(r -> r.type() == type); }

    /**
     <hr>
     <h2>List of relations of duration [duration]</h2>
     <hr>

     @param duration    Duration to look for
     @param smallerThan True if looking for : actual duration < [duration], False otherwise
     @return List of relations of duration [duration]
     */
    @NotNull
    public ArrayList8<Relation> getByDuration (int duration, boolean smallerThan)
    {
        return all.subList(r -> (smallerThan) ? r.getDuration() < duration : r.getDuration() > duration);
    }

    //endregion
}

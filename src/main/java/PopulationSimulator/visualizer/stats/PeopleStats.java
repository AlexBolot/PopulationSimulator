package PopulationSimulator.visualizer.stats;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import static java.util.stream.Collectors.toCollection;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PeopleStats class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ({"WeakerAccess", "unused"})
public class PeopleStats extends ArrayList8<ArrayList8<Person>>
{
    //region --------------- Attributes ---------------------
    private ArrayList8<Person> all;
    //endregion

    //region --------------- Constructors -------------------
    public PeopleStats () { all = new ArrayList8<>(); }
    //endregion

    //region --------------- Adder --------------------------

    /**
     <hr>
     <h2>Adds the [people] to the total group of people</h2>
     <hr>

     @param people List of people to add to this
     @return True if the adding went well
     */
    @Override
    public boolean add (ArrayList8<Person> people)
    {
        return all.addAll(people) && super.add(people);
    }
    //endregion

    //region --------------- Global accessors ---------------

    /**
     <hr>
     <h2>List of all People</h2>
     <hr>

     @return List of all People
     */
    @NotNull
    public ArrayList8<Person> getAll ()
    {
        return all;
    }

    /**
     <hr>
     <h2>List of amounts of new Person each turn</h2>
     <hr>

     @return List of amounts of new Person each turn
     */
    @NotNull
    public ArrayList8<Integer> countByTurn () { return map(ArrayList8::size).collect(toCollection(ArrayList8::new)); }

    /**
     <hr>
     <h2>Average amount of new Person by turn</h2>
     <hr>

     @return Average amount of new Person by turn
     */
    public double averageAmount () { return stream().mapToInt(ArrayList8::size).average().orElse(0); }
    //endregion

    //region --------------- Count --------------------------

    /**
     <hr>
     <h2>Amount of [age] year old people</h2>
     <hr>

     @param age Age to look for
     @return Amount of [age] year old people
     */
    public int countByAge (int age) { return getByAge(age).size(); }

    /**
     <hr>
     <h2>Amount of people older than [age] year old</h2>
     <hr>

     @param age Age to look for
     @return Amount of people older than [age] year old
     */
    public int countOlderThan (int age) { return getOlderThan(age).size(); }

    /**
     <hr>
     <h2>Amount of people younger than [age] year old</h2>
     <hr>

     @param age Age to look for
     @return Amount of people younger than [age] year old
     */
    public int countYoungerThan (int age) { return getYoungerThan(age).size(); }

    /**
     <hr>
     <h2>Amount of [gender] people</h2>
     <hr>

     @param gender Gender to look for
     @return Amount of [gender] people
     */
    public int countByGender (@NotNull Gender gender) { return getByGender(gender).size(); }

    /**
     <hr>
     <h2>Amount of [orientation] people</h2>
     <hr>

     @param orientation Sexual orientation to look for
     @return Amount of [orientation] people
     */
    public int countByOrientation (@NotNull SexualOrientation orientation) { return getByOrientation(orientation).size(); }
    //endregion

    //region --------------- Get by -------------------------

    /**
     <hr>
     <h2>List of [age] year old people</h2>
     <hr>

     @param age Age to look for
     @return List of [age] year old people
     */
    @NotNull
    public ArrayList8<Person> getByAge (int age) { return getAll().subList(p -> p.data().age() == age); }

    /**
     <hr>
     <h2>List of people older than [age] year old</h2>
     <hr>

     @param age Age to look for
     @return List of people older than [age] year old
     */
    @NotNull
    public ArrayList8<Person> getOlderThan (int age) { return getAll().subList(p -> p.data().age() > age); }

    /**
     <hr>
     <h2>List of people younger than [age] year old</h2>
     <hr>

     @param age Age to look for
     @return List of people younger than [age] year old
     */
    @NotNull
    public ArrayList8<Person> getYoungerThan (int age) { return getAll().subList(p -> p.data().age() < age); }

    /**
     <hr>
     <h2>List of [gender] people</h2>
     <hr>

     @param gender Gender to look for
     @return List of [gender] people
     */
    @NotNull
    public ArrayList8<Person> getByGender (@NotNull Gender gender) { return getAll().subList(p -> p.data().gender() == gender); }

    /**
     <hr>
     <h2>List of [orientation] people</h2>
     <hr>

     @param orientation Sexual orientation to look for
     @return List of [orientation] people
     */
    @NotNull
    public ArrayList8<Person> getByOrientation (@NotNull SexualOrientation orientation)
    {
        return getAll().subList(p -> p.data().orientation() == orientation);
    }
    //endregion
}

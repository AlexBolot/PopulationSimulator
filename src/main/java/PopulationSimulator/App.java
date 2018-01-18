package PopulationSimulator;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.model.Sector;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.model.rules.CoupleRule;
import PopulationSimulator.model.rules.LifespanRule;
import PopulationSimulator.model.rules.ReproductionRule;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:57
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>This class is the main access to the Simulator.<br>
 Nothing more to say :)</h2>
 */
public class App
{
    public static void main (String[] args)
    {
        ArrayList8<Person> people = new ArrayList8<Person>()
        {{
            IntStream.range(0, 10).forEach(i -> add(randPerson()));
        }};

        ArrayList8<Applyable> rules = new ArrayList8<Applyable>()
        {{
            add(new CoupleRule(5));
            add(new ReproductionRule(7));
            add(new LifespanRule(15));
        }};

        ArrayList8<Sector> sectors = new ArrayList8<Sector>()
        {{
            add(new Sector(1, 3)); // 0
            add(new Sector(0, 2)); // 1
            add(new Sector(1, 3)); // 2
            add(new Sector(0, 2)); // 3
        }};

        LinkedHashMap<Person, Sector> locations = new LinkedHashMap<>();

        people.forEach(person -> locations.put(person, sectors.get(randBetween(0, sectors.size()))));

        Context context = new Context(people, new ArrayList8<>(), sectors, locations);

        new SimulationController(context, rules).simulate(25);
    }

    /**
     <hr>
     <h2>Generates a random Person with :<br>
     - Age = 0 <br>
     - Gender = random from the Enum <br>
     - Sexual Ori. = random from the Enum</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 11/01
     </h3>
     <hr>

     @return A new Person with random data (see details above)
     */
    @NotNull
    private static Person randPerson ()
    {
        int age = 0;

        Gender gender = Gender.values()[randBetween(0, Gender.values().length)];
        SexualOrientation orientation = SexualOrientation.values()[randBetween(0, SexualOrientation.values().length)];

        return new Person(new PersonalData(age, gender, orientation));
    }
}

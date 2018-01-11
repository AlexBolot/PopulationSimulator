package PopulationSimulator;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.rules.Applyable;
import PopulationSimulator.rules.CoupleRule;
import PopulationSimulator.rules.LifespanRule;
import PopulationSimulator.rules.ReproductionRule;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 23:25
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
        LinkedHashSet<Person> people = new LinkedHashSet<Person>()
        {{
            IntStream.range(0, 10).forEach(i -> add(randPerson()));
        }};

        LinkedHashSet<Applyable> rules = new LinkedHashSet<Applyable>()
        {{
            add(new LifespanRule(15));
            add(new CoupleRule(5));
            add(new ReproductionRule(7));
        }};

        new SimulationController(new Population(people), rules).simulate(25);
    }

    /**
     <hr>
     <h2>Generates a random Person with :<br>
     - ID between 10 and 100 <br>
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
        Random random = new Random();

        int ID = randBetween(10, 100);
        int age = 0;
        Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
        SexualOrientation orientation = SexualOrientation.values()[random.nextInt(SexualOrientation.values().length)];

        return new Person(new PersonalData(String.valueOf(ID), age, gender, orientation));
    }
}

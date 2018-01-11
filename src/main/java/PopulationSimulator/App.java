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

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.stream.IntStream;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 22:19
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

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

    private static Person randPerson ()
    {
        Random random = new Random();

        int ID = random.nextInt(90) + 10;
        int age = 0;
        Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
        SexualOrientation orientation = SexualOrientation.values()[random.nextInt(SexualOrientation.values().length)];

        return new Person(new PersonalData(String.valueOf(ID), age, gender, orientation));
    }
}

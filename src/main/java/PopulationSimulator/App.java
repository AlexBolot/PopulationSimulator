package PopulationSimulator;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;

import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.SexualOrientation.Hetero;

public class App 
{
    public static void main( String[] args )
    {
        PersonalData data1 = new PersonalData("Alex", Male);
        PersonalData data2 = new PersonalData("Alex", Male, Hetero);
        PersonalData data3 = new PersonalData("Alex", 12, Male, Hetero);

        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);

        System.out.println(new Person(data1));
    }
}

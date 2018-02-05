package PopulationSimulator.visualizer.cli;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.RelationType;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.utils.ArrayList8;
import PopulationSimulator.visualizer.cli.CLI.OptionEnum;
import PopulationSimulator.visualizer.stats.PeopleStats;
import PopulationSimulator.visualizer.stats.RelationsStats;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.isInteger;
import static PopulationSimulator.visualizer.Logger.logCLI;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Visualizer class was coded by : Alexandre BOLOT
 .
 . Last modified : 05/02/18 16:45
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Visualizer
{
    //region --------------- Attributes ----------------------
    private PeopleStats    peopleStats;
    private CommandLine    userArgs;
    private RelationsStats relationsStats;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Simple constructor of the Visualizer</h2>
     <hr>

     @param userArgs CommandLine containing the options and their parameters to be used later
     */
    public Visualizer (@NotNull CommandLine userArgs)
    {
        this.peopleStats = new PeopleStats();
        this.relationsStats = new RelationsStats();
        this.userArgs = userArgs;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public PeopleStats peopleStats () { return peopleStats; }

    public RelationsStats relationsStats () { return relationsStats; }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Merges a [context] with this, filling the PeopleStats and RelationsStats objects</h2>
     <hr>

     @param context Context to merge with this, filling the PeopleStats and RelationsStats objects
     */
    public void addTurn (@NotNull Context context)
    {
        peopleStats.add(context.people());
        relationsStats.add(context.relations());
    }

    /**
     <hr>
     <h2>Prints statistics required by userArgs, in the constructor</h2>
     <h3>Note : Return value is only used for UnitTesting, since this method already prints on standars output</h3>
     <hr>

     @return A String value containing all of the statistics required by userArgs
     */
    @NotNull
    public String printStats ()
    {
        StringBuilder str = new StringBuilder();

        Option[] options = userArgs.getOptions();

        for (int i = 0; i < options.length; i++)
        {
            Option option = options[i];
            OptionEnum optionEnum = OptionEnum.valueOf(option.getOpt());

            String[] values = (option.hasArgs()) ? option.getValues() : new String[]{};
            int argc = values.length;

            int intVal = 0;
            boolean smallerThan = false;
            Gender gen1 = Gender.getRandom();
            Gender gen2 = Gender.getRandom();
            SexualOrientation ori = SexualOrientation.getRandom();
            RelationType type = RelationType.getRandom();

            if (argc >= 1 && isInteger(values[0])) intVal = parseInt(values[0]);
            if (argc == 1 && SexualOrientation.contains(values[0])) ori = SexualOrientation.valueOf(values[0]);
            if (argc == 1 && RelationType.contains(values[0])) type = RelationType.valueOf(values[0]);
            if (argc >= 1 && Gender.contains(values[0])) gen1 = Gender.valueOf(values[0]);
            if (argc == 2 && Gender.contains(values[1])) gen2 = Gender.valueOf(values[1]);
            if (argc == 2 && Arrays.asList("true", "false").contains(values[1])) smallerThan = parseBoolean(values[1]);

            String qualifier = smallerThan ? "smaller" : "longer";

            ArrayList8<Integer> amounts;

            switch (optionEnum)
            {
                //region --------------- People ------------------

                case listAllP:
                    str.append("List of all People :\n");
                    peopleStats.getAll().forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;

                case nbP_avg:
                    str.append("Average amount of new Person by turn : ");
                    str.append(new DecimalFormat("#.#").format(peopleStats.averageAmount()));
                    break;

                case nbP_eachTurn:
                    str.append("List of amounts of new Person each turn :\n");

                    amounts = peopleStats.countByTurn();

                    IntStream.range(0, amounts.size()).forEach(j -> str.append("\t-> ")
                                                                       .append("turn ")
                                                                       .append(j)
                                                                       .append("\t=\t")
                                                                       .append(amounts.get(j))
                                                                       .append("\n"));
                    break;

                case nbP_age:
                    str.append("Amount of ").append(intVal).append(" year old people : ");
                    str.append(peopleStats.countByAge(intVal));
                    break;

                case nbP_older:
                    str.append("Amount of people older than ").append(intVal).append(" year old : ");
                    str.append(peopleStats.countOlderThan(intVal));
                    break;

                case nbP_younger:
                    str.append("Amount of people younger than ").append(intVal).append(" year old : ");
                    str.append(peopleStats.countYoungerThan(intVal));
                    break;

                case nbP_gender:
                    str.append("Amount of ").append(gen1).append(" people : ");
                    str.append(peopleStats.countByGender(gen1));
                    break;

                case nbP_orientation:
                    str.append("Amount of ").append(ori).append(" people : ");
                    str.append(peopleStats.countByOrientation(ori));
                    break;

                case listP_age:
                    str.append("List of ").append(intVal).append(" year old people : ");
                    peopleStats.getByAge(intVal).forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;

                case listP_older:
                    str.append("List of people older than ").append(intVal).append(" year old :\n");
                    peopleStats.getOlderThan(intVal).forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;

                case listP_younger:
                    str.append("List of people younger than ").append(intVal).append(" year old :\n");
                    peopleStats.getYoungerThan(intVal).forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;

                case listP_gender:
                    str.append("List of the ").append(gen1).append(" people : \n");
                    peopleStats.getByGender(gen1).forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;

                case listP_orientation:
                    str.append("List of the ").append(ori).append(" people : \n");
                    peopleStats.getByOrientation(ori).forEach(person -> str.append("\t-> ").append(person).append("\n"));
                    break;
                //endregion

                //region --------------- Relations ---------------

                case listAllR:
                    str.append("List of all Relations :\n");
                    relationsStats.getAll().forEach(relation -> str.append("\t-> ").append(relation).append("\n"));
                    break;

                case nbR_avg:
                    str.append("Average amount of new Relations by turn : ");
                    str.append(new DecimalFormat("#.#").format(relationsStats.averageAmount()));
                    break;

                case nbR_eachTurn:
                    str.append("List of amounts of new Relation each turn :\n");

                    amounts = relationsStats.countByTurn();

                    IntStream.range(0, amounts.size()).forEach(j -> str.append("\t-> ")
                                                                       .append("turn ")
                                                                       .append(j)
                                                                       .append("\t=\t")
                                                                       .append(amounts.get(j))
                                                                       .append("\n"));
                    break;

                case get_longest:
                    str.append("Longest relation duration : ");
                    str.append(relationsStats.getLongest());
                    str.append(" years");
                    break;

                case get_shortest:
                    str.append("Shortest relation duration : ");
                    str.append(relationsStats.getShortest());
                    str.append(" years");
                    break;

                case get_avgDuration:
                    str.append("Average relation duration : ");
                    str.append(relationsStats.getAverageDuration());
                    break;

                case get_biggestDelta:
                    str.append("Biggest age delta in a relation : ");
                    str.append(relationsStats.getBiggestDelta());
                    break;

                case get_avgDelta:
                    str.append("Average age delta in a relation : ");
                    str.append(relationsStats.getAverageDelta());
                    break;

                case nbR_gender:
                    str.append("Amount of relations with 1 ").append(gen1).append(" and 1 ").append(gen2).append(" :");
                    str.append(relationsStats.countByGender(gen1, gen2));
                    break;

                case nbR_ageDelta:
                    str.append("Amount of relations with an age delta of ").append(intVal).append(" years : ");
                    str.append(relationsStats.countByAgeDelta(intVal, smallerThan));
                    break;

                case nbR_type:
                    str.append("Amount of relations of type ").append(type).append(" : ");
                    str.append(relationsStats.countByType(type));
                    break;

                case nbR_duration:
                    str.append("Amount of relations of duration ").append(qualifier).append(" than ").append(intVal).append(" : ");
                    str.append(relationsStats.countByDuration(intVal, smallerThan));
                    break;

                case listR_gender:
                    str.append("List of Relations with 1 ").append(gen1).append(" and 1 ").append(gen2).append(" : ");
                    relationsStats.getByGender(gen1, gen2).forEach(rel -> str.append("\t-> ").append(rel).append("\n"));
                    break;

                case listR_ageDelta:
                    str.append("List of Relations with an age delta of ").append(intVal).append(" years : ");
                    relationsStats.getByAgeDelta(intVal, smallerThan).forEach(rel -> str.append("\t-> ").append(rel).append("\n"));
                    break;

                case listR_type:
                    str.append("List of Relations of type ").append(type).append(" : ");
                    relationsStats.getByType(type).forEach(rel -> str.append("\t-> ").append(rel).append("\n"));
                    break;

                case listR_duration:
                    str.append("List of Relations of duration ").append(qualifier).append(" than ").append(intVal).append(" : ");
                    relationsStats.getByDuration(intVal, smallerThan).forEach(rel -> str.append("\t-> ").append(rel).append("\n"));
                    break;

                //endregion
            }

            if (i < options.length - 1) str.append("\n--------------------\n");
        }

        String message = str.toString();

        logCLI(message);
        return message;
    }
    //endregion
}
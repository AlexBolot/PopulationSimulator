package PopulationSimulator.visualizer.cli;

import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

import static PopulationSimulator.visualizer.Logger.clearCLI;
import static PopulationSimulator.visualizer.Logger.logCLI;
import static PopulationSimulator.visualizer.cli.CLI.OptionEnum.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CLI class was coded by : Alexandre BOLOT
 .
 . Last modified : 05/02/18 16:47
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class CLI
{
    //region --------------- Attributes ----------------------
    private String            footer;
    private Options           options;
    private CommandLine       userArgs;
    private HelpFormatter     formatter;
    private CommandLineParser parser;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of the CLI</h2>
     <h3>Sets the help formatter's width to 190 and sorting to none</h3>
     <hr>
     */
    public CLI ()
    {
        parser = new DefaultParser();
        options = new Options();

        formatter = new HelpFormatter();
        formatter.setWidth(190);
        formatter.setOptionComparator((o1, o2) -> 0);

        footer = "";

        try
        {
            userArgs = parser.parse(new Options(), new String[]{});
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        initOptions();
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public CommandLine userArgs () { return userArgs; }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Creates all the Option objects and adds them to this options</h2>
     <hr>
     */
    private void initOptions ()
    {
        options = new Options();

        //region --------------- Misc -------------------

        options.addOption(OptionEnum.clear.name(), OptionEnum.clear.name());
        options.addOption(help.name(), help.desc());
        options.addOption(exit.name(), exit.desc() + "\n\n");

        //endregion

        //region --------------- People -----------------

        options.addOption(listAllP.name(), listAllP.desc());
        options.addOption(nbP_avg.name(), nbP_avg.desc());
        options.addOption(nbP_eachTurn.name(), nbP_eachTurn.desc());

        options.addOption(withParam(nbP_age.name(), nbP_age.arg(), nbP_age.desc()));
        options.addOption(withParam(nbP_older.name(), nbP_older.arg(), nbP_older.desc()));
        options.addOption(withParam(nbP_younger.name(), nbP_younger.arg(), nbP_younger.desc()));
        options.addOption(withParam(nbP_gender.name(), nbP_gender.arg(), nbP_gender.desc()));
        options.addOption(withParam(nbP_orientation.name(), nbP_orientation.arg(), nbP_orientation.desc()));

        options.addOption(withParam(listP_age.name(), listP_age.arg(), listP_age.desc()));
        options.addOption(withParam(listP_older.name(), listP_older.arg(), listP_older.desc()));
        options.addOption(withParam(listP_younger.name(), listP_younger.arg(), listP_younger.desc()));
        options.addOption(withParam(listP_gender.name(), listP_gender.arg(), listP_gender.desc()));
        options.addOption(withParam(listP_orientation.name(), listP_orientation.arg(), listP_orientation.desc() + "\n\n"));

        //endregion

        //region --------------- Relation ---------------

        options.addOption(listAllR.name(), listAllR.desc());
        options.addOption(nbR_avg.name(), nbR_avg.desc());
        options.addOption(nbR_eachTurn.name(), nbR_eachTurn.desc());

        options.addOption(withParam(get_longest.name(), get_longest.arg(), get_longest.desc()));
        options.addOption(withParam(get_shortest.name(), get_shortest.arg(), get_shortest.desc()));
        options.addOption(withParam(get_avgDuration.name(), get_avgDuration.arg(), get_avgDuration.desc()));
        options.addOption(withParam(get_biggestDelta.name(), get_biggestDelta.arg(), get_biggestDelta.desc()));
        options.addOption(withParam(get_avgDelta.name(), get_avgDelta.arg(), get_avgDelta.desc()));

        //region options.addOption(Option.builder(nbR_gender.name())
        options.addOption(Option.builder(nbR_gender.name())
                                .desc(nbR_gender.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(nbR_gender.arg())
                                .build());
        //endregion
        //region options.addOption(Option.builder(nbR_ageDelta.name())
        options.addOption(Option.builder(nbR_ageDelta.name())
                                .desc(nbR_ageDelta.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(nbR_ageDelta.arg())
                                .build());
        //endregion
        options.addOption(withParam(nbR_type.name(), nbR_type.arg(), nbR_type.desc()));
        //region options.addOption(Option.builder(nbR_duration.name())
        options.addOption(Option.builder(nbR_duration.name())
                                .desc(nbR_duration.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(nbR_duration.arg())
                                .build());
        //endregion
        //region options.addOption(Option.builder(listR_gender.name())
        options.addOption(Option.builder(listR_gender.name())
                                .desc(listR_gender.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(listR_gender.arg())
                                .build());
        //endregion
        //region options.addOption(Option.builder(listR_ageDelta.name())
        options.addOption(Option.builder(listR_ageDelta.name())
                                .desc(listR_ageDelta.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(listR_ageDelta.arg())
                                .build());
        //endregion
        options.addOption(withParam(listR_type.name(), listR_type.arg(), listR_type.desc()));
        //region options.addOption(Option.builder(listR_duration.name())
        options.addOption(Option.builder(listR_duration.name())
                                .desc(listR_duration.desc())
                                .hasArgs()
                                .numberOfArgs(2)
                                .argName(listR_duration.arg())
                                .build());
        //endregion

        //endregion

        //region --------------- Footer -----------------
        //noinspection StringBufferReplaceableByString
        StringBuilder str = new StringBuilder();
        str.append("\n");
        str.append("Gen         : ").append(Arrays.toString(Gender.values())).append("\n");
        str.append("Ori         : ").append(Arrays.toString(SexualOrientation.values())).append("\n");
        str.append("X           : int value\n");
        str.append("smallerThan : [true, false]");

        footer = str.toString();

        //endregion
    }

    /**
     <hr>
     <h2>Starts the CLI and waits for args</h2>
     <h3>
     Note 1 : if <code>App.main()</code> already has args, deals with them without asking for some more<br>
     Note 2 : in case of unkown options, asks to type again
     </h3>
     <hr>

     @param args Args obtained from <code>App.main()</code><br>
     -> If empty, asks for args on console
     */
    public void start (@NotNull String... args)
    {
        if (args.length == 0) args = new Scanner(System.in).nextLine().split(" ");

        try
        {
            CommandLine line = parser.parse(options, args);

            if (line.getOptions().length == 0) throw new ParseException("Unrecognized option : " + Arrays.toString(args));
            else if (line.hasOption(help.name())) formatter.printHelp("Stats commands\n\n", "", options, footer);
            else if (line.hasOption(clear.name())) clearCLI();
            else if (line.hasOption(exit.name())) System.exit(0);
            else if (line.hasOption(nbR_gender.name()))
            {
                logCLI(Arrays.toString(line.getOptionValues(nbR_gender.name())));
            }
            else
            {
                userArgs = line;
                return;
            }
        }
        catch (ParseException exp)
        {
            logCLI(exp.getMessage());
        }

        start();
    }

    /**
     <hr>
     <h2>Custom method to build an Option</h2>
     <hr>

     @param name Name of the option
     @param arg  Name of the argument
     @param desc Description of the option
     @return An Option with the above values set
     */
    @NotNull
    private Option withParam (String name, String arg, String desc)
    {
        return Option.builder(name).hasArg().argName(arg).desc(desc).build();
    }
    //endregion

    //region --------------- Inner Enum ----------------------
    protected enum OptionEnum
    {
        //region --------------- General -----------------

        clear("Clears the console (usefull after reading this help message)"),
        help("Print list of stat options"),
        exit("Quit the simulator"),

        //endregion

        //region --------------- People ------------------

        listAllP("List of all Person"),
        nbP_avg("Average amount of Person added each turn"),
        nbP_eachTurn("Number of new Person for each turn"),

        nbP_age("Number of Person of age = <X>", "X"),
        nbP_older("Number of Person older than <X> years old", "X"),
        nbP_younger("Number of Person younger than <X> years old", "X"),
        nbP_gender("Number of Person of gender = <Gen>", "Gen"),
        nbP_orientation("Number of Person of sexual orientation = <Ori>", "Ori"),

        listP_age("List of Person of age X", "X"),
        listP_older("List of Person of age > X", "X"),
        listP_younger("List of Person of age < X", "X"),
        listP_gender("List of Person of gender = <Gen>", "Gen"),
        listP_orientation("List of Person of sexual orientation = <Ori>", "Ori"),

        //endregion

        //region --------------- Relations ---------------

        listAllR("List of all Relation"),
        nbR_avg("Average amount of Relation added each turn"),
        nbR_eachTurn("Number of new Relation for each turn"),
        get_longest("Get the relation with the longest duration"),
        get_shortest("Get the relation with the shortest duration"),
        get_avgDuration("Get the relation with the average duration"),
        get_biggestDelta("Get the relation with the biggest age delta between the 2 Person"),
        get_avgDelta("Get the relation with the average age delta between the 2 Person"),

        nbR_gender("Number of Relation containing Gen1 and Gen2", "Gen1 Gen2"),
        nbR_ageDelta("Number of Relation with age delta < or > to X, depending on smallerThan", "X smallerThan"),
        nbR_type("Number of Relation of RelationType = Type", "Type"),
        nbR_duration("Number of Relation with a duration < or > to X, depending on smallerThan", "X smallerThan"),

        listR_gender("List of Relation containing Gen1 and Gen2", "Gen1 Gen2"),
        listR_ageDelta("List of Relation with age delta < or > to X, depending on smallerThan", "X smallerThan"),
        listR_type("List of Relation of RelationType = Type", "Type"),
        listR_duration("List of Relation with a duration < or > to X, depending on smallerThan", "X smallerThan");
        //endregion

        private String desc;
        private String arg;

        OptionEnum (@NotNull String desc)
        {
            this(desc, "");
        }

        OptionEnum (@NotNull String desc, @NotNull String arg)
        {
            this.desc = desc;
            this.arg = arg;
        }

        @Contract (pure = true)
        @NotNull
        public String desc () { return desc; }

        @Contract (pure = true)
        @NotNull
        public String arg () { return arg; }
    }
    //endregion
}

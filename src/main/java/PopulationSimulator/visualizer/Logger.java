package PopulationSimulator.visualizer;

import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*................................................................................................................................
 . Copyright (c)
 .
 . The Logger class was coded by : Alexandre BOLOT
 .
 . Last modified : 31/01/18 17:41
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("WeakerAccess")
public class Logger
{
    //region --------------- Attributes ---------------
    private static final Path pathToLogs = Paths.get("src", "main", "resources", "logs");
    //endregion

    //region --------------- Methods ------------------

    /**
     <hr>
     <h2>Clears the log [files]</h2>
     <hr>

     @param files List of Files to clear
     */
    public static void clearLogs (@NotNull LogFile... files)
    {
        Arrays.stream(files).forEach(file -> log("", file, true));
    }

    /**
     <hr>
     <h2>Write the [header] + each element of the [list] in the [file]</h2>
     <h3>Note : add this at the end of the [file]</h3>
     <hr>

     @param header  Header message
     @param list    List of Objects to log
     @param logFile File to log into
     */
    public static void log (String header, @NotNull ArrayList8<?> list, @NotNull LogFile logFile)
    {
        StringBuilder str = new StringBuilder();

        str.append(header).append("\n\n");
        list.forEach(str::append);

        log(str.toString(), logFile);
    }

    /**
     <hr>
     <h2>Write the [message] in the [file]</h2>
     <h3>Note : add this at the end of the [file]</h3>
     <hr>

     @param message Message to log
     @param logFile File to log into
     */
    public static void log (String message, @NotNull LogFile logFile)
    {
        log(message, logFile, false);
    }

    /**
     <hr>
     <h2>Writes or Overwrites the [message] in the [file]</h2>
     <hr>

     @param message   Message to log
     @param logFile   File to log into
     @param overwrite True if you want to overwite the previous content of the [file]
     */
    public static void log (String message, @NotNull LogFile logFile, boolean overwrite)
    {
        try
        {
            Path pathToFile = Paths.get(pathToLogs.toString(), logFile.path());

            if (!Files.exists(pathToLogs) || !Files.isDirectory(pathToLogs)) Files.createDirectory(pathToLogs);
            if (!Files.exists(pathToFile) || !Files.isRegularFile(pathToFile)) Files.createFile(pathToFile);

            String oldContent = overwrite ? "" : cat(logFile);
            String newContent = (oldContent.isEmpty()) ? message : oldContent + "\n" + message;

            BufferedWriter out = new BufferedWriter(new FileWriter(pathToFile.toString()));
            out.write(newContent);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     <hr>
     <h2>Prints [message] on the console : replaces <code>System.out.println()</code></h2>
     <hr>

     @param message Message to print on the console
     */
    public static void logCLI (String message)
    {
        System.out.println(message);
    }

    /**
     <hr>
     <h2>Prints 5 blank lines on the console (not by best so far)</h2>
     <hr>
     */
    public static void clearCLI ()
    {
        logCLI(IntStream.range(0, 5).mapToObj(i -> "\n").collect(Collectors.joining()));
    }

    /**
     <hr>
     <h2>Returns the String value of the content of the [file]</h2>
     <hr>

     @param file File to read from
     @return The String value of the content of the [file]
     */
    @NotNull
    public static String cat (@NotNull LogFile file)
    {
        Path pathToFile = Paths.get(pathToLogs.toString(), file.path());

        try
        {
            if (Files.exists(pathToFile) && Files.isRegularFile(pathToFile))
            {
                BufferedReader in = new BufferedReader(new FileReader(pathToFile.toString()));
                Optional<String> reduce = in.lines().reduce((s1, s2) -> s1 + "\n" + s2);

                return reduce.orElse("");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return "";
    }
    //endregion

    //region --------------- Inner Enum ---------------
    @SuppressWarnings ("unused")
    public enum LogFile
    {
        PeopeLogFile("/peopleLog.txt"),
        RelationsLogFile("/relationsLog.txt"),
        SectorsLogFile("/sectorsLog.txt");

        private String path;

        LogFile (@NotNull String path) { this.path = path; }

        @Contract (pure = true)
        @NotNull
        public String path () { return path; }
    }
    //endregion
}

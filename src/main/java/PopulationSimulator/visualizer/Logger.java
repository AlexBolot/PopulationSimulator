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


/*................................................................................................................................
 . Copyright (c)
 .
 . The Logger class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ({"WeakerAccess", "unused"})
public class Logger
{
    private static final Path pathToLogs = Paths.get("src", "main", "resources", "logs");

    public static void clearLogs (@NotNull LogFile... files)
    {
        Arrays.stream(files).forEach(file -> log("", file, true));
    }

    public static void log (String header, @NotNull ArrayList8<?> list, @NotNull LogFile logFile)
    {
        StringBuilder str = new StringBuilder();

        str.append(header).append("\n\n");
        list.forEach(str::append);

        log(str.toString(), logFile);
    }

    public static void log (String message, @NotNull LogFile logFile)
    {
        log(message, logFile, false);
    }

    public static void log (String message, @NotNull LogFile logFile, boolean override)
    {
        try
        {
            Path pathToFile = Paths.get(pathToLogs.toString(), logFile.path());

            if (!Files.exists(pathToLogs) || !Files.isDirectory(pathToLogs)) Files.createDirectory(pathToLogs);
            if (!Files.exists(pathToFile) || !Files.isRegularFile(pathToFile)) Files.createFile(pathToFile);

            String oldContent = override ? "" : cat(logFile);
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
}

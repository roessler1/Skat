package skat.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class Log {

    final static Level level = Level.ALL;
    static Logger logger = null;
    Handler fileHandler;
    SimpleFormatter plainText;

    private Log() {
        logger = Logger.getLogger(Log.class.getName());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH.mm.ss");
        String fileName = "logfile_" + LocalDate.now() + "_" + LocalTime.now().format(timeFormatter) + ".txt";
        try {
            Path path = Paths.get("crash-reports");
            if(!Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            fileHandler = new FileHandler("crash-reports/" + fileName, true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.setLevel(level);
        logger.addHandler(fileHandler);
    }

    public static Logger getLogger() {
        if(logger == null) {
            new Log();
        }
        return logger;
    }
}

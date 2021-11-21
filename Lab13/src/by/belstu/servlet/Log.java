package by.belstu.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static String logFile = "D:\\Danil\\university\\4\\PiI\\Internet-programming\\Lab13\\logs\\stdout\\log.txt";

    public static void info(String message) throws IOException {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer
                    .append(message)
                    .append(String.valueOf('\n'));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


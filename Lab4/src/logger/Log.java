package logger;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    public static void info(String message) {
        System.out.println(System.getProperty("user.dir"));
        try (FileWriter writer = new FileWriter("../logs/stdout.txt", true)) {
            writer
                    .append(message)
                    .append(String.valueOf('\n'));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

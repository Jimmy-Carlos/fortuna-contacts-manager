import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsFile {
    private static String directory;
    private static String file;

    public ContactsFile(String directory, String file) {
        this.directory = directory;
        this.file = file;
    }

    public static void createDirectory() throws IOException {
        if(Files.notExists(Paths.get(directory))) {
            Files.createDirectories(Paths.get(directory));
        }
    }

    public static void createFile() throws IOException {
        if (!Files.exists(Paths.get(directory, file))) {
            Files.createFile(Paths.get(directory, file));
        }
    }

    public static List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get(directory, file));
    }

    public static void writeToFile(String name, String phone) throws IOException {
        Files.write(Paths.get(directory, file),
                Arrays.asList(name + "," + phone),
                StandardOpenOption.APPEND
        );
    }
}

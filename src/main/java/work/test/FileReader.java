package work.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    public static String[] textReader() throws Exception {

        Path path = Paths.get("src/main/resources/file/name_age.txt");
        String content = Files.readString(path);
        String[] str = content.split(System.lineSeparator());
        return str;
    }

}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

//    public List<String> readFile(String filePath) {
    public List<String> readFile() {
        List<String> linesList = new ArrayList<>();
        try {

            String content = Files.readString(Paths.get(Demo.filePath));
            String[] lines = content.split("\n");

            for (String line : lines) {
//                line = line + "\n";
                linesList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linesList;
    }

}
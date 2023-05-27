import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    private static final String filePath = System.getProperty("user.dir") + "/timeLogs.csv";

    public List<String> readFile() {
        List <String> linesList = new ArrayList<>();
        try {
            String content = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linesList;
    }
}

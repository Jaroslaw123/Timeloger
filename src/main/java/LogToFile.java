import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LogToFile {
    private static final String path = System.getProperty("user.dir") + "/timelogs.csv";

    FileWriter fileWriter;
    ReadFromFile readFromFile;

    public LogToFile() {
        this.readFromFile = new ReadFromFile();
    }

    private String parseInput(List<String> paramWithComa){
        return paramWithComa.get(0) + " | " + paramWithComa.get(1) + " | " + paramWithComa.get(2) + "\n";
    }
    public void fileLogingStart(List<String> lineToLog){
        try {
            fileWriter = new FileWriter(LogToFile.path,true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(parseInput(lineToLog));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileLogingStop(String stopDate) {
        try {
            List<String> fileContent = this.readFromFile.readFile(path);
            if (!fileContent.isEmpty()) {
                String lastLine = fileContent.get(fileContent.size() - 1);
                String[] lastline = lastLine.split("\\|");

                lastline[0] = lastline[0].trim() + " | " + stopDate;
                String updatedLine = String.join(" | ", lastline);

                fileContent.set(fileContent.size() - 1, updatedLine);

                fileWriter = new FileWriter(LogToFile.path);
                BufferedWriter out = new BufferedWriter(fileWriter);
                for (String line : fileContent) {
                    out.write(line);
                }
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
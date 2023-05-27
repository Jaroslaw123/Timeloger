import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogToFile {
    private static final String path = System.getProperty("user.dir") + "/timelogs.csv";
    private String[] parseInput(String[] paramWithComa){
        for (String s : paramWithComa) {
            s = s + ",";
        }
        return paramWithComa;
    }
    public void fileLogingStart(String[] lineToLog){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(LogToFile.path,true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(Arrays.toString(parseInput(lineToLog)));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileLogingStop(String stopDate){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(LogToFile.path,true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write("," +stopDate + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

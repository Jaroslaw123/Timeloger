import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Raport {
    static long id;
    ReadFromFile readFromFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    private static final String path = "rest.csv";

    public Raport() {
        this.readFromFile = new ReadFromFile();
    }

    public void generatorRaportu(String path) throws FileNotFoundException {
        Map<String, Integer> hm = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");

                String projectName = values[2];


                LocalDateTime ld1 = LocalDateTime.parse(values[0].trim(), formatter);
                LocalDateTime ld2;
                if (values.length < 4) {
                    ld2 = LocalDateTime.now();
                } else {
                    ld2 = LocalDateTime.parse(values[1].trim(), formatter);
                }


                Integer diff = (int) Math.abs(ChronoUnit.SECONDS.between(ld1, ld2));

                if (hm.containsKey(projectName)) {
                    diff += hm.get(projectName);
                }
                hm.put(projectName, diff);
            }
            for (String name : hm.keySet()) {
                String key = name;
                String value = hm.get(name).toString();
                System.out.println("-------------------------------------------");
                System.out.printf("| %-2s | %-10s | %-8s | %10s |%n", getId(), "Project: ", key, value + " sec");
                System.out.println("-------------------------------------------");
                printTasks(name, path);
            }
            System.out.println("-------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getId() {
        id++;
        return id;
    }
    public void printTasks(String projectName, String path) {
        Map<String, Integer> hm2 = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split("\\|");

                LocalDateTime ld1 = LocalDateTime.parse(values[0].trim(), formatter);

                LocalDateTime ld2;
                String taskName;
                if (values.length < 4) {
                    ld2 = LocalDateTime.now();
                    taskName = values[2].trim();
                } else {
                    ld2 = LocalDateTime.parse(values[1].trim(), formatter);
                    taskName = values[3].trim();
                }

                Integer diff2 = (int) Math.abs(ChronoUnit.SECONDS.between(ld1, ld2));

                if (hm2.containsKey(taskName)) {
                    diff2 += hm2.get(taskName);
                }

                hm2.put(taskName, diff2);

                String taskOfProject = values[2];
                if (taskOfProject.equals(projectName)) {
                    System.out.printf("| %-2s | %-10s | %-8s | %10s |%n", "", "Task: ",
                            taskName, hm2.get(taskName) + " sec");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
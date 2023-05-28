import java.util.List;

public class Raport {
    static long id;
    ReadFromFile readFromFile;
    Project project;
    Task task;

    private static final String path = System.getProperty("user.dir") + "/rest.csv";

    public Raport() {
        this.readFromFile = new ReadFromFile();
    }

    public void generatorRaportu() {
        List<String> lines = this.readFromFile.readFile();
        for (String s : lines) {
            String[] line = s.split("\\|");
            for (String l : line) {
                l.trim();
            }
            String projectName = line[2];
            System.out.println("------------------------------------------");
            System.out.printf("%-10s %s %s\n", getId(), "Project: " + projectName, "");
            System.out.println("------------------------------------------");
            printTasks(projectName);
        }


        System.out.printf("|  %-20s |  %-20s |", "hel", "world");
        System.out.printf("|  %-20s |  %-20s |", "hel", "world");

    }

    public static void main(String[] args) {
        Raport raport = new Raport();
        raport.generatorRaportu();
    }

    public long getId() {
        id++;
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void printTasks(String projectName) {
        List<String> lines = this.readFromFile.readFile();
        for (String s : lines) {
                String[] line = s.split("\\|");
                for (String l : line) {
                    l.trim();
                }
                String taskName = line[2];
                if (taskName.equals(projectName)) {
                    System.out.printf("%-5s %s %s\n", "", "Task: " + line[3], "");
            }
        }
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class Demo {
    LogToFile logToFile;
    ReadFromFile readFromFile;

    Raport raport;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String filePath = "timeLogs.csv";
    public static final String filePath2 = "rest.csv";
    public Demo() {
        this.logToFile = new LogToFile();
        this.readFromFile = new ReadFromFile();
        this.raport = new Raport();
    }

    public void start(String projectName, String taskName) {
        if(isLogFileExists(filePath)) {
            String[] lastLine = readLastLine();
            if (lastLine.length < 4) {
                System.out.println("Ostatni projekt został zakończony !!!");
                stop();
            }
        }
        Project project = new Project(projectName);
        Task task = new Task(taskName);
        List<String> res = List.of(project.getStart().format(formatter),
                project.getName(), task.getName());
        this.logToFile.fileLogingStart(res);
    }

    public void last() {
        if (isLogFileExists(filePath)) {
            String[] lastLine = readLastLine();
            for (String s : lastLine) {
                System.out.print(s);
            }
            System.out.println("");
        } else {
            System.out.println("Błędna ścieżka do pliku");
        }
    }

    public void stop() {
        if (readLastLine() != null && readLastLine().length > 3) {
            System.out.println("Ten projekt juz zostal zastopowany");
        } else if (readLastLine() != null) {
            logToFile.fileLogingStop(LocalDateTime.now().format(formatter));
        } else {
            System.out.println("Nie masz rozpoczetych projektow");
        }
    }

    public String[] readLastLine() {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            String lastLine = fileContent.get(fileContent.size() - 1);
            return lastLine.split("\\|");
        } else {
            return null;
        }
    }

    public String[] findLineByIndex(String index) {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            String line = fileContent.get(Integer.parseInt(index));
            if (line != null) {
                return line.split("\\|");
            }
        }
        return null;
    }

    public String[] findLine(String projectName, String taskName) {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            for (int i = fileContent.size() - 1; i > 0; i--) {
                if (fileContent.get(i).contains(projectName)
                        && fileContent.get(i).contains(taskName)) {
                    return fileContent.get(i).split("\\|");
                }
            }
        }
        return null;
    }

    public void readList() {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            for (String line : fileContent) {
                System.out.print(line);
            }
        }
    }

    public void helpInfo() {
        System.out.println("usage: start -p <name> -t <name>");
        System.out.println("       stop");
        System.out.println("       continue");
        System.out.println("       last");
        System.out.println("       list");
    }

    public boolean isLogFileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Demo demo = new Demo();

        if (args.length == 5) {
            if (args[0].equals("start") && args[1].equals("-p") && args[3].equals("-t")) {
                demo.start(args[2], args[4]);
            } else if (args[0].equals("continue") && args[1].equals("-p") && args[3].equals("-t")) {
                    demo.continueTask(args[2], args[4]);
            }
        } else if (args.length == 4) {
            if (args[0].equals("edit") && args[2].equals("-ts")) {
                demo.editStartTime(args[1], args[3]);
            } else if (args[0].equals("edit") && args[2].equals("-te")) {
                //demo.editStopTime(args[1], args[3])
            } else if (args[0].equals("edit") && args[2].equals("-pn")) {
                //demo.editProjectName(args[1], args[3]);
            } else if (args[0].equals("edit") && args[2].equals("-pn")) {
                //demo.editTaskName(args[1], args[3]);
            }
        }
        else if (args.length == 1) {
            if (args[0].equals("stop")) {
                demo.stop();
            } else if (args[0].equals("list")) {
                demo.readList();
            } else if (args[0].equals("last")) {
                demo.last();
            } else if (args[0].equals("--help")) {
                demo.helpInfo();
            } else if (args[0].equals("report")) {
                demo.raport.generatorRaportu(filePath);
            }
        } else {
            demo.helpInfo();
        }
    }

    public void editStartTime(String index, String timeStart) {
    }

    public void continueTask(String projectName, String taskName) {
        if (isLogFileExists(filePath)) {
            if (readLastLine().length < 4) {
                stop();
            }
            String[] foundTaskAndProject = findLine(projectName, taskName);
            if (foundTaskAndProject != null) {
                String project = foundTaskAndProject[2].trim();
                String task = foundTaskAndProject[3].trim();
                Project found = new Project(project);
                Task foundT = new Task(task);
                List<String> res = List.of(found.getStart().format(formatter),
                        found.getName(), foundT.getName());
                this.logToFile.fileLogingStart(res);
            } else {
                System.out.println("Bledna nazwa projektu lub zadania");
            }
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

class Demo {
    LogToFile logToFile;
    ReadFromFile readFromFile;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String filePath = "timeLogs.csv";
    Raport raport;

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

    public void last(int number) {
        if (isLogFileExists(filePath)) {
            if (number == 0) {
                String[] lastLine = readLastLine();
                for (String s : lastLine) {
                    System.out.print(s);
                }
                System.out.println("");
            } else if (number > 0) {
                for (String readNumLine : readNumLines(number)) {
                    System.out.println(readNumLine);
                }
            } else {
                System.out.println("Błędny parametr liczby tasków");
            }
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
    public List<String> readNumLines(int n) {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            List<String> allLines = new ArrayList<>();
            if (fileContent.size() < n) {
                n = fileContent.size();
            }

            for (int i = fileContent.size() - n; i < fileContent.size(); i++) {
                allLines.add(fileContent.get(i));
            }
            return allLines;
        } else {
            return null;
        }
    }

    public String[] findLineByIndex(String index) {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            String line = fileContent.get(parseInt(index));
            if (line != null) {
                return line.split("\\|");
            }
        }
        return null;
    }

    public String[] findLine(int id) {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            String[] line = fileContent.get(id).split("\\|");
            return line;
        }
        return null;
    }

    public void readList() {
        List<String> fileContent = readFromFile.readFile();
        if (fileContent != null && !fileContent.isEmpty()) {
            int counter = 1;
            for (String line : fileContent) {
                System.out.println("[" + counter + "] " + line);
                counter++;
            }
        }
    }

    public void helpInfo() {
        Help help = new Help();
        help.displayHelpMessage();
    }

    public boolean isLogFileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Demo demo = new Demo();

        if (args.length == 5) {
            if (args[0].equals("start") && args[1].equals("-p") && args[3].equals("-t")) {
                demo.start(args[2], args[4]);
            }
        } else if (args.length == 4) {
            if (args[0].equals("edit") && args[2].equals("-ts")) {
                demo.editStartTime(args[1], args[3]);
            } else if (args[0].equals("edit") && args[2].equals("-te")) {

            } else if (args[0].equals("edit") && args[2].equals("-pn")) {

            } else if (args[0].equals("edit") && args[2].equals("-pn")) {

            }
        }
        else if (args.length == 3) {
            if (args[0].equals("continue") && args[1].equals("-id")) {
                demo.continueTask(parseInt(args[2]));
            }
        }
        else if (args.length == 1) {
            if (args[0].equals("stop")) {
                demo.stop();
            } else if (args[0].equals("list")) {
                demo.readList();
            } else if (args[0].equals("--help")) {
                demo.helpInfo();
            }
            else if (args[0].equals("last") ) {
                demo.last(0);
            }
            else if (args[0].equals("report")) {
                demo.raport.generatorRaportu(filePath);
            }
        }
        else if (args.length == 3 && args[0].equals("last") && args[1].equals("-n")) {
            demo.last(parseInt(args[2]));
        }
        else {
            demo.helpInfo();
        }

    }

    public void editStartTime(String index, String timeStart) {
    }

public void continueTask(int id) {
        if (isLogFileExists(filePath)) {
            List<String> fileContent = readFromFile.readFile();
            if(id > 0 && id < fileContent.size()) {
                String[] foundTaskAndProject = findLine(id - 1);


                String projectName;
                String taskName;
                if (foundTaskAndProject.length == 3) {
                    projectName = foundTaskAndProject[1];
                    taskName = foundTaskAndProject[2];

                } else {
                    projectName = foundTaskAndProject[2];
                    taskName = foundTaskAndProject[3];
                }
                if (foundTaskAndProject != null) {
                    start(projectName, taskName);
                } else {
                    System.out.println("Bledna nazwa projektu lub zadania");
                }
            } else {
                System.out.println("Podaj wlasciwe id zadania");
            }
        }
    }
}
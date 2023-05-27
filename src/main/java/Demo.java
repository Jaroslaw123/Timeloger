import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {
    static Stack<Project> projects = new Stack<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static void start(String projectName, String taskName) {
        Project newProject = new Project(projectName);
        Task newTask = new Task(taskName);
        newProject.addTask(newTask);
        projects.push(newProject);
        System.out.println(newProject.getStart().format(formatter)
                + " " + newProject.getName()
                + " " + newTask.getName());
    }

    public static void stop() {
        Project lastProject = projects.pop();
        lastProject.setEnd(LocalDateTime.now());
        System.out.println(lastProject.getEnd().format(formatter)
                + " " + lastProject.getName());
    }

//    public static String convertToCSV(String[] data) {
//        return Stream.of(data)
////                .map(this::escapeSpecialCharacters)
//                .collect(Collectors.joining(","));
//    }
    public static void main(String[] args) throws IOException {
        LogToFile logToFile = new LogToFile();
        String[] params = {"MWO","test","data"};
        String stopDate = "2022.08.20";
        logToFile.fileLogingStart(params);
        logToFile.fileLogingStop(stopDate);

//        start("MWO" , "task");
//        stop();
//        LogToFile logToFile = new LogToFile();
//        System.out.println(LogToFile.path);
//
//        List<String[]> dataLines = new ArrayList<>();
//        dataLines.add(new String[]
//                { "jan", "dom", "38", "Comment Data\nAnother line of comment data" });
//
//        File csvOutputFile = new File(LogToFile.path);
//        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
//            for (String[] dataLine : dataLines) {
//                String s = convertToCSV(dataLine);
//                pw.println(s);
//            }
//        }
//        assertTrue(csvOutputFile.exists());
    }
}

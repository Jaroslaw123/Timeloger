import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

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


    public static void main(String[] args) {
        start("MWO" , "task");
        stop();

    }
}

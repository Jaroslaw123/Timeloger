
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Demo {
    private List<Project> projects;

    public Demo() {
        this.projects = new ArrayList<>();
    }
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void start(String projectName, String taskName) {
        Project newProject = new Project(projectName);
        Task newTask = new Task(taskName);
        newProject.addTask(newTask);
        projects.add(newProject);
        StringBuilder sb = new StringBuilder();
        sb.append(newProject.getStart().format(formatter)
                + " " + newProject.getName()
                + " " + newTask.getName());
        System.out.println(sb);
    }

    public void stop() {
        Project newProject = new Project("MWO");
        Task newTask = new Task("task");
        newProject.addTask(newTask);
        newProject.setEnd(LocalDateTime.now());
        projects.add(newProject);
//        Project lastProject = projects.get(projects.size()-1);
//        lastProject.setEnd(LocalDateTime.now());
        StringBuilder sb = new StringBuilder();
        sb.append(newProject.getStart().format(formatter)
                + " | " + newProject.getName()
                + " | " + newProject.getTasks().get(0).getName());
        sb.insert(17, " --> " + newProject.getEnd().format(formatter));
        System.out.println(sb);
    }


    public static void main(String[] args) {
        Demo demo = new Demo();


        if (args.length == 5) {
            if (args[0].equals("start") && args[1].equals("-p") && args[3].equals("-t")) {
                demo.start(args[2], args[4]);
            }
        }

        if (args.length == 1) {
            if (args[0].equals("stop")) {
                demo.stop();
            }
        }

    }
}

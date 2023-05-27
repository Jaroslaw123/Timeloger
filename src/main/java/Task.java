import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    String name;
    LocalDateTime start;
    LocalDateTime end;

    public Task(String name) {
        this.name = name;
        this.start = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}

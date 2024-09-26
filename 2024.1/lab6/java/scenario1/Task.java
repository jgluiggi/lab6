import java.util.Random;

public class Task {
    String id;
    int priority;
    long timestamp;
    long execDuration;

    public Task(String id, long timestamp, int priority) {
        this.id = id;
        this.timestamp = timestamp;
        this.priority = priority;
    }

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
            this.execDuration = execDuration;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

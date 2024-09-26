import java.time.Instant;
import java.util.Queue;

public class TaskProducer implements Runnable {

    private Queue<Task> queue;
    private long id;

    public TaskProducer(Queue<Task> queue, long id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        long i = 0;
        while (true) {
            try {
                long timestamp = Instant.now().toEpochMilli();
                Thread.sleep(5000);
                queue.offer(new Task(id + "-" + i, timestamp));
                i += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
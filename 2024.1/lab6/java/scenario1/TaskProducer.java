import java.time.Instant;
import java.util.Queue;

public class TaskProducer implements Runnable {

    private Queue<Task> queue;
    private int id;
    private int waitTime;

    public TaskProducer(Queue<Task> queue, int id) {
        this.queue = queue;
        this.id = id;
        if (id == 0){
            this.waitTime = 13000;
        } else if (id == 1) {
            this.waitTime = 7000;
        } else {
            this.waitTime = 3000;
        }
    }

    @Override
    public void run() {
        long i = 0;
        while (true) {
            try {
                long timestamp = Instant.now().toEpochMilli();
                Thread.sleep(waitTime);
                queue.offer(new Task(id + "-" + i, timestamp, this.id));
                i += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
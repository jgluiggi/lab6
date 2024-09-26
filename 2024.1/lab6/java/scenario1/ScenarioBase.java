import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;



public class ScenarioBase {


    public static void main(String[] args) {
        var queue = new PriorityBlockingQueue<Task>(11, new PriorityCompare());
        var scheduler = Executors.newScheduledThreadPool(1);
        var array = new ArrayList<LinkedList<Task>>(3);
        for (int i = 0; i < 3; i++) {
            array.add(i, new LinkedList<>());
        }

        scheduler.scheduleAtFixedRate(() -> {
            long timestamp = Instant.now().toEpochMilli();
            status(array, timestamp);
        }, 0, 5, TimeUnit.SECONDS);

        var executorTaskProducer = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorTaskProducer.submit(new TaskProducer(queue, i));
        }

        var executorNode = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorNode.submit(new Node(queue, array.get(i)));
        }
    }

    public static void status(ArrayList<LinkedList<Task>> array, long timestamp) {
        for (int i = 0; i < array.size(); i++) {
            var tasks = array.get(i);

            System.out.println("Tasks de executor " + i);
            var sum = 0L;
            for (int j = 0; j < tasks.size(); j++) {
                var task = tasks.get(j);
                if (task.timestamp > timestamp) {
                    break;
                }

                sum += task.execDuration;
                System.out.println("  " + task.id + " tempo: " + task.execDuration + " tempo total: " + (timestamp - task.timestamp));
            }
            if (sum > 0) {
                System.out.println("  Média: " + (sum / tasks.size()));
            }
        }
    }
}

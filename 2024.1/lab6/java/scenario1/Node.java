import java.util.LinkedList;
import java.util.Queue;

public class Node implements Runnable {

  private Queue<Task> queue;

  private LinkedList<Task> out;

  public Node(Queue<Task> queue, LinkedList<Task> out) {
    this.queue = queue;
    this.out = out;
  }

  @Override
  public void run() {
    while (true) {
      var task = queue.poll();
      if (task != null) {
        task.execute();
        out.add(task);
      }
    }
  }
}

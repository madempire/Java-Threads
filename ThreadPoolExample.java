
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                System.out.println("Task 1 "+LocalDateTime.now().getSecond());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        };
        Runnable task2 = () -> {
            try {
                System.out.println("Task 2 "+LocalDateTime.now().getSecond());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        };
        Runnable task3 = () -> {
            try {
                System.out.println("Task 3 "+LocalDateTime.now().getSecond());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        };
        Runnable tas4 = () -> {
            try {
                System.out.println("Task 4 "+LocalDateTime.now().getSecond());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        };
        Runnable task5;
        task5 = () -> {
            System.out.println("Task 5 "+LocalDateTime.now().getSecond());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(task);
        service.execute(task2);
        service.execute(task3);
        service.execute(tas4);
        service.execute(task5);
        service.close();
    }
}


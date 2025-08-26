public class VolatileEx {
    private static volatile boolean running = true;
    public static void main(String[] args) {
        new Thread(() -> {
            while(running){

            }
        }).start();

        try {
            Thread.sleep(1000); // give some time to complere thread execution
        } catch (InterruptedException ex) {
        }
        running = false; // Change the volatile variable, visible to other thread
        System.out.println("Main thread set running to false");
    }
}

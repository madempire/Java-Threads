public class DaemonThread {
    public static void main(String[] args) {
        // Daemon thread run in backgorund till all the user threads finish execution after jvm will automatically terminates the daemon Thread
        Thread deamon = new Thread(() -> {
            while (true) { 
                try {
                    System.out.println("Daemon Thread is running");
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        deamon.setDaemon(true);
        deamon.start();
        System.out.println("Main thread is running");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Main thread is running");
        
    }
}

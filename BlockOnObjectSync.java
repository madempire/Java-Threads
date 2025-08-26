public class BlockOnObjectSync {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Runnable job1 = () -> printer.printDocument("Report.pdf");
        Runnable job2 = () -> printer.printDocument("Invoice.pdf");

        new Thread(job1, "User-1").start();
        new Thread(job2, "User-2").start();
    }
}
class Printer {
    private final Object lock = new Object();
    public void printDocument(String docName){
        System.out.println(Thread.currentThread().getName()+" preparing to print "+docName);
        synchronized (lock) {
            for (int i = 0; i <= 3; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+" printing page "+i+" of "+docName);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+" finished printing "+docName);
    }
}

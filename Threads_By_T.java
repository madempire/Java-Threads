public class Threads_By_T {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        mt.start();
        // Sleep in main thread to let mt run first
        Thread.sleep(1000);
        // mt.join(); it also do the nearly same job as sleep 
       
        Thread t = new Thread(new MyrThread());
        // Sleep in main thread to let t run before the program
        t.start();
        t.sleep(1000);
        // t.join();

    }
}
class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("Thread");
        for (int i = 0; i < 10 ; i++) {
            
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}
class MyrThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable");
        for (int i = 0; i < 10; i++) {
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}

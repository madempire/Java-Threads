public class InstanceMethoSync {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
            }
        };
        new Thread(task,"Thread-A").start();
        new Thread(task,"Thread-B").start();
    }
}

class Counter{
    private int count = 0;
    public synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName()+" incremented count to "+count);
    }
    public int getCount(){
        return count;
    }
}

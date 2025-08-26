public class SyncOnClass {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                
                    GlobalCounter.incrementGlobalCounter();
                    
                
            }
        };
        new Thread(task, "Thread 1").start();
        new Thread(task, "Thread 2").start();
     }
}
class GlobalCounter{
    private static int count = 0;
    public static synchronized void incrementGlobalCounter(){
        count++;
        System.out.println(Thread.currentThread().getName()+" Incremented count "+count);
    }
    public static int getGlobalCount(){
        return count;
        
    }
}

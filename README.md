# Java-Threads
## 1. difference between threads and process
### 1.1 Process:-
    * An executing program is called a process. Process provides environment for execution of threads
    * Every process has its separate address space.
    * Processs-based multitasking allows a computer to run two or more than two programs concurrently.
    * Communication and Context-Switching between two processes is expensive and limited.
    * Process is called heavyweight task.
### 1.2 Thread:-
    *Thread is a small part of process.
    * All the threads of process share the same address space cooperatively as that of a process.
    * Thread-based mutltitasking allows a single program to run two ore more threads concurrently.
    * Communication and context-switching between two threads is less expensive as compared to process.
    * Thread are also called lightweight task.
## 2. Thread Life Cycle:-
![Thread Life Cycle](image-1.png)
### 2.1 New State:- 
    * When you create a thread object using new Thread() or implementing Runnable, the thread starts in New state. At this point:
        * No system resuorces have been allocated
        * The thread hasn't started the execution.
        * Only start() or stop() methods can be called.
        * Calling other methods throws IllegalThreadStateException.
### 2.2 Runnable State:-
    * After calling start(), the thread moves to Runnable state. This state means:
        * The thread is ready to execute but may not be currently running.
        * It is waiting for CPU time allocation from the thread scheduler.
        * Multiple runnable threads share CPU time in small slices.
        * The thread scheduler decides which runnable thread gets CPU based on priority.
### 2.3 Running State(Active State):-
    * When the thread scheduler assigns CPU time to a runnable thread, it enters Running state:
        * The thread is actively executing on the CPU.
        * Instruction in the run() method are executing sequentially.
        * After the time slice ends, it returns to Runnable State.
        * This is sub-state of the broader "Active State".
### 2.4 Blocked state:-
    * A thread enters Blocked state when:
        * It is waiting to acquire a monitor lock held by another thread.
        * Trying to enter a synchronized block/method already occupied.
        * Waiting to reacquire a lock after calling Object.wait().
        * The only transition from Blocked is back to Runnable once the lock is acquired.
### 2.5 Waiting State:-
    * A thread moves to Waiting state when:
        * Calling Object.wait() without timeout
        * Calling Thread.join() without timeout
        * Calling LockSupport.park()
        * The thread waits indefinitely until notified by another thread.
### 2.6 Timed_Waiting State:-
    * This state occurs when a thread calls methods with timeout parameters:
        * Thread.sleep(milliseconds)
        * Object.wait(timeout).
        * Thread.join(timeOut).
        * LockSupport.parkNanos() or LockSupport.parkUntil().
        * The thread automaticalli returns to Runnable When timeout expires or notification is recieved.
### 2.7 Terminated/Dead state:-
    * A thread reaches Terminated state when:
        * The run() method completes execution.
        * An uncaught exception terminates the thread.
        * The thread is explicitly stopped (depcreated).
        * Once terminated, a thread cannot be restarted.

![alt text](image.png)
## 3. Creating thread using Thread class vs Runnable interface
### 3.1 Thread class
    * When we extend Thread class, we can’t extend any other class even we require.
    *  When we extend Thread class, each of our thread creates unique object associate with it. 
### 3.2 Runnable interface
    * When we implement Runnable, we can save a space for our class to extend any other class in future or now.
    * When we implements Runnable, it shares the same object to multiple threads.
Ex:-public class Threads_By_T {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        mt.start();
        // Sleep in main thread to let mt run first
        Thread.sleep(1000);
       
        Thread t = new Thread(new MyrThread());
        // Sleep in main thread to let t run before the program
        t.start();
        t.sleep(1000);
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
## 4. Daemon Thread:-
    * DT in java is a low priority thread that runs in the background to perform tasks such as garbage collection. Daemon thread in java is a service provider thread that provides services to the user thread. Its life depends on the mercy of user threads i.e when all the user threads die. JVM terminates this thread automatically. Eg:-Garbage collection in java(gc), finalizer, etc...

    * They can not prevent the jvm from exiting, when all the user threads finish execution Jvm terminates itself, By default the main thread is always non-daemon but for all the remaining threads created by daemon thread inherit deamon in nature from parent to child.
    
    * Methods:- setDaemon(boolean status), isDaemon() etc...
Ex:-public class DaemonThread {
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
## 5. Concurrency :-
    * Concurrency focuses on managing multiple tasks at once, creating the illusion of simultaneous execution even on a single-core processor.
    * It is achieved through techniques like context switching where a single CPU rapidly switches between different tasks, making progress on each without necessarily completing one before starting another.
    * Java's multithreading capabilities, using the Thread class or Runnable interface, facilitate concurrency. Threads can be managed and synchronized using constructs like synchronized blocks/methods, volatile keywords, and java.util.concurrent package utilities (e.g., Lock, Semaphore, ExecutorService) 
## 6. Parallel Execution :-
    * Parallelism involves the actual simultaneous execution of multiple tasks or subtasks. This requires multiple processing units (CPU cores or separate processors).
    * It aims to reduce the total execution time of a large task by dividing it into smaller, independent subtasks that can be processed concurrently on different cores.
## 7. Thread Pool:-
    * Java Thread pool represents a group of worker threads that are waiting for the job and reused many 
    times. A thread from the thread pool is pulled out and assigned a job by the service provider. After 
    completion of the job, the thread is contained in the thread pool again.
    * Thread pool reuses previously created threads to execute current tasks and offers a solution to the 
    problem of thread cycle overhead and resource thrashing. Since the thread is already existing when 
    the request arrives, the delay introduced by thread creation is eliminated, making the application 
    more responsive.
    * Eg-Server Programs such as database and web servers repeatedly execute requests from multiple 
    clients. JVM creating too many threads at the same time can cause the system to run out of memory.
    * newFixedThreadPool(int) -Creates a fixed size thread pool.
    * newCachedThreadPool() -Creates a thread pool that creates new threads as needed, but will reuse 
    previously constructed threads when they are available
    * newSingleThreadExecutor()-Creates a single thread.  
    * Thread Pool has to be ended explicitly at the end. If this is not done, then the program goes on 
    executing and never ends. Call shutdown() on the pool to end the executor.
![alt text](image-2.png)

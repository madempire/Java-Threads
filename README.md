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
## Thread Life Cycle:-
![Thread Life Cycle](image-1.png)
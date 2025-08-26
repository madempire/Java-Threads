class WaitNotifyDemo {
    public static void main(String[] args) {
       PingPong game = new PingPong();
       Thread pingThread = new Thread(game::ping,"ping thread");
       Thread pongThread = new Thread(game::pong,"pong thread");
       pingThread.start();
       pongThread.start();
       try {
        pingThread.join();
        pongThread.join(); 
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
class PingPong {
    private final Object lock = new Object();

    public void ping(){
        synchronized (lock) {
            try {
                // if it's not ping's turn wait (though here it always is)
                System.out.println("ping wait to notified by pong");
                lock.wait();
                System.out.println("ping notified by pong");
            } catch (InterruptedException ex) {
            }
        }
    }
    public void pong(){
        synchronized (lock) {
           System.out.println("Pong notifying ping");
            lock.notify();
        }
    }
}
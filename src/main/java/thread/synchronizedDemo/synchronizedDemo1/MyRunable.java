package thread.synchronizedDemo.synchronizedDemo1;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        synchronized (this) { //this看作是“demo这个Runnable对象”,线程t1和t2共享“demo对象的同步锁”
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}
/*
t1 loop 0
t1 loop 1
t1 loop 2
t1 loop 3
t1 loop 4
t2 loop 0
t2 loop 1
t2 loop 2
t2 loop 3
t2 loop 4
 */

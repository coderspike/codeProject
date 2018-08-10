package thread.ThreadSchool.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTwoThreadTest {
    private static Lock lock = new ReentrantLock();

    private static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 1 start");
            lock.lock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("Thread 1 end!!!");
        }
    }

    private static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread 2 start");
            lock.lock();
            lock.unlock();
            System.out.println("Thread 2 end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1().start();
        Thread.sleep(100);
        new T2().start();
    }
}
/*
不同线程不可访问同一锁
 */

package thread.ThreadSchool.lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(new Date() + "\tThread 1 is waiting");
                try {
                    long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                    System.out.println(new Date() + "\tThread 1 remaining time " + waitTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(new Date() + "\tThread 1 is waken up");
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(new Date() + "\tThread 2 is running");
                try {
                    Thread.sleep(4000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                condition.signal();
                System.out.println(new Date() + "\tThread 2 ended");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
/*
Sun Jun 19 15:59:09 CST 2016  Thread 1 is waiting
Sun Jun 19 15:59:09 CST 2016  Thread 2 is running
Sun Jun 19 15:59:13 CST 2016  Thread 2 ended
Sun Jun 19 15:59:13 CST 2016  Thread 1 remaining time -2003467560
Sun Jun 19 15:59:13 CST 2016  Thread 1 is waken up
从执行结果可以看出，虽然thread 2一开始就调用了signal()方法去唤醒thread 1，
但是因为thread 2在4秒钟后才释放锁，也即thread 1在4秒后才获得锁，所以thread 1的await方法在4秒钟后才返回，并且返回负值。
 */
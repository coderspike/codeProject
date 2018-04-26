package thread.ThreadSchool.lock;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println(new Date() + "\tThread 1 started with read lock");
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                }
                System.out.println(new Date() + "\tThread 1 ended");
            } finally {
                readWriteLock.readLock().unlock();
            }
        }).start();
        new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println(new Date() + "\tThread 2 started with read lock");
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                }
                System.out.println(new Date() + "\tThread 2 ended");
            } finally {
                readWriteLock.readLock().unlock();
            }
        }).start();
        new Thread(() -> {
            Lock lock = readWriteLock.writeLock();
            lock.lock();
            try {
                System.out.println(new Date() + "\tThread 3 started with write lock");
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(new Date() + "\tThread 3 ended");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}

/*
Sat Jun 18 21:33:46 CST 2016  Thread 1 started with read lock
Sat Jun 18 21:33:46 CST 2016  Thread 2 started with read lock
Sat Jun 18 21:33:48 CST 2016  Thread 2 ended
Sat Jun 18 21:33:48 CST 2016  Thread 1 ended
Sat Jun 18 21:33:48 CST 2016  Thread 3 started with write lock
Sat Jun 18 21:33:50 CST 2016  Thread 3 ended

thread 1和thread 2都只需获得读锁，因此它们可以并行执行。
而thread 3因为需要获取写锁，必须等到thread 1和thread 2释放锁后才能获得锁。
 */
package thread.ThreadSchool.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    AtomicReference<Thread> owner = new AtomicReference<Thread>();
    private int count;

    public void lock() {
        Thread currentThread = Thread.currentThread();
        System.out.println("lock() -> " + currentThread.getName());
        if (currentThread == owner.get()) {
            count++; // 获取锁的次数
            return;
        }

        // 当线程越来越多由于while循环会浪费cpu时间片，compareAndSet需要多次对同一内存进行访问
        while (!owner.compareAndSet(null, currentThread)) {

        }
    }

    public void unLock() {
        Thread currentThread = Thread.currentThread();
        System.out.println("unLock() -> " + currentThread.getName());
        if (currentThread == owner.get()) {
            if (count > 0) {
                count--;
            } else {
                owner.compareAndSet(currentThread, null);
            }
        }
    }
}
package thread.ThreadSchool.lock;

public class SpinLockTest implements Runnable {
    static int sum;
    private SpinLock lock;


    public SpinLockTest(SpinLock lock) {
        this.lock = lock;
    }

    public void run() {
        this.lock.lock();
        this.lock.lock();
        System.out.println("当前线程 " + Thread.currentThread().getName() + " start...");
        sum++;
        System.out.println("当前线程 " + Thread.currentThread().getName() + " end...");

        this.lock.unLock();
        this.lock.unLock();
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        for (int i = 1; i < 5; i++) {
            SpinLockTest lockTest = new SpinLockTest(lock);
            Thread t = new Thread(lockTest, "thread-lock-" + i);
            t.start();
        }

        Thread.sleep(1000);
        System.out.println(sum);
    }
}

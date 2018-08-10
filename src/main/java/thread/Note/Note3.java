package thread.Note;

public class Note3 {
    /*
    ReentrantLock 重入锁  是同步锁的加强版
    1.1.1. 可重入
    单线程可以重复进入，但要重复退出
    1.1.2. 可中断
    lockInterruptibly()
    1.1.3. 可限时
    超时不能获得锁，就返回false，不会永久等待构成死锁
    1.1.4. 公平锁
    先来先得
    public ReentrantLock(boolean fair)
    public static ReentrantLock fairLock = new ReentrantLock(true);

     */
}

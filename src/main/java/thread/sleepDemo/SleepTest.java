package thread.sleepDemo;

public class SleepTest {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        t1.start();
    }
}

/*
    sleep() 定义在Thread.java中。[时间到了就变成就绪状态了，和yield一样了]
    sleep() 的作用是让当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。sleep()会指定休眠时间，
    线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。

    sleep方法有什么作用,一般用来做什么？
    sleep()方法（休眠）是线程类（Thread）的静态方法，调用此方法会让当前线程暂停执行指定的时间，
    将执行机会（CPU）让给其他线程，
    但是对象的锁依然保持，因此休眠时间结束后会自动恢复。注意这里的恢复并不是恢复到执行的状态，
    而是恢复到可运行状态中等待CPU的宠幸。

    线程的sleep()方法和yield()方法有什么区别？
    ① sleep()方法给其他线程运行机会时不考虑线程的优先级，因此会给低优先级的线程以运行的机会；
    yield()方法只会给相同优先级或更高优先级的线程以运行的机会；
    ② 线程执行sleep()方法后转入阻塞（blocked）状态，而执行yield()方法后转入就绪（ready）状态；
    ③ sleep()方法声明抛出InterruptedException，而yield()方法没有声明任何异常；
    ④ sleep()方法比yield()方法（跟操作系统CPU调度相关）具有更好的可移植性。

    请说出与线程同步以及线程调度相关的方法。
    wait()：使一个线程处于等待（阻塞）状态，并且释放所持有的对象的锁[会释放锁]；
    sleep()：使一个正在运行的线程处于睡眠状态，是一个静态方法，调用此方法要处理InterruptedException异常；
    notify()：唤醒一个处于等待状态的线程，当然在调用此方法的时候，并不能确切的唤醒某一个等待状态的线程，
    而是由JVM确定唤醒哪个线程，而且与优先级无关；
    notityAll()：唤醒所有处于等待状态的线程，该方法并不是将对象的锁给所有线程，而是让它们竞争，
    只有获得锁的线程才能进入就绪状态；

    Java多线程中调用wait() 和 sleep()方法有什么不同？
    Java程序中wait和sleep都会造成某种形式的暂停，它们可以满足不同的需要。
    wait存在于Object类中；sleep存在于Thread类中。
    wait会让出CPU资源以及释放锁；sleep只会释放CPU资源。
    wait只能在同步块中使用；sleep没这限制。
    wait需要notify（或 notifyAll）唤醒，进入等锁状态；sleep到指定时间便会自动恢复到运行状态。
 */
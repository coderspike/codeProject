package thread.waitAndnotifyAndnotifyAllDemo;

public class NotifyAllTest {

    private static Object obj = new Object();

    public static void main(String[] args) {

        ThreadC t1 = new ThreadC("t1");
        ThreadC t2 = new ThreadC("t2");
        ThreadC t3 = new ThreadC("t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println(Thread.currentThread().getName() + " sleep(3000)");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj) {
            // 主线程等待唤醒。
            System.out.println(Thread.currentThread().getName() + " notifyAll()");
            obj.notifyAll();
        }
    }

    static class ThreadC extends Thread {
        public ThreadC(String name) {
            super(name);
        }

        public void run() {
            synchronized (obj) {
                try {
                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " wait");

                    // 唤醒当前的wait线程
                    obj.wait();

                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*
notify(), wait()依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个！
这就是为什么notify(), wait()等函数定义在Object类，而不是Thread类中的原因。

Java中notify 和 notifyAll有什么区别？
notify()方法不能唤醒某个具体的线程，所以只有一个线程在等待的时候它才有用武之地。
而notifyAll()唤醒所有线程并允许他们争夺锁确保了至少有一个线程能继续运行。

多个线程之间如何协调？
wait()、notify()、notifyAll()：这三个方法用于协调多个线程对共享数据的存取，所以必须在同步语句块内使用。
wait方法要等待notify/notifyAll的线程释放锁后才能开始继续往下执行。
 */
package thread;

/**
 * 基础线程demo
 */
public class ThreadDemo implements Runnable {
    /*
          实现多线程的两种方式:
          1、实现Runnable 接口
          2、继承Thread类
            [在java.lang包中定义,继承此类必须重写run()方法]
            public class Thread implements Runnable

          区别：
          1、Runnable 属于接口，所以可以有多个实现；Thread只有一个。
          2、实现Runnable 的线程类，可以被多个线程实例共享数据[每个线程一份数据]。
          [一个类只能有一个父类，但是可以实现多个接口，所以实现Runnable 有更好的扩展性，多个线程“资源共享”，建议使用这种方式]

          线程状态：
          线程有新建，运行，就绪，阻塞，死亡等状态。
          在一个线程完整的生命周期中，它可能经历五种状态：新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）、终止（Zombie）
          wait()会释放对象的同步锁，而sleep()则不会释放锁。

          在Java中什么是线程调度？
           JVM调度的模式有两种：分时调度和抢占式调度。
           分时调度是所有线程轮流获得CPU使用权，并平均分配每个线程占用CPU的时间;
           抢占式调度是根据线程的优先级别来获取CPU的使用权。
           JVM的线程调度模式采用了抢占式模式。既然是抢占调度，那么我们就能通过设置优先级来“有限”的控制线程的运行顺序，注意“有限”一次。
     */

    public static void main(String[] args) {
        Thread thread = new Thread();
        /*
        Thread 是一个类。Thread本身就实现了Runnable接口[public class Thread implements Runnable {}]

        Object类，定义了wait(), notify(), notifyAll()等休眠/唤醒函数。

        Thread类，定义了一些列的线程操作函数。例如，sleep()休眠函数, interrupt()中断函数, getName()获取线程名称等。
        sleep方法：
        public static native void sleep(long millis) throws InterruptedException;

        synchronized，是关键字；它区分为synchronized代码块和synchronized方法。synchronized的作用是让线程获取对象的同步锁。

         */

    }

    @Override
    public void run() {
        /*
        Runnable 是一个接口，该接口中只包含了一个run()方法

        start() : 它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。
        run()：就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！



         */

    }
}

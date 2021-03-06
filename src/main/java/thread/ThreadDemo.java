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

      线程有哪几种状态？
      线程有新建，运行，就绪，阻塞，死亡等状态。
      在一个线程完整的生命周期中，它可能经历五种状态：新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）、
      终止（Zombie）

      wait和sleep的不同？
      wait()会释放对象的同步锁，而sleep()则不会释放锁。

      在Java中什么是线程调度？
      JVM调度的模式有两种：分时调度和抢占式调度。
      分时调度是所有线程轮流获得CPU使用权，并平均分配每个线程占用CPU的时间;
      抢占式调度是根据线程的优先级别来获取CPU的使用权。
      JVM的线程调度模式采用了抢占式模式。既然是抢占调度，那么我们就能通过设置优先级来“有限”的控制线程的运行顺序，
      注意“有限”一次。

      线程池:
      在程序启动的时候就创建若干线程来响应处理，它们被称为线程池，里面的线程叫工作线程。

      在后台默默地完成一些系统性的服务，比如垃圾回收线程、JIT线程就可以理解为守护线程.
      当一个Java应用内，只有守护线程时，Java虚拟机就会自然退出

      线程安全：
      public class AccountingSync implements Runnable{
        static AccountingSync instance=new AccountingSync();
        static int i=0;
        @Override
        public void run() {
        for(int j=0;j<10000000;j++){
        synchronized(instance){
        i++;
        }
        }
        }
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

        /*
        多线程中的三个核心概念：
        原子性：跟数据库事务的原子性概念差不多，即一个操作（有可能包含有多个子操作）要么全部执行（生效），
        要么全部都不执行（都不生效）。
        可见性：当多个线程并发访问共享变量时，一个线程对共享变量的修改，其它线程能够立即看到。
        可见性问题是好多人忽略或者理解错误的一点。
        顺序性：CPU虽然并不保证完全按照代码顺序执行，但它会保证程序最终的执行结果和代码顺序执行时的结果一致。

        如何保证多线程的并发问题：
        1 java如何保证原子性
        public void testLock () {
          lock.lock();
          try{
            int j = i;
            i = j + 1;
          } finally {
            lock.unlock();
          }
        }
        与锁类似的是同步方法或者同步代码块。使用非静态同步方法时，锁住的是当前实例；使用静态同步方法时，
        锁住的是该类的Class对象；使用静态代码块时，锁住的是synchronized关键字后面括号内的对象。
        下面是同步代码块示例
        public void testLock () {
          synchronized (anyObject){
            int j = i;
            i = j + 1;
          }
        }
        无论使用锁还是synchronized，本质都是一样，通过锁来实现资源的排它性。
         */
    }
}

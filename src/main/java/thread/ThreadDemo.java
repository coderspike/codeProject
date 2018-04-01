package thread;

/**
 * 基础线程demo
 */
public class ThreadDemo {
    /*
          实现多线程的两种方式:
          1、实现Runable接口
          2、继承Thread类
            [在java.lang包中定义,继承此类必须重写run()方法]
            public class Thread implements Runnable

          区别：
          1、Runable属于接口，所以可以有多个实现；Thread只有一个。
          2、实现Runable的线程类，可以被多个线程实例共享数据[每个线程一份数据]。

          线程状态：
          线程有新建，运行，就绪，阻塞，死亡等状态。
          wait()会释放对象的同步锁，而sleep()则不会释放锁。
     */

    public static void main(String[] args) {
        Thread thread = new Thread();

    }

}

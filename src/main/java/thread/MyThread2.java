package thread;

public class MyThread2 extends Thread {

    public MyThread2(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}

class Demo {
    public static void main(String[] args) {
        Thread mythread = new MyThread2("mythread");

        System.out.println(Thread.currentThread().getName() + " call mythread.run()");
        mythread.run();

        System.out.println(Thread.currentThread().getName() + " call mythread.start()");
        mythread.start();
    }
    /*
    main call mythread.run()
    main is running
    main call mythread.start()
    mythread is running
     */
}

/*
(01) Thread.currentThread().getName()是用于获取“当前线程”的名字。当前线程是指正在cpu中调度执行的线程。
(02) mythread.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
(03) mythread.start()会启动“线程mythread”，“线程mythread”启动之后，会调用run()方法；此时的run()方法是运行在“线程mythread”上。
 */

/*
start方法源码：
public synchronized void start() {
    // 如果线程不是"就绪状态"，则抛出异常！
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    // 将线程添加到ThreadGroup中
    group.add(this);

    boolean started = false;
    try {
        // 通过start0()启动线程
        start0();
        // 设置started标记
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
        }
    }
}

start()实际上是通过本地方法start0()启动线程的
start0()会新运行一个线程，新线程会调用run()方法。
private native void start0();

Thread.java中run()的代码如下：
public void run() {
    if (target != null) {
        target.run();
    }
}
target是一个Runnable对象。run()就是直接调用Thread线程的Runnable成员的run()方法，并不会新建一个线程。
 */
package thread.daemonDemo;

public class Demo {
    /*
    java 中有两种线程：用户线程和守护线程。可以通过isDaemon()方法来区别它们：如果返回false，则说明该线程是“用户线程”；否则就是“守护线程”。
    用户线程一般用户执行用户级任务，而守护线程也就是“后台线程”，一般用来执行后台任务。需要注意的是：Java虚拟机在“用户线程”都结束后会后退出。
     */
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()
                + "(isDaemon=" + Thread.currentThread().isDaemon() + ")");

        Thread t1 = new MyThread("t1");    // 新建t1
        Thread t2 = new MyDaemon("t2");    // 新建t2
        t2.setDaemon(true);                // 设置t2为守护线程
        t1.start();                        // 启动t1
        t2.start();                        // 启动t2
    }
}

/*
(01) 主线程main是用户线程，它创建的子线程t1也是用户线程。
(02) t2是守护线程。在“主线程main”和“子线程t1”(它们都是用户线程)执行完毕，只剩t2这个守护线程的时候，JVM自动退出。
 */
package thread;

public class MyRunnable implements Runnable {

    private int ticketsCount = 5;

    @Override
    public void run() {
        while (ticketsCount > 0) {
            ticketsCount--;
            System.out.println(Thread.currentThread().getName() + " 卖了一张票，还剩下：" + ticketsCount);
        }
    }
}


class TicketsTestRunnable {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread th1 = new Thread(mr, "窗口1");
        Thread th2 = new Thread(mr, "窗口2");
        Thread th3 = new Thread(mr, "窗口3");

        th1.start();
        th2.start();
        th3.start();
        /*
        实现Runnable接口的话可以做到多个线程之间的资源共享
        共享数据，应为创建Thread实例时，使用的是同一个MyRunnable类对象
        start会直接启动一个新线程，并在新线程中运行run方法
        Java不支持类的多重继承，但允许你调用多个接口。所以如果你要继承其他类，当然是调用Runnable接口好了。
         */


    }
}

package thread;

public class MyThread extends Thread {
    private int ticketsCount = 5;
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketsCount > 0) {
            ticketsCount--;
            System.out.println(name + " 卖了一张票，还剩下：" + ticketsCount);
        }
    }

}

class TicketsTestThread {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("窗口1");
        MyThread mt2 = new MyThread("窗口2");
        MyThread mt3 = new MyThread("窗口3");

        mt1.start();
        mt2.start();
        mt3.start();
        // 从结果来看，每个线程都拥有自己的5张票，其实是重复了。
    }
}
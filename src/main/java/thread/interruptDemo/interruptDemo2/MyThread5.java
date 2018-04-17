package thread.interruptDemo.interruptDemo2;

public class MyThread5 extends Thread {
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("线程被停止了！");
                return;
            }
            System.out.println("Time: " + System.currentTimeMillis());
        }
    }
}

class Run5 {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread5();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
/*
通过return 终止线程
不过还是建议使用“抛异常”的方法来实现线程的停止，因为在catch块中还可以将异常向上抛，使线程停止事件得以传播。
 */
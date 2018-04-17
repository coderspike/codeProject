package thread.interruptDemo.interruptDemo2;

public class MyThread2 extends Thread {
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) { //测试当前线程是否已经中断，并清除中断状态
                System.out.println("线程已经终止， for循环不再执行");
                break;//使用break中断
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("这是for循环外面的语句，也会被执行");
        //如何解决这个问题呢？通过抛出异常解决
    }
}

class Run2 {
    public static void main(String args[]) {
        Thread thread = new MyThread2();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

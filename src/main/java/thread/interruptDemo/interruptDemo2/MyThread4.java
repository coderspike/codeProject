package thread.interruptDemo.interruptDemo2;

public class MyThread4 extends Thread {
    public void run() {
        super.run();
        try {
            System.out.println("线程开始。。。");
            for (int i = 0; i < 10000; i++) {
                System.out.println("i=" + i);
            }
            Thread.sleep(200000);
            System.out.println("线程结束。");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到sleep，进入catch异常");
            e.printStackTrace();
        }

    }
}
/*
 如果在sleep状态下停止某一线程，会进入catch语句，并且清除停止状态值，使之变为false(调用isInterrupted()方法的结果是：false)。
 */

class Run4 {
    public static void main(String args[]) {
        Thread thread = new MyThread4();
        thread.start();
        thread.interrupt();
    }
}
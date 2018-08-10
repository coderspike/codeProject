package thread.synchronizedField;

public class SynTest {

    public static void main(String args[]) throws InterruptedException {
        SynTest synTest = new SynTest();
        synTest.test();
    }

    public void test() throws InterruptedException {
        new SynThread1().start();
        new SynThread1().start();
    }

    public void syn(String userName) throws Exception {
        synchronized (userName) {
            System.out.println("进入到同步块，userName=" + userName);
            Thread.sleep(5000);  //5秒
            System.out.println("退出同步块，userName=" + userName);
        }
    }

    class SynThread1 extends Thread {
        public void run() {
            try {
                syn("hahaha");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
/*
同步方法和同步代码块的区别是什么？
同步方法默认用this或者当前类class对象作为锁； 同步代码块可以选择以什么来加锁，比同步方法要更细颗粒度，
我们可以选择只同步会发生同步问题的部分代码而不是整个方法； 同步方法使用关键字 synchronized修饰方法，
而同步代码块主要是修饰需要进行同步的代码，用 synchronized（object）{代码内容}进行修饰；
 */
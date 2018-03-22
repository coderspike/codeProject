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
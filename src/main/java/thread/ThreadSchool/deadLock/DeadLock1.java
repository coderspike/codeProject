package thread.ThreadSchool.deadLock;

/*
创建两个字符串a和b，再创建两个线程A和B， 让每个线程都用synchronized锁住字符串（A先锁a，再去锁b；B先锁b，再锁a）
如果A锁住a，B锁住b，A就没办法锁住b，B也没办法锁住a，这时就陷入了死锁
 */
public class DeadLock1 {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}

class Lock1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock1 running");
            while (true) {
                synchronized (DeadLock1.obj1) {
                    System.out.println("Lock1 lock obj1");
                    // 获取obj1后先等一会儿，让Lock2有足够的时间锁住obj2
                    Thread.sleep(3000);
                    synchronized (DeadLock1.obj2) {
                        System.out.println("Lock1 lock obj2");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Lock2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock2 running");
            while (true) {
                synchronized (DeadLock1.obj2) {
                    System.out.println("Lock2 lock obj2");
                    Thread.sleep(3000);
                    synchronized (DeadLock1.obj1) {
                        System.out.println("Lock2 lock obj1");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
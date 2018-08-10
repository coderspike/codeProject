package thread.ThreadSchool.lock;

/*
可重入锁的demo演示，可见synchronized是可重入锁
 */
public class ReentrantTest {
    public void method1() {
        synchronized (ReentrantTest.class) {
            System.out.println("方法1获得ReentrantTest的内置锁运行了");
            method2();
        }
    }

    public void method2() {
        synchronized (ReentrantTest.class) {
            System.out.println("方法1里面调用的方法2重入内置锁,也正常运行了");
        }
    }

    public static void main(String[] args) {
        new ReentrantTest().method1();
    }
}
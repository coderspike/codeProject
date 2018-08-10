package thread.synchronizedDemo.synchronizedDemo1;

public class synchronizedDemo1_2 {

    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");  // 新建“线程t1”
        Thread t2 = new MyThread("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
}
/*
t1 loop 0
t2 loop 0
t1 loop 1
t2 loop 1
t1 loop 2
t2 loop 2
t1 loop 3
t2 loop 3
t1 loop 4
t2 loop 4
 */
package thread.synchronizedField;

class HasSelfPrivateNum {
    private int num = 0;

    //    public void addI(String username) {
//        try {
//            if (username.equals("a")) {
//                num = 100;
//                System.out.println("a set over!");
//                Thread.sleep(2000);
//            } else {
//                num = 200;
//                System.out.println("b set over!");
//            }
//            System.out.println(username + " num=" + num);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    结果
//    a set over!
//    b set over!
//    b num=200
//    a num=200
    // TODO: 2018-03-22  synchronized同步块
    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    结果
//    a set over!
//    a num=100
//    b set over!
//    b num=200
//    实验结果：两个线程访问同一个对象中的同步方法是一定是线程安全的。本实现由于是同步访问，所以先打印出a，然后打印出b
}

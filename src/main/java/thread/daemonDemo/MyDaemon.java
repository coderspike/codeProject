package thread.daemonDemo;

class MyDaemon extends Thread {
    public MyDaemon(String name) {
        super(name);
    }

    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                Thread.sleep(1);
                System.out.println(this.getName() + "(isDaemon=" + this.isDaemon() + ")" + ", loop " + i);
            }
        } catch (InterruptedException e) {
        }
    }
}

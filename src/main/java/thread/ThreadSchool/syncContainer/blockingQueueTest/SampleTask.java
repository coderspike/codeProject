package thread.ThreadSchool.syncContainer.blockingQueueTest;

public class SampleTask implements Runnable {

    private String name;

    public SampleTask(String name) {
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是" + this.name + "，正在快速运行中...");
    }

}
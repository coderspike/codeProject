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
        System.out.println("����" + this.name + "�����ڿ���������...");
    }

}
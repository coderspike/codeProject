package thread.ThreadSchool.demo;

import java.util.List;
import java.util.Random;

public class ThreadTask implements Runnable {
    private String url;
    private List<String> list;

    public ThreadTask(String url, List<String> list) {
        this.url = url;
        this.list = list;
    }

    @Override
    public void run() {
        String result = this.send(url);
        list.add(result);
    }

    //模拟接口请求
    private String send(String url) {
        try {
            Thread.sleep(1000);
            System.out.println(url + "接口请求中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "interfaceResult" + new Random().nextInt(100);
        return result;
    }
}
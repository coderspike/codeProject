package thread.ThreadSchool.demo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class ThreadTaskForReturn implements Callable {
    private String url;
    private List<String> list;

    public ThreadTaskForReturn(String url, List<String> list) {
        this.url = url;
        this.list = list;
    }

    @Override
    public String call() throws Exception {
        String result = this.send(url);
        list.add(result);
        return list.size() + "";
    }

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
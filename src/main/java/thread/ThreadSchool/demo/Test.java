package thread.ThreadSchool.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    /**
     * 模拟测试，通过线程池调用多个接口获取返回值并存储数据
     */
    public static void main(String[] args) {
//       String s= test1();//无返回值，适合一些异步操作
//        System.out.println(s);
    String s = test2();//有返回值，可判断是否执行结束
        System.out.println("-----"+s);
    }

    //测试1
    public static String  test1() {
        List<String> listUrl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listUrl.add("interface" + i);
        }
        List<String> result = Collections.synchronizedList(new ArrayList<String>());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        if (listUrl != null && listUrl.size() > 0) {
            for (String interfaceUrl : listUrl) {
                String url = interfaceUrl;
                ThreadTask threadTask = new ThreadTask(url, result);
                executorService.execute(threadTask);
            }
        }
        executorService.shutdown();
        return "1";
    }

    //测试2
    public static String test2() {
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        //模拟接口url
        List<String> listUrl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listUrl.add("interface" + i);
        }
        //存入返回值
        List<String> result = Collections.synchronizedList(new ArrayList<String>());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        if (listUrl != null && listUrl.size() > 0) {
            for (String interfaceUrl : listUrl) {
                String url = interfaceUrl;
                //创建任务
                ThreadTaskForReturn threadTask = new ThreadTaskForReturn(url, result);
                //执行任务
                Future<String> future = executorService.submit(threadTask);
                resultList.add(future);
            }
            for (Future<String> fs : resultList) {
                try {
                    //while (!fs.isDone()) ;
                    //通过GET 获取线程的执行结果，如果没有执行结束，则此get方法会处于等待状态，直到线程执行结束
                    String i=fs.get();
                    //可以通过返回值判断
                    System.out.println(i);
                    if(i.equals("1")){
                        System.out.println("###");
                        executorService.shutdown();
                        return i;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    executorService.shutdown();
//                    shutdown()
//                    shutdown并不是直接关闭线程池，而是不再接受新的任务...如果线程池内有任务，那么把这些任务执行完毕后，关闭线程池....
//                    shutdownNow()
//                    这个方法表示不再接受新的任务，并把任务队列中的任务直接移出掉，如果有正在执行的，尝试进行停止...
                }
            }
            System.out.println(result.size());
            //在这里执行存储数据操作
            System.out.println("END");
        }
        return "s";
    }
}
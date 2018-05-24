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
     * ģ����ԣ�ͨ���̳߳ص��ö���ӿڻ�ȡ����ֵ���洢����
     */
    public static void main(String[] args) {
//       String s= test1();//�޷���ֵ���ʺ�һЩ�첽����
//        System.out.println(s);
    String s = test2();//�з���ֵ�����ж��Ƿ�ִ�н���
        System.out.println("-----"+s);
    }

    //����1
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

    //����2
    public static String test2() {
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        //ģ��ӿ�url
        List<String> listUrl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listUrl.add("interface" + i);
        }
        //���뷵��ֵ
        List<String> result = Collections.synchronizedList(new ArrayList<String>());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        if (listUrl != null && listUrl.size() > 0) {
            for (String interfaceUrl : listUrl) {
                String url = interfaceUrl;
                //��������
                ThreadTaskForReturn threadTask = new ThreadTaskForReturn(url, result);
                //ִ������
                Future<String> future = executorService.submit(threadTask);
                resultList.add(future);
            }
            for (Future<String> fs : resultList) {
                try {
                    //while (!fs.isDone()) ;
                    //ͨ��GET ��ȡ�̵߳�ִ�н�������û��ִ�н��������get�����ᴦ�ڵȴ�״̬��ֱ���߳�ִ�н���
                    String i=fs.get();
                    //����ͨ������ֵ�ж�
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
//                    shutdown������ֱ�ӹر��̳߳أ����ǲ��ٽ����µ�����...����̳߳�����������ô����Щ����ִ����Ϻ󣬹ر��̳߳�....
//                    shutdownNow()
//                    ���������ʾ���ٽ����µ����񣬲�����������е�����ֱ���Ƴ��������������ִ�еģ����Խ���ֹͣ...
                }
            }
            System.out.println(result.size());
            //������ִ�д洢���ݲ���
            System.out.println("END");
        }
        return "s";
    }
}
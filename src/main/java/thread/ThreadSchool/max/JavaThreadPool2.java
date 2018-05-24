
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleCallableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JavaThreadPool2 {

    public static void main(String[] args) {
        /*
         * ����һ�������ù̶��߳������̳߳�
		 */
        ExecutorService pool = Executors.newFixedThreadPool(2);
        /*
         * ����һ��FutureList,����׷���߳�״̬
		 */
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            /*
             * ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������
			 */
            Future<String> future = pool.submit(new SimpleCallableTask(i));
            resultList.add(future);
        }

        for (Future<String> fs : resultList) {
            try {
				/*
				 * Future�������û����ɣ���һֱѭ���ȴ���ֱ��Future�������
				 */
                while (!fs.isDone()) {
                }
				/*
				 * ��ӡ�����̣߳�����ִ�еĽ��
				 */
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
				/*
				 * ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������
				 */
                pool.shutdown();
            }
        }
        while (!(pool.isTerminated())) {
        }
        System.out.println("finished all threads, and the thread pool has been terminated");
    }
}

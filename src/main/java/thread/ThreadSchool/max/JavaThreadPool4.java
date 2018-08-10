
package thread.ThreadSchool.max;


import thread.ThreadSchool.max.task.SimpleCallableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JavaThreadPool4
{

	public static void main(String[] args)
	{
		/*
		 * create a ArrayBlockingQueue for Thread Pool. �����ȴ�����
		 */
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);

		/*
		 * create a thread pool by Class 'ThreadPoolExecutor' �����̳߳أ����б�����߳���Ϊ3�����������߳���Ϊ5
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue);

		List<Future<String>> resultList = new ArrayList<Future<String>>();

		for (int i = 0; i < 10; i++)
		{

			/*
			 * submit Callable task by ExecutorService, and return Future.
			 * ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������
			 */
			Future<String> future = pool.submit(new SimpleCallableTask(i));
			resultList.add(future);
		}

		for (Future<String> fs : resultList)
		{
			try
			{
				/*
				 * loop check is the thread is done by method 'isDone()', Future�������û����ɣ���һֱѭ���ȴ���ֱ��Future�������
				 */
				while (!fs.isDone())
				{
				}

				/*
				 * print thread's result. ��ӡ�����̣߳�����ִ�еĽ��
				 */
				System.out.println(fs.get());
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				e.printStackTrace();
			}
			finally
			{
				/*
				 * execute submited task before from task Queue, but doesn't accept new task. ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������
				 */
				pool.shutdown();
			}
		}
	}
}

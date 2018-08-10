
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleRunnableTask;
import thread.ThreadSchool.max.task.SimpleThreadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaThreadPool1
{
	public static void main(String[] args)
	{
		/*
		 *  Create a fixed thread pool by Class 'Executors', method 'newFixedThreadPool'
		 *  ����һ�������ù̶��߳������̳߳�
		 */
		ExecutorService pool = Executors.newFixedThreadPool(2);

		
		Thread t1 = new SimpleThreadTask(1);

		Runnable r1 = new SimpleRunnableTask(1);
		Thread t2 = new Thread(r1);
		
		/*
		 * 	put these threads into thread pool to execute
		 *  ���̷߳�����н���ִ��
		 */
		pool.execute(t1);
		pool.execute(t2);

		pool.shutdown();

		/* loop for thread pool closed.
		 * ѭ���ȴ��̳߳���ȫ�ر�
		 */
		while (!(pool.isTerminated()))
		{
		}

		System.out.println("finished all threads, and the thread pool has been terminated");
	}
}

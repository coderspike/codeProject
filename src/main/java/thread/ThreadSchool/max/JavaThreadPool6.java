
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleThreadTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class JavaThreadPool6
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
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue)
		{
			@Override
			protected void beforeExecute(Thread t, Runnable r)
			{
				System.out.println("before execute: id = " + ((SimpleThreadTask)r).getId());
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t)
			{
				System.out.println("after execute: id = " + ((SimpleThreadTask)r).getId());
			}

			@Override
			protected void terminated()
			{
				System.out.println("finished all threads, and the thread pool has been terminated");
			}
		};

		Thread t1 = new SimpleThreadTask(1);
		Thread t2 = new SimpleThreadTask(2);

		/*
		 * submit task into thread pool
		 */
		pool.execute(t1);
		pool.execute(t2);

		pool.shutdown();

	}
}

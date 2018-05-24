
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleRunnableTask;
import thread.ThreadSchool.max.task.SimpleThreadTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JavaThreadPool3
{

	public static void main(String[] args)
	{
		/*
		 * 	create a ArrayBlockingQueue for Thread Pool.
		 *	创建等待队列
		 */
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
		
		/*
		 * 	create a thread pool by Class 'ThreadPoolExecutor'
		 *  创建线程池，池中保存的线程数为3，允许的最大线程数为5
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue);
		
		Thread t1 = new SimpleThreadTask(1);
		Runnable r1 = new SimpleRunnableTask(1);

		/*
		 * submit task into thread pool
		 */
		pool.execute(t1);
		pool.execute(r1);

		pool.shutdown();

	}
}

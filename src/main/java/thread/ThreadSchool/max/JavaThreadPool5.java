
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleCallableTask;

import java.util.concurrent.*;


public class JavaThreadPool5
{
	public static void main(String[] args)
	{
		/*
		 * Create a fixed thread pool by Class 'Executors', method 'newFixedThreadPool' 创建一个可重用固定线程数的线程池
		 */
		ExecutorService pool = Executors.newFixedThreadPool(2);

		SimpleCallableTask callTask1 = new SimpleCallableTask(1);
		SimpleCallableTask callTask2 = new SimpleCallableTask(2);

		FutureTask<String> futureTask1 = new FutureTask<>(callTask1);
		FutureTask<String> futureTask2 = new FutureTask<>(callTask2);

		/*
		 * put these threads into thread pool to execute 将线程放入池中进行执行
		 */
		pool.execute(futureTask1);
		pool.execute(futureTask2);

		pool.shutdown();

		while (true)
		{
			try
			{
				if (futureTask1.isDone() && futureTask2.isDone())
				{
					/*
					 * loop for thread pool closed. 循环等待线程池完全关闭
					 */
					pool.shutdown();
					System.out.println("finished all threads, and the thread pool has been terminated");
					return;
				}
				
				String s1 = futureTask1.get(200L, TimeUnit.MILLISECONDS);
				if (s1 != null)
				{
					System.out.println("futureTask1 output = " + s1);
				}
				
				String s2 = futureTask2.get(200L, TimeUnit.MILLISECONDS);
				if (s2 != null)
				{
					System.out.println("FutureTask2 output = " + s2);
				}
			}
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			catch (TimeoutException e)
			{
				//do nothing
			}
		}
	}
}

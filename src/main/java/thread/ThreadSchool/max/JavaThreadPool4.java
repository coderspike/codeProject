
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
		 * create a ArrayBlockingQueue for Thread Pool. 创建等待队列
		 */
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);

		/*
		 * create a thread pool by Class 'ThreadPoolExecutor' 创建线程池，池中保存的线程数为3，允许的最大线程数为5
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue);

		List<Future<String>> resultList = new ArrayList<Future<String>>();

		for (int i = 0; i < 10; i++)
		{

			/*
			 * submit Callable task by ExecutorService, and return Future.
			 * 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
			 */
			Future<String> future = pool.submit(new SimpleCallableTask(i));
			resultList.add(future);
		}

		for (Future<String> fs : resultList)
		{
			try
			{
				/*
				 * loop check is the thread is done by method 'isDone()', Future返回如果没有完成，则一直循环等待，直到Future返回完成
				 */
				while (!fs.isDone())
				{
				}

				/*
				 * print thread's result. 打印各个线程（任务）执行的结果
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
				 * execute submited task before from task Queue, but doesn't accept new task. 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
				 */
				pool.shutdown();
			}
		}
	}
}

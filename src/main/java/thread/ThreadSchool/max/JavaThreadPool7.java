
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleThreadTask;

import java.util.concurrent.*;


public class JavaThreadPool7
{

	public static void main(String[] args) throws InterruptedException
	{
		/*
		 * create a ArrayBlockingQueue for Thread Pool. 
		 * 创建等待队列
		 */
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);

		/*
		 * create a thread pool by Class 'ThreadPoolExecutor'.
		 * 创建线程池，池中保存的线程数为3，允许的最大线程数为5
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue, Executors.defaultThreadFactory(),
			new RejectedExecutionHandler() {

				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
				{
				    /*
				     * print and discard
				     * 输出该任务描述并丢弃该任务
				     */
	                System.out.println(r.toString()+" is discard");					
				}
			
		});

        for(int i=0;i<10;i++){
        	Thread task = new SimpleThreadTask(i);
            pool.execute(task);
            if (i == 6) {
        		//关闭线程池   
        		pool.shutdown();
            }
        }
	}
}

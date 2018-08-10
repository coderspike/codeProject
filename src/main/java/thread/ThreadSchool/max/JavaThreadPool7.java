
package thread.ThreadSchool.max;

import thread.ThreadSchool.max.task.SimpleThreadTask;

import java.util.concurrent.*;


public class JavaThreadPool7
{

	public static void main(String[] args) throws InterruptedException
	{
		/*
		 * create a ArrayBlockingQueue for Thread Pool. 
		 * �����ȴ�����
		 */
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);

		/*
		 * create a thread pool by Class 'ThreadPoolExecutor'.
		 * �����̳߳أ����б�����߳���Ϊ3�����������߳���Ϊ5
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, bqueue, Executors.defaultThreadFactory(),
			new RejectedExecutionHandler() {

				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
				{
				    /*
				     * print and discard
				     * �������������������������
				     */
	                System.out.println(r.toString()+" is discard");					
				}
			
		});

        for(int i=0;i<10;i++){
        	Thread task = new SimpleThreadTask(i);
            pool.execute(task);
            if (i == 6) {
        		//�ر��̳߳�   
        		pool.shutdown();
            }
        }
	}
}

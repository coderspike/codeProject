
package thread.ThreadSchool.max.task;

public class SimpleRunnableTask implements Runnable
{

	private int id;

	public SimpleRunnableTask(int id)
	{
		this.id = id;
	}
	
	@Override
	public void run()
	{	
		//task
		System.out.println("Runnable Thread: " + id + " is running...");
	}

}
 

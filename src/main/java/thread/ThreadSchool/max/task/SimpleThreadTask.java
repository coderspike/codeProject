
package thread.ThreadSchool.max.task;

public class SimpleThreadTask extends Thread
{

	private int id;

	public SimpleThreadTask(int id)
	{
		this.id = id;
	}

	@Override
	public void run()
	{
		//task
		System.out.println("Simple Thread: " + id + " is running...");
	}

}

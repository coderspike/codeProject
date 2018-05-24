
package thread.ThreadSchool.max.task;

import java.util.concurrent.Callable;

public class SimpleCallableTask implements Callable<String>
{

	private int id;

	public SimpleCallableTask(int id)
	{
		this.id = id;
	}

	@Override
	public String call() throws Exception
	{
		//Task
		System.out.println("Callable Thread: " + id + " is running...");
		return "Callable Thread: " + id + " done!";
	}

}
 

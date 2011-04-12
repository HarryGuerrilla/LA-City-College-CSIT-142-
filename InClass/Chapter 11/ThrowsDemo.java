//What is the result when you compile and run the following code?
public class ThrowsDemo
{
	static void throwMethod()
    throws IllegalAccessException
	{
		  System.out.println("Inside throwMethod.");
		  throw new IllegalAccessException("demo");
	}
	public static void main(String args[])
	{
	   try
		{
					throwMethod();
		}
		catch (IllegalAccessException e)
		{
					System.out.println("Caught " + e);
	   }
	}
}

 /*
 Compilation error
 Runtime error
 Compile successfully, nothing is printed.
 Inside throwMethod. followed by caught: java.lang.IllegalAccessException: demo
*/

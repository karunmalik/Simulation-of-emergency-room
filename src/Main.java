import java.lang.*;
import java.util.*;

public class Main 
{
	public static Semaphore Nurses = new Semaphore(3);		/* Group1 has 3 machines */
	public static Semaphore CWDocs = new Semaphore(3);		/* Group2 has 2 machines */
	public static Semaphore Lab = new Semaphore(1);		/* Group3 has 4 machines */
	public static NEA_Semaphore NEADocs = new NEA_Semaphore(3);		
	private static DataCollection dc;

	public static void main(String[] arg)
	{
		Random IA = new Random(0); 		
		//System currentSystem;
		DataCollection dc = new DataCollection(); 		
		
 		Thread S = new CreateThread(Nurses,NEADocs,CWDocs,Lab, dc,IA);
		S.start();
		System.out.println("Simulation started... " );
	}
}


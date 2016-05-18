import java.lang.*;
import java.util.*;

public class CreateThread extends Thread
{
	private static Semaphore Nurses, CWDocs, Lab;		
	private static NEA_Semaphore NEADocs;	
	private static DataCollection Data;
	private static Random rand0;
	private static System mySystem;
	
	
	public CreateThread(Semaphore aNurses, NEA_Semaphore aNEADocs, Semaphore aCWDocs, Semaphore aLab, DataCollection d, Random rand)
	{

		rand0 = rand;
		Nurses=aNurses;
		NEADocs=aNEADocs;
		CWDocs=aCWDocs;
		Lab=aLab;
		Data=d;
		
	}
	
	
	private static void interval(long Starttime, Long Endtime)
	{
		double intervalTime = (-1.0 * Math.log(1.0 - rand0.nextFloat()*1.0)/ 0.01);  /* 1min = 10ms */
		 /* 1min = 10ms */
 /* 1min = 10ms */
 /* 1min = 10ms */
		try
		{
			sleep((long)intervalTime);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
	}



	public void run()
	{
		
		long startTime = 0;
		long endTime = 0;
		float jobType;
		CW_Patient type1;
		NEA_Patient type2;
		
				
		startTime = mySystem.currentTimeMillis();
		
		
		endTime = mySystem.currentTimeMillis();
		
		
		while ((endTime - startTime)<=10000)
		{
			jobType = rand0.nextFloat();
			if (jobType<0.8)
			{
				type1 = new CW_Patient(Nurses,CWDocs, Data);
				type1.start();
			}
			else  
			{
				type2 = new NEA_Patient( Nurses, NEADocs,Lab, Data);
				type2.start();
			}
			
			
			interval(startTime, mySystem.currentTimeMillis());
			
			endTime = mySystem.currentTimeMillis();
			
		}
		
		System.out.println("Simulation time is over..." );
		System.out.println(" " );
		System.out.println("------------------------- Simulation Results -------------------------" );
		System.out.println(" " );
		Data.Report();
		System.out.println(" " );
		System.out.println("Simulation is successful!!!" );
		
	}
	
}

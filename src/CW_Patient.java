
import java.lang.*;
import java.util.*;

public class CW_Patient extends Thread 
{
	private static Semaphore CWDocs, Nurses, NEADocs, Lab;
	
	private static DataCollection Data;
	private static System currentTime;
	private static Random randomA = new Random();
	
	

	private static void MoveToNurses()
	{
		long tbegN; long tendN;
		tbegN=currentTime.currentTimeMillis();
	
		double NursesTime = 150-50+2*50*randomA.nextFloat(); /* 15+-10 mins, define 1 min=10ms */
	
	 try
		{
			sleep((long)NursesTime);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
	 tendN =currentTime.currentTimeMillis();
		Data.getNW(tendN-tbegN);
	}
	private static void MoveToCWDocs()
	{
		long tbegD; long tendD;
		tbegD=currentTime.currentTimeMillis();
		double MoveToCWDocs = 300-150+2*150*randomA.nextFloat(); /*  */
		try
		{
			sleep((long)MoveToCWDocs);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		tendD=currentTime.currentTimeMillis();
		Data.getCW(tendD-tbegD);
	}

	

	
	public CW_Patient(Semaphore group1, Semaphore group3,  DataCollection d)
	{
		Nurses = group1;
		CWDocs = group3;
		
		
		Data = d;
		// Thread can be set as a daemon thread in the constructor
        setDaemon(true);
        

	}
	
	public void run()
	{
			
		long tBeg, tCWDocs0, tCWDocs1, tNurses0, tNurses1, tNEADocs0, tNEADocs1, tLab0, tLab1, tStO0, tStO1, tEnd;
		
		tBeg = currentTime.currentTimeMillis(); 
		
		//move to Nurses
		tNurses0 = currentTime.currentTimeMillis(); 
		if(Nurses.getValue()<=0)
		{
			Data.getG1WQ();
		
		}	
		Nurses.take();
		tNurses1 = currentTime.currentTimeMillis();
		Data.getG1WT(tNurses1-tNurses0); //get CWDocs waiting time  		
  		
  		MoveToNurses();
  	  	Nurses.release();	
  				
  		  
  		
  		//move to CWDocs
		tCWDocs0 = currentTime.currentTimeMillis(); 
		if(CWDocs.getValue()<=0)
		{
			Data.getG3WQ();
		}	   			
		CWDocs.take();
		tCWDocs1 = currentTime.currentTimeMillis();
		Data.getG3WT(tCWDocs1-tCWDocs0); //get Nurses waiting time
		 		      		
  		MoveToCWDocs();
  	
  		CWDocs.release();  
  		
  		
  	
  		
  		
  		//CW Patient total time in Hospital
  		tEnd = currentTime.currentTimeMillis();  		
  		Data.getAR(tEnd - tBeg); 
      	
      	//System.out.println("T1 Running");
    }
}


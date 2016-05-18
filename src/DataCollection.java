import java.lang.*;
import java.util.*;

public class DataCollection
{
	
	private long CWPatient = 0;
	private long NEAPatient = 0;
	
	
	private long NurseWaitTime = 0;
	private long NEADocsWaitTime = 0;
	private long CWDocsWaitTime = 0;
	private long LabWaitTime = 0;
	
	private long NurseBusyTime = 0;
	private long NEADocsBusyTime = 0;
	private long CWDocsBusyTime = 0;
	private long LabBusyTime = 0;
	
	
	
	
	private int numA = 0, numB = 0;
		
	private int G1WQ = 0, G2WQ = 0, G3WQ=0, G4WQ=0;
	private long StartTime, EndTime;
	
	private System rightNow;

	public DataCollection()
	{
		StartTime = rightNow.currentTimeMillis();
	}
	
	//methods to get residence time
	public synchronized void getAR(long t)
	{
		CWPatient += t;
		numA++;
	}
	
	public synchronized void getBR(long t)
	{
		NEAPatient += t;
		numB++;
	}
	
	
	
	//methods to get waiting time and queue
	public synchronized void getG1WT(long t)
	{
		NurseWaitTime += t;
	}
	public synchronized void getG1WQ()
	{		
		G1WQ++;
	}
	
	public synchronized void getG2WT(long t)
	{
		NEADocsWaitTime += t;
	}
	public synchronized void getG2WQ()
	{		
		G2WQ++;
	}
	
	public synchronized void getG3WT(long t)
	{
		CWDocsWaitTime += t;
	}
	public synchronized void getG3WQ()
	{		
		G3WQ++;
	}	
	
	public synchronized void getG4WT(long t)
	{
		LabWaitTime += t;
	}
	public synchronized void getG4WQ()
	{		
		G4WQ++;
	}
	public synchronized void getNW(long t)
	{
		NurseBusyTime+=t;
	}
	public synchronized void getCW(long t)
	{
		CWDocsBusyTime+=t;
	}
	public synchronized void getNEA(long t)
	{
		NEADocsBusyTime+=t;
	}
	public synchronized void getLab(long t)
	{
		LabBusyTime+=t;
	}
	
	
	
	
	//Storage overflowing waitings
	
	
	public synchronized void Report()
	{
		EndTime = rightNow.currentTimeMillis();
		long totaltime=(EndTime - StartTime) ;
		
        System.out.println("Simulation Time = " + ((EndTime - StartTime) / 10) + " " + "Start Time = " + StartTime/10 + " " + "End Time = " + EndTime/10);
        
        System.out.println(" " );
        
        System.out.println("The average  time for CWPatient  = " + (CWPatient / numA / 10) );
        System.out.println("The average  time for NEA Patient = " + (NEAPatient / numB / 10)) ;
        
System.out.println(" " );
		
		System.out.println("The average waiting time for Nurse = " + ((double)NurseWaitTime /G1WQ /3 /10));
		System.out.println("The average waiting time for NEADoc = " + ((double)NEADocsWaitTime / G2WQ / 3 /10));
		System.out.println("The average waiting time for CWDoc = " + ((double)CWDocsWaitTime / G3WQ / 3 /10));
		System.out.println("The average waiting time for Lab = " + ((double)LabWaitTime / G4WQ / 1 /10));
		
		System.out.println(" " );
		
		System.out.println("Fraction of Time Nurses were busy = " + ( (double)(totaltime-NurseWaitTime))/ totaltime);
		System.out.println("Fraction of Time CWDocs were busy = " + ( (double)(totaltime-CWDocsWaitTime)/ totaltime));
		System.out.println("Fraction of Time NEADocs were busy = " + ( (double)(totaltime-NEADocsWaitTime)/ totaltime));
		System.out.println("Fraction of Time Lab was busy = " + ( (double)(totaltime-LabWaitTime)/ totaltime));
		System.out.println(" " );

		System.out.println("The average waitings for Nurses = " + (G1WQ / 3));
		System.out.println("The average waitings for NEADocs = " + (G2WQ / 3));
		System.out.println("The average waitings for CWDocs = " + (G3WQ / 3));
		System.out.println("The average waitings for Lab = " + (G4WQ ));
		
		System.out.println(" " );
		
		
		

		
		
		System.out.println(" " );
		
		

	}
}


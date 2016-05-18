import java.lang.*;
import java.util.*;

public class NEA_Patient extends Thread 
{
	private static Semaphore Nurses,  Lab;
	private static NEA_Semaphore NEADocs;
	private  int docappointed;
	
	private static DataCollection Data;
	private static System currentTime;
	private static Random randomB = new Random(2);
 
   

	private static void MoveToNurses()
	
	{
		
		
		 
		double NursesTime = 150-50+2*50*randomB.nextFloat(); /* 15+-10 mins, define 1 min=10ms */
		try
		{
			sleep((long)NursesTime);	
			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		
		
		
	}

	private static void MoveToNEADocs()
	{
		long tbegD; long tendD;
		tbegD=currentTime.currentTimeMillis();
		double NEADocsTime = 450-100+2*100*randomB.nextFloat(); /* 45+-10 mins*/
		try
		{
			sleep((long)NEADocsTime);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		tendD=currentTime.currentTimeMillis();
		Data.getNEA(tendD-tbegD);
	}

	
	private static void MoveToLab()
	{
		
	
		double LabTime = 300-100+2*100*randomB.nextFloat(); /*30+-10 mins*/
		long tbegL; long tend;
		tbegL=currentTime.currentTimeMillis();
		try
		{
			sleep((long)LabTime);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		tend=currentTime.currentTimeMillis();
		Data.getLab(tend-tbegL);
	}
	private static void MoveToNEADoc2()
	{
		long tbegD; long tendD;
		tbegD=currentTime.currentTimeMillis();
		double NEADoc2Time = 150-50+2*50*randomB.nextFloat();
		try
		{
			sleep((long)NEADoc2Time);			
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		tendD=currentTime.currentTimeMillis();
		Data.getNEA(tendD-tbegD);
		
	}

	
	public NEA_Patient(Semaphore group4, NEA_Semaphore group1, Semaphore group3,  DataCollection d)
	{
		Nurses = group4;
		NEADocs = group1;
		Lab = group3;
		
		Data = d;
		setDaemon(true);
	}
	
	public void run()
	{
			
		long tBeg, tNurses0, tNurses1, tNEADocs0, tNEADocs1, tLab0, tLab1, tNEA20, tNEA21, tEnd;
		
		tBeg = currentTime.currentTimeMillis(); 
		
		//move to Nurses
		tNurses0 = currentTime.currentTimeMillis(); 
		if(Nurses.getValue()<=0)
		{ 
			Data.getG1WQ();
			
		}	   		
		Nurses.take();
		tNurses1 = currentTime.currentTimeMillis();
		Data.getG1WT(tNurses1-tNurses0); //get Nurses waiting time  		
  		
  		MoveToNurses();
  		Nurses.release(); 
  		
  		  
  		
  		//move to NEADocs
		tNEADocs0 = currentTime.currentTimeMillis(); 
		if(NEADocs.getValue()<=0)
		{
			Data.getG2WQ();
		}	   	   			
		docappointed = NEADocs.take();
		tNEADocs1 = currentTime.currentTimeMillis();
		Data.getG2WT(tNEADocs1-tNEADocs0); //get NEADocs waiting time
		 		      		
  		MoveToNEADocs();
  		System.out.println("Release Doc"+docappointed);
  		NEADocs.release(docappointed);  		
  	
 
		
if(Math.random() <0.8){
  		
  		//move to Lab
		tLab0 = currentTime.currentTimeMillis(); 
		if(Lab.getValue()<=0)
		{
			Data.getG4WQ();
		}	   	
		Lab.take();    			
		tLab1 = currentTime.currentTimeMillis();
		Data.getG4WT(tLab1-tLab0);//get Lab waiting time	
  		     		
  		MoveToLab();      		
  		Lab.release(); 
  		
  		//move to NEADoc2 again
  		tNEA20= currentTime.currentTimeMillis();
  		if(NEADocs.getValue()<=0)
  			Data.getG2WQ();
  		if(docappointed==1){
  			NEADocs.take1();
  			NEADocs.release(1);
  			System.out.println("Release NEADoc"+docappointed);
  		}
  		else if(docappointed==2){
  			NEADocs.take2();
  			NEADocs.release(2);
  			System.out.println("Release NEADoc"+docappointed);
  		}
  		else if(docappointed==3){
  			NEADocs.take3();
  			NEADocs.release(3);
  			System.out.println("Release NEADoc"+docappointed);
  		}
  		else if(docappointed==0)
  			System.out.println("ERROR");
  			
  		
  		tNEA21=currentTime.currentTimeMillis();
  		Data.getG2WT(tNEA21-tNEA20);
  		
}
  	
  		
  		//NEA Patient Time in Hospital
  		tEnd = currentTime.currentTimeMillis();  
  		Data.getBR(tEnd - tBeg); 
  		
      	//System.out.println("T2 Running");
    }
}

      

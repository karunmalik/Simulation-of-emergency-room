public class NEA_Semaphore 
{
  private int value;
  int value1 =1;// value1 , value2, value3 here represents the doctors. 1 signifies doctor is available
  //while 0 signifies that doc is busy.
	int value2 = 1;
	int value3 =1;
	
	int ru=0;

  

  public NEA_Semaphore(int v)
	{
		value = v;
	}
	
	public int getValue() 
	{ 
    	return value; 
	} 
  
  
    synchronized int take() 
    {
    	
    	
    		while(value <= 0) 
        	{
          		try 
          		{
            		wait();
          		} 
          		catch(InterruptedException e) 
          		{
            		System.out.println(e);
          		}
        	}
    		System.out.println("1 indicates doc is free and 0 indicates doc is busy ");
    		System.out.println("Total NEA Docs Free: "+value +"Doc 1:  "+value1 +"Doc 2:  "+value2+"Doc 3: "+value3);
    		
    		if(value1==1){
    	    	take1();
    	    	return 1;
    	    	
    		}
    	    	else if(value2==1){
    	    		take2();
    	    		return 2;
    	    	}
    	    	else if(value3==1){
    	    		take3();
    	    		return 3;
    	    	}
    	    	
    	    		
    	    	else
    	    	{
    	    		
    	    		return 0;
    	    	}
    	}
    	
   
    	
  	

    synchronized void take1() 
    {
    	while(value1 <= 0 && value<=0) 
    	{
      		try 
      		{
        		wait();
      		} 
      		catch(InterruptedException e) 
      		{
        		System.out.println(e);
      		}
    	}
    
    	value1--;
    	value--;
    	
  	}
    synchronized void take2() 
    {
    	while(value2 <= 0 && value<=0) 
    	{
      		try 
      		{
        		wait();
      		} 
      		catch(InterruptedException e) 
      		{
        		System.out.println(e);
      		}
    	}
    	value--;
    	value2--;
    	
  	}
    synchronized void take3() 
    {
    	while(value3 <= 0 && value<=0) 
    	{
      		try 
      		{
        		wait();
      		} 
      		catch(InterruptedException e) 
      		{
        		System.out.println(e);
      		}
    	}
    	value--;
    	value3--;
    	
  	}
   
    
  	synchronized void release(int i) 
  	{
  		
    	
    	
    	if( i==1)
    		value1++;
    	if( i==2)
    		value2++;
    	if(i==3)
    		value3=value3+1;
    	
    		
    	if(i==0)
    		System.out.println("ERROR");
    	
    	value = value+1;
    	notifyAll();
   	}
  	
}

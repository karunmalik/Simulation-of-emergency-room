public class Semaphore 
{
  	private int value;
  	
  	

  	public Semaphore(int v)
	{
		value = v;
	}
	
	
	public int getValue() 
	{ 
 	return value; 
	} 
  
  
    synchronized void take() 
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
    	value--;
  	}

	synchronized void release() 
	{ 		
			value=value+1;
			
			notifyAll();    
	}
}

package algstudent.s3;

/*  K = 0 -> complexity of non recursive calls
	b = 2 -> constant
	a = 4 -> recursive calls
	O(n^logb(a))


 */
public class Division5 {
	public static long rec5 (int n)
	{
   
     
     if(n < 1)
    	 return 1; //O(1)
   
     
     
     
	 return rec5(n/2) + rec5(n/2) + rec5(n/2) + rec5(n/2);   
	}
	
	public static void main (String arg []) 
	{
		 long t1,t2,cont = 0;	 
		 for (int n=1000;n<=10000000;n*=2)
		 {
			  t1 = System.currentTimeMillis ();
			   
			  cont = rec5(n);
			      
			  t2 = System.currentTimeMillis ();
			
			  System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);	
		 }  // for
	} // main
} //class
import java.util.LinkedList;
public class A2 {
	private static boolean  primoA2(int m) {
	
	
	   
	    for (int i = 2; i < m; i++)
	        if (m%i==0)
	            return false;
	    return true;
	}
	
	private static LinkedList<Integer> listadoPrimos(long n) {
		LinkedList<Integer> primes = new LinkedList<Integer>();
		
		  for (int i = 2; i < n+1; i++)
			        if(primoA2(i))
			            primes.add(i);
			    return primes;
	}
	    
	public static void main(String[] args){
		
		
		 long n= 10000;
		
		for(int i = 0; i < 7; i++) {
			 long time = System.currentTimeMillis();
			 LinkedList<Integer> primes = listadoPrimos(n);
			 long time2 = System.currentTimeMillis();
			 System.out.println("n ="+ n+ "***"+ "time ="+ (time2-time) + "milliseconds)");
			
			n = n*2;
		}
	}
}

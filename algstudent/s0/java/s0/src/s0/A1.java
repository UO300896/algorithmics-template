package s0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class A1 {

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
	
	
	private static LinkedList<Integer> listadoPrimos(long n) {
		  
		LinkedList<Integer> primes = new LinkedList<Integer>();
		
	    for (int i = 2; i < n+1; i++) {
	    	 if(primoA1(i))
		            primes.add(i);
	    }
	       
	    return primes;
	}


	private static boolean primoA1(int m) {
		 
	   boolean p = true;
	    for(int i = 2; i < m; i++) {
	    	  if(m%i == 0)
		            p = false;
	    }
	      
	    return p;
	}
	

	 
	  

}

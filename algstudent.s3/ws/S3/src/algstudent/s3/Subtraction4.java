package algstudent.s3;

/* 
 * Complexity of non recursive part = O(n^2)
 * 1 recursive call per iteration
 * b does not matter
 */
public class Subtraction4 {
	public static long rec4(int n) {
		long cont = 0;
		if(n <= 0)
			return 1;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				cont++;
		
			
		rec4(n - 1);
			
		
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		for (int n = Integer.parseInt(arg[0]); n <= 100000; n*=2) {
			t1 = System.currentTimeMillis();

			cont = rec4(n);

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class
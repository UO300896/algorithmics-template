package algstudent.s12;

public class Loop5 {
	public static long loop5(long n) {
		long cont = 0;
		for (int i = 1; i <= n; i++) // O(n)
			for (int j = 1; j <= n; j++) // O(n)
				for (long k = 1; k <= n; k *= 2) // O(logn)
					for (long l = 1; l <= n; l *= 2) //O(logn)
						cont++;

		return cont; //O(n^2 * log^2(n))
	}

	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;

		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (int n = 100; n <= 819200; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop5(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes + "\t\t" + c);
		} // for
	} // main
}
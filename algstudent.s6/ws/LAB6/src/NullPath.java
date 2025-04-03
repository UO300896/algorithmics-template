import java.util.Arrays;
import java.util.Random;

public class NullPath {

	private int[][] matrix;
	
	boolean found = false;
	double p1 = 0.7;
	double p2 = 1 - p1;
	int minWeight = 10;
	int maxWeight = 99;
	int cost = 0;
	int tolerance = 5;
	int weight = 0;
	int[] sol;
	public NullPath(int n) {
		
		Random r = new Random();
		matrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				matrix[i][j] = r.nextDouble() <= p1? r.nextInt(minWeight, maxWeight) : r.nextInt(minWeight, maxWeight)*-1;
				
			}
		}
		
		for(int i = 0; i < n; i++)
			System.out.println(Arrays.toString(matrix[i]));
	
		sol = new int[matrix.length];
		backtracking();
	}
	
	
	private boolean backtracking() {
		found = backtracking(matrix, 0);
			if (!found)
				System.out.println("THERE IS NO SOLUTION");
		return found;
	}
	
	
	private boolean backtracking(int[][] matrix2, int origin) {
	
	
	for(int i = origin+1; i < matrix2.length; i++) {
		weight += matrix2[origin][i];
		sol[i] = i;
		if(backtracking(matrix2, i))
			break;
		
	}
	if (origin == matrix.length-1 && isSolution(weight)) {
		
		
		System.out.println(Arrays.toString(sol)); //make things with the solution
		found = true;
		
	}
	else {
		//for all the j children of state
	
		if (!found && origin < matrix2.length-1) {
			weight -= matrix2[origin][matrix2.length-1];
			backtracking(matrix2, origin +1);
		}
			
		}
	
	return found;
	}


	private boolean isSolution(int weight) {
		return weight <= cost + tolerance && weight >= cost - tolerance;
	}
}

import java.util.Arrays;
import java.util.Random;

public class NullPath {

	private int[][] matrix;
	private Boolean[] visited;
	boolean found = false;
	double p1 = 0.7;
	double p2 = 1 - p1;
	int minWeight = 10;
	int maxWeight = 99;
	int cost = 0;
	int tolerance = 99;
	int weight = 0;
	int j;
	int[] sol;
	public NullPath(int n) {
		visited = new Boolean[n];
		Random r = new Random();
		matrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				matrix[i][j] = r.nextDouble() <= p1? r.nextInt(minWeight, maxWeight) : r.nextInt(minWeight, maxWeight)*-1;
				
			}
			visited[i] = false;
		}
		
		for(int i = 0; i < n; i++)
			System.out.println(Arrays.toString(matrix[i]));
	
		sol = new int[matrix.length];
		backtracking();
		
	}
	
	
	

	
	private boolean backtracking() {
	    int n = matrix.length;
	   

	    boolean success = backtracking(matrix, 0, n - 1);

	    if (!success)
	        System.out.println("THERE IS NO SOLUTION");

	    else
	    	 System.out.println("Found solution: " + Arrays.toString(sol));
	    return success;
	}





	private boolean backtracking(int[][] matrix2, int current, int target) {
	    visited[current] = true;
	    sol[j++] = current; 

	    if (current == target) {
	        if (Arrays.stream(visited).allMatch(Boolean::booleanValue)) {
	            if (isSolution(weight)) {
	               
	                found = true;
	                return true;
	            }
	        }
	    } else {
	        for (int next = 0; next < matrix2.length; next++) {
	            if (!visited[next]) {
	                weight += matrix2[current][next];
	                if (backtracking(matrix2, next, target))
	                    return true; 
	                weight -= matrix2[current][next]; 
	            }
	        }
	    }

	  
	    visited[current] = false;
	    sol[--j] = -1; 
	    return false;
	}



	private boolean isSolution(int weight) {
		System.out.println("Solution attempt: " + weight + Arrays.toString(sol) );
		return weight <= cost + tolerance && weight >= cost - tolerance && Arrays.stream(visited)
                .allMatch(Boolean::booleanValue);
	}
}

import java.util.Arrays;
import java.util.Random;

public class NullPath {

	//EXAM EXERCISES:
	//EX1: LET BE TOLERANCE IN RANGE [-75, 74]
	//EX2: LET BE HAVING AN EVEN NUMBER AS WEIGHT OR ODD NUMBER A CONDITION WHEN CHECKING NEXT NODO
	//THIS IS: NODE0 40 (EVEN) -> NODE 31 (ODD)...
	
	private int[][] matrix;
	private Boolean[] visited;
	boolean found = false;
	double p1 = 0.7;
	double p2 = 1 - p1;
	int minWeight = 10;
	int maxWeight = 99;
	int cost = 0;
	int tolerance = 75; //Ex1: Tolerance = [-75, 74]
	int weight = 0;
	int j;
	int k;
	int[] sol;
	int[] weights;
	private boolean isEven = true; //Ex2: Atrribute to know  if prev node has an even weight
	private boolean isFirst = true; //Ex2: Attribute to know if current node is the first node, because first node needs no check for prev even number
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
		weights = new int[matrix.length-1]; //Added for output pourposes
		backtracking();
		
	}
	
	
	

	
	private boolean backtracking() {
	    int n = matrix.length;
	   
	   
	    boolean success = backtracking(matrix, 0, n - 1);

	    if (!success)
	        System.out.println("THERE IS NO SOLUTION");

	    else
	    	 {
	    	System.out.println("Found solution: " + Arrays.toString(sol));
	    	System.out.println("Found weights: " + Arrays.toString(weights)); }
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
	        for (int next = 1; next < matrix2.length; next++) {
	        	
	            if (!visited[next] && (current == 0 && target == matrix.length-1 || calculateEven(current, next)) ) //Ex2: added conditions
                	 {
	            	
	                weight += matrix2[current][next];
	                weights[k++] = matrix2[current][next];
	                isEven = matrix2[current][next] % 2 == 0; //Ex2: updates
	                
	                if (backtracking(matrix2, next, target))
	                		return true; 
	                weight -= matrix2[current][next]; 
	                weights[--k] = -1;
	            }
	        }
	    }

	  
	    visited[current] = false;
	    sol[--j] = -1; 
	   
	    return false;
	}



	
	





	private boolean calculateEven(int current, int next) { //Ex2: Method added to calculate if current element meets condition with respect to previous element
		return (isEven ? (matrix[current][next] % 2 != 0) : (matrix[current][next] % 2 == 0)); //Ex2: if prev is even, now it must be odd...
	}





	private boolean isSolution(int weight) {
		System.out.println("Solution attempt: " + weight + Arrays.toString(sol) );
		
		return weight < cost + tolerance && weight >= cost - tolerance && Arrays.stream(visited)
                .allMatch(Boolean::booleanValue);
		//Ex1: weight < cost + tolerance (weight <= 74), weight >= cost - tolerance (weight >= -75),
		// SO tolerance -> [-75, 74]
	}
}

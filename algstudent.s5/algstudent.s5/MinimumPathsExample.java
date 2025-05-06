package labs.en._25.algstudent.s5;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPathsExample {
	private static String[] v;

	private static int[][] weights;
	private static int n;
	private static int[][] costs;
	private static int[][] p;
	public final static int INDEX_NOT_FOUND = -1;
	public final static int EMPTY = -1;
	public final static double INFINITE = Double.POSITIVE_INFINITY;

	public static void main(String arg[]) {
		n = 5; //v of example graph
		v = new String[n];
		
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = weights.clone();
		p = new int[n][n];
		
		for(int i = 0; i < p.length; i++)
			for(int j = 0; j < p.length; j++)
				p[i][j] = -1;
		
		fillInWeights(weights); //weights for the example
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);

		floyd();

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different v):");
		System.out.println();
		for (int source = 0; source <= n-1; source++)
			for (int target = 0; target <= n-1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					minimumPath(v, weights, costs, p, source, target);
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
				}

	}

	private static void initsFloyd() {
		for(int origin = 0; origin < n; origin++) {
			for(int target = 0; target < n; target++) {
				p[origin][target] = EMPTY;
				if(existsEdge(v[origin], v[target])) {
					costs[origin][target] = weights[origin][target];
				}
					
				else
					costs[origin][target] = 10000;
				if(target == origin)
					costs[origin][target] = 0;
				
				
			}
		}
	}
	/**
	 * returns whether it exists an edge between two nodes
	 * returns constant INDEX_NOT_FOUND if one node does not exist
	 * @return
	 */
	public static boolean existsEdge(String origin, String destination){
		
		
		int row = getNode(origin);
		int col = getNode(destination);
		if (row == INDEX_NOT_FOUND || col == INDEX_NOT_FOUND) {
			return false;
		}
		return weights[row][col] < 10000000;
	}
	
	/**
	 * Returns the index of the node
		If the node does not belong to the graph, returns -1 
	 * @param element
	 * @return
	 * @throws NullPointerException()
	 */
	public static int getNode(String element){
		
		
		if (element == null) {
			throw new NullPointerException(element + " does not belong to the graph");
		}
		
		for(int i = 0; i < n; i++) {
			if(v[i].equals(element)) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}


	
	/**
	 * does floyd
	 */
	public static void floyd() {
    initsFloyd();
    int newCost;

    for (int pivot = 0; pivot < n; pivot++) {
        for (int origin = 0; origin < n; origin++) {
            for (int target = 0; target < n; target++) {
                if (costs[origin][pivot] < 10000 && costs[pivot][target] < 10000) {
                	
                    newCost = costs[origin][pivot] + costs[pivot][target];
                    if (newCost < costs[origin][target]) {
                        costs[origin][target] = newCost;
                        if(p[pivot][target] != EMPTY)
							p[origin][target] = p[pivot][target];
						else
							p[origin][target] = pivot;
					}
                }
             
            }
        }
    }
}



	static void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] steps, int source, int target) {
		// TO FINISH
		if (costs[source][target] >= 10000)
			System.out.println("THERE IS NO PATH");
		else {

			int pivot = p[source][target];
			System.out.print("NODE" + source + "--> ");

			System.out.print(printFloydPath(v[source], v[target]));
			System.out.print("NODE" + target +"\n");
		}
	}

	

	/* load the example cost matrix */
	static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = 10000000;
		w[0][1] = 19;
		w[0][2]=  10;
		w[1][2]=  20;
		w[2][1] = 19;
		w[2][3] = 14;
		w[3][0] = 27;
		w[3][1] = 67;
	    w[3][2]=  21;
		w[4][1]=  80;
	}
	
	/* print the cost matrix */
	static void printMatrix(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(String.format("%10s", a[i][j]));
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * returns FloydPath
	 * @param departure
	 * @param arrival
	 * @return
	 */
	public static String printFloydPath(String departure, String arrival) {
		int origin = getNode(departure);
		int target = getNode(arrival);
		
		if(origin == INDEX_NOT_FOUND || target == INDEX_NOT_FOUND)
			return null;
		return checkFloydPathValid(origin, target) + getFloydPath(origin, target);
		
		
	}
	
	private static String checkFloydPathValid(int origin, int target) {
		if(costs[origin][target] == INFINITE)
			return "_NO_PATH_FOUND_TO_";
		else return "";
	}
	
	
	private static String getFloydPath(int origin, int target) {
	String result = "";
		
	
		if(target == EMPTY)
		{

			return result;
			
		}
		
		
		result = getFloydPath(origin, p[origin][target]);
		
		if(p[origin][target] != EMPTY)
			result += v[p[origin][target]]   + "--> ";
		
		
		return result;
		
		
	}
}

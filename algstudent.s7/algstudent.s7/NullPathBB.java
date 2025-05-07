import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import labs.examples.branchandbound.pyramid.utils.BranchAndBound;
import labs.examples.branchandbound.pyramid.utils.Node;

public class NullPathBB extends BranchAndBound {

    private int[][] matrix;
    private int size;
    private int cost = 0;
    private int tolerance = 99;

    public NullPathBB(int n) {
        this.size = n;
        matrix = new int[n][n];
        Random r = new Random();
        double p1 = 0.7;
        int minWeight = 10;
        int maxWeight = 99;
        Boolean[] visited = new Boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = r.nextDouble() <= p1 ? r.nextInt(minWeight, maxWeight)
                                                    : -r.nextInt(minWeight, maxWeight);
            }
        }
        int i = 0;
        for (int[] row : matrix)
            {
        	System.out.println(Arrays.toString(row));
        	visited[i++] = false;;
            }

        Node start = new PathNode(0,visited , 0, new ArrayList<>(), null);
        super.branchAndBound(start);
        super.printSolutionTrace();
    }

    public class PathNode extends Node {

        int current;
        Boolean[] visited;
        
        ArrayList<Integer> path;

        public PathNode(int current, Boolean[] visited, int totalWeight, ArrayList<Integer> path, UUID parentID) {
            this.current = current;
            this.visited = visited.clone();
            this.visited[current] = true;
            this.totalWeight = totalWeight;
            this.path = new ArrayList<>(path);
            this.path.add(current);
            this.depth = this.path.size();
            this.parentID = parentID;
            calculateHeuristicValue();  
        }


        @Override
        public ArrayList<Node> expand() {
            ArrayList<Node> children = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (!visited[i] && current != matrix.length-1 && (i != size-1 || depth == matrix.length-1)) {
                    int newWeight = totalWeight + matrix[current][i];
                    children.add(new PathNode(i, visited, newWeight, path, ID));
                }
            }
            return children;
        }

        @Override
        public boolean isSolution() {
        	
        	System.out.println(path.toString());
            if (path.size() != size) return false;
            
            return current == size - 1 && totalWeight < cost + tolerance && totalWeight > cost - tolerance;
        }

        @Override
        public String toString() {
            return "Path: " + path + ", Weight: " + totalWeight;
        }

    
        
        
        @Override
        public int initialValuePruneLimit() {
            return Integer.MAX_VALUE;
        }


		@Override
		public void calculateHeuristicValue() {
			
			heuristicValue = totalWeight;
			for(int i = 0 ; i < matrix.length; i++)
				if (!visited[i]) {
				heuristicValue += matrix[current][i];
				}
				
			System.out.println("heuristic of  " + current +": " + heuristicValue);
		}
		
		
    }
    
    
}


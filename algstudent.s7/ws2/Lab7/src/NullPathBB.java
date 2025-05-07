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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = r.nextDouble() <= p1 ? r.nextInt(minWeight, maxWeight)
                                                    : -r.nextInt(minWeight, maxWeight);
            }
        }

        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));

        Node start = new PathNode(0, new boolean[n], 0, new ArrayList<>(), null);
        super.branchAndBound(start);
        super.printSolutionTrace();
    }

    public class PathNode extends Node {

        int current;
        boolean[] visited;
        
        ArrayList<Integer> path;

        public PathNode(int current, boolean[] visited, int totalWeight, ArrayList<Integer> path, UUID parentID) {
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
                if (!visited[i] && current != matrix.length) {
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
            
            return current == size - 1 ;
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
			
			heuristicValue = 0;
			for(int i = 0 ; i < matrix.length; i++)
				
					
				
				if (!visited[i]) {
				heuristicValue += Math.abs(matrix[current][i]);
				}
				
		}
		
		public int getTotalWeight() {
			return totalWeight;
		}
    }
    
    
}


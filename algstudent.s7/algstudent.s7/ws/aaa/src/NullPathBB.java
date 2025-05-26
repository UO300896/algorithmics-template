import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import labs.examples.branchandbound.pyramid.utils.BranchAndBound;
import labs.examples.branchandbound.pyramid.utils.Node;

public class NullPathBB extends BranchAndBound {

    private int[][] weights;
    private int size;
    private int cost = 0;
    private int tolerance = 99;

    public NullPathBB(int n) {
        this.size = n;
        weights = new int[n][n];
        
       
        Boolean[] visited = new Boolean[n];
       
        fillInWeights(visited);
        rootNode = new PathNode(0,visited , 0, new ArrayList<>(), null);
        super.branchAndBound(rootNode);
        super.printSolutionTrace();
    }
    
    private void fillInWeights(Boolean[] visited) {
    	Random r = new Random();
    	 double p1 = 0.5;
         int minWeight = 10;
         int maxWeight = 99;
    	 for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 weights[i][j] = r.nextDouble() <= p1 ? r.nextInt(minWeight, maxWeight)
                                                     : -r.nextInt(minWeight, maxWeight);
             }
         }
         int i = 0;
         for (int[] row : weights)
             {
         	System.out.println(Arrays.toString(row));
         	visited[i++] = false;;
             }
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
                if (!visited[i] && current != weights.length-1 && (i != size-1 || depth == weights.length-1)) {
                    int newWeight = totalWeight + weights[current][i];
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
			int min = Integer.MAX_VALUE;
			
			for(int i = 0 ; i < weights.length; i++)
				if (!visited[i]) {
					if(Math.abs(min) > Math.abs(weights[current][i] + totalWeight))
						heuristicValue += weights[current][i]+totalWeight;
				}
				
			System.out.println("heuristic of  " + current +": " + heuristicValue);
		}
		
		
    }
    
    
}


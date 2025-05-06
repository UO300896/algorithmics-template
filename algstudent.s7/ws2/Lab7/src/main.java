import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import labs.examples.branchandbound.pyramid.utils.Node;

public class main {
	static int[][] board = {
            { 0, 10, 15, 20 },
            { 20, 0, 30, 22 },
            { 25, 35, 0, 30 },
            { 20, 5, 4, 0 }
        };
	
	static int cost;
	static int heuristicValue = Integer.MAX_VALUE;
	static ArrayList<Integer> visited = new ArrayList<Integer>();
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
    	cost = 0;
    	
    	for(int i = 1; i < board.length; i++) {
    		int sol = calculateHeuristicValue(0, i);
    		if(prune(sol)) {
    			continue;
    		}
    		map.put(sol, i);
    		if( sol < heuristicValue)
    			heuristicValue = sol;
    		
    	}
    	
    	
       System.out.println(heuristicValue);
       System.out.println(map.get(heuristicValue));
    }

       
        public static int calculateHeuristicValue(int row, int column) {
        	int prevVal = Integer.MAX_VALUE;
        	
          int heuristicValue2 = cost + board[row][column];
          for(int i = 0; i < board.length; i++) {
        	  if(i != column && !visited.contains(i)) {
        	  for(int j = 0; j < board.length; j++) {
        		
        			  if(j != row  && !visited.contains(j)) {
        				  if(board[j][i] != 0 && board[j][i] < prevVal)
        				  {  prevVal = board[j][i];
        				 
        				  }
        			  }
        		  }
        	  heuristicValue2 += prevVal;
        	  prevVal = Integer.MAX_VALUE;
        	  }
        	 
        	  
        	  
          }
          return heuristicValue2;
       
    }
        
        
        
        
       
    	public ArrayList<Integer> expand() {
        	ArrayList<Integer> children = new ArrayList<>();
            for(int i = 0; i < board.length; i++) {
            	if(!visited.contains(i))
            		children.add(i);
            }
            
            return children;
        	
}
        
        
        /**
    	 * Checks if we should prune when the conditions are not longer met
    	 * @return True if we should prune. False otherwise
    	 */
    	private static boolean prune(int heuristicValue2) {
    		return heuristicValue2 > 50;
    	}

}


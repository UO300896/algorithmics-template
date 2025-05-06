import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;


import labs.examples.branchandbound.pyramid.utils.Node;

class PathNode extends Node {
	private int id;
	private int[][] board; //board for playing
	private int row; //current row of this board
	private int column; //current column of this board
	private static int n = 5; //size of the side of the board to save the pyramid
	 static final int MIN_WEIGHT = 10;
	    static final int MAX_WEIGHT = 99;
	    static final int TARGET_COST = 0;
	    static final int TOLERANCE = 20;
	    static final double p1 = 0.5;
	    static final double p2 = 1 - p1;
	  
	    
	    @Override
	    public int initialValuePruneLimit() {
			return TOLERANCE + TARGET_COST;
		}
	    
	    
	    public void printMatrix() {
	        for (int[] row : board) {
	            for (int val : row) {
	                System.out.printf("%5d", val);
	            }
	            System.out.println();
	        }
	    }
	/**
	 * Constructor for Pyramid puzzle objects (root node)
	 * @param n Size of the board
	 */
	public PathNode(int n) { //Generates an empty board
		PathNode.n = n;	 	
		board = new int[n][n];
		row = n-1;
		column = n-1;
	}
	
	/**
	 * Inserts the values of a line from the pyramid 
	 * It is call once per every row of the pyramid to initialize all the values
	 * @param values Values of a row of the pyramid
	 * @param row Number of the current row
	 */
	public int[][] generateRandomGraph() {
        board = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    board[i][j] = 0;
                } else {
                    boolean positive = rand.nextInt(101) < p1 * 100;
                    int weight = rand.nextInt(MIN_WEIGHT, MAX_WEIGHT + 1);
                    board[i][j] = positive ? weight : -weight;
                }
            }
        }
        return board;
    }
		
    

    @Override
    public void calculateHeuristicValue() {
    	int prevVal = Integer.MAX_VALUE;
       heuristicValue = board[row][column];
      for(int i = 0; i < board.length; i++) {
    	  for(int j = 0; j < board.length; j++) {
    		  if(i != row) {
    			  if(j != column) {
    				  if(board[j][i] < prevVal)
    					  prevVal = board[j][i];
    			  }
    		  }
    	  }
    	  heuristicValue += prevVal;
    	  prevVal = Integer.MAX_VALUE;
    	  
      }
      

    

    @Override
    public boolean isSolution() {

    	return depth == n-2;
    }


    private int[][] copyBoard(int row, int column, int k) {
        int[][] newBoard = new int[n][n];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j <= i; j++)
                newBoard[i][j] = board[i][j];				      

        newBoard[row][column] = k;	
        return newBoard;
    }

    
    @Override
    public ArrayList<Node> expand() {
        ArrayList<Node> children = new ArrayList<>();
        
       
        }
        return children; }

    public PathNode(int[][] board, int depth, UUID parentID, int row, int column, UUID id) {
        this.board = board;
        this.depth = depth;
        this.parentID = parentID;
        this.row = row;
        this.column = column;
        this.ID = id; 
        calculateHeuristicValue(); 
    }
}

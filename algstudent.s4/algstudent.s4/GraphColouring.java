package algstudent.s4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphColouring {

	public static Map<String, String> greedy(Map<String, List<String>> graph) {
		List<String> colors = new ArrayList<String>();
		String color = "";
		int j = 0;
		
		colors.add("red");
		colors.add("blue");
		colors.add("green");
		colors.add("yellow");
		colors.add("orange");
		colors.add("purple");
		colors.add("cyan");
		colors.add("magenta");
		colors.add("lime");
		
		Map<String, String> result = new HashMap<String, String>();
		for(int i = 0; i < graph.size(); i++) { //O(n)
			
			do {
				
				
				if(j == colors.size())
					{
					
						throw new IllegalStateException("more colors are needed");
					}
				color =colors.get(j);
				j++;
				
			} while(containsColor(graph.get(String.valueOf(i)), color, result)); //O(n) (when containsColor() is bestCase O(1), while is executed n times [O(1) * O(n); when worstCase O(n), while is executed once [O(n) * O(1)] 
			
			result.put(String.valueOf(i), color);
			j = 0;
		}
		
		
		return result;
	} //O(n^2)

	private static boolean containsColor(List<String> list, String color, Map<String, String> result) {
		boolean contains = false;
	
		for(Object s : list) { //O(1) best case, O(n) worst case
			
			contains = result.get(""+s) == color;
			if(contains)
				return true;
		}
		
		return false;
	}
}

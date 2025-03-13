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
		for(int i = 0; i < graph.size(); i++) {
			
			do {
				
				
				if(j == colors.size())
					{
					
						throw new IllegalStateException("more colors are needed");
					}
				color =colors.get(j);
				j++;
				
			} while(containsColor(graph.get(String.valueOf(i)), color, result));
			
			result.put(String.valueOf(i), color);
			j = 0;
		}
		
		
		return result;
	}

	private static boolean containsColor(List<String> list, String color, Map<String, String> result) {
		boolean contains = false;
	
		for(Object s : list) {
			
			contains = result.get(""+s) == color;
			if(contains)
				return true;
		}
		
		return false;
	}
}

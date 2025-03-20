package algstudent.s4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GreedyTimes {

	public static void main(String[] args) {
		long t1 = -1, t2 = -1;
		JSONParser parser = new JSONParser();
		for(int i = 8; i < 65537; i*=2) {
			try (FileReader reader = new FileReader("C:\\Algoritmia\\Lab\\algorithmics-template\\algstudent.s4\\Session 4\\ws\\S4\\src\\algstudent\\s4\\g" + i + ".json")) {
				JSONObject jsonObject = (JSONObject) parser.parse(reader);
				@SuppressWarnings("unchecked")
				Map<String, List<String>> graph = (Map<String, List<String>>) jsonObject.get("graph");
				
				t1 = System.currentTimeMillis();
				Map<String, String> solution = GraphColouring.greedy(graph);
				t2 = System.currentTimeMillis();
				try (FileWriter file = new FileWriter("C:\\Algoritmia\\Lab\\algorithmics-template\\algstudent.s4\\Session 4\\ws\\S4\\src\\algstudent\\s4\\s"  + i + ".json")) {
					file.write(new JSONObject(solution).toJSONString());
				}

				if (solution != null) {
					System.out.println("Solution found: " + solution);
				} else {
					System.out.println("Solution not found.");
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
			System.out.println("----------------------");
			System.out.println("n " + i + "\t" + "t " + (t2 - t1));
			System.out.println("----------------------");

			

			
		}
		
	}
}

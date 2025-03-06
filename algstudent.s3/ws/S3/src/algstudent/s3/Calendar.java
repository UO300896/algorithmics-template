package algstudent.s3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import labs.examples.dandc.closest.Point;

public class Calendar {

	public static String file;
	public static List<String> people = new ArrayList<String>();
	public static void main (String[] arg) { 
		file = arg[0];
		loadFile(file);
		printHeader();
		printNames(0);
	}
	
	
	public static void loadFile(String name) {
		String line = "";
		StringTokenizer pair; //to get the two parts of each line (x and y coordinate of one point)
		BufferedReader reader = null; //to read each line of the file
		try {
			reader = new BufferedReader(new FileReader(name));
			int n = Integer.parseInt(reader.readLine()); //in the first line of the file we have the number of points 
			for (int i=0; i<n; i++) {
				line = reader.readLine();
				people.add(line);
			}
			reader.close();
		} catch(Exception e) { 
			 System.out.println(e.getMessage()); 
		}
	}
	
	public static void printHeader() {
		for(int i = 0; i < people.size(); i++)
			if(i == 0)
				System.out.print("PLAYER/OPPONENT" +  "\t");
			else
				System.out.print("Day " + (i) + "\t");
	}
	
	public static void printNames(int index) {
		if(index >= people.size())
			return;
		int r = 0;
		System.out.print("\n" + people.get(index) +  "\t");
		
		for(int i = index; i > 0; i--)
			{
				r++;
				System.out.print( "\t" + people.get(index-i));
			}
		
		
		for(int i = index+1; i < people.size(); i++)
		{
			System.out.print( "\t" + people.get(i));
		}
	
		
		printNames(index+1);
		
	}
}

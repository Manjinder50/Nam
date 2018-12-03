package Assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class KeywordAnalysis {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the input file name and output file name");
		String inputs = scan.nextLine();
		
		String[] inputValues = inputs.split("\\s");
		
		String[] fileContents = readFile(inputValues[0]);
				
		Map<String , Integer> mapContents = analyze(fileContents);
		
		writeValuesToAFile(mapContents,inputValues[1]);
	}
	
	private static String[] readFile(String inputValue) {
	         String line = null;
	         String[] lines = null;
		try {
			FileReader reader = new FileReader("C:\\"+inputValue+".txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
	       
            while((line = bufferedReader.readLine()) != null) {
                 lines = line.split("\\s|\\.");
            }   

	        bufferedReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		 return lines;	
		 
	}

	private static Map<String, Integer> analyze(String[] fileContents) {
		
		Map<String,Integer> mapCount = new HashMap<>();
		
		for(String s:fileContents) {
			
			if(mapCount.containsKey(s)) {
				mapCount.put(s, mapCount.get(s)+1);
			}
			else {
				mapCount.put(s, 1);
			}
		}
		
		return mapCount;
	}
	
	private static void writeValuesToAFile(Map<String, Integer> mapContents, String inputValues) {
		
		int valuesToBePrinted = mapContents.size();
		
		FileWriter fStream;
		BufferedWriter out;
		
		try {
			fStream = new FileWriter("C:\\"+inputValues+".txt");
			out = new BufferedWriter(fStream);
			
			int count=0;
			
			Iterator<Entry<String,Integer>> iter= (Iterator<Entry<String, Integer>>) mapContents.entrySet();
			
			while(iter.hasNext() && count < valuesToBePrinted) {
				Map.Entry<String, Integer> mapEntry = iter.next();
				System.out.println(mapEntry.getKey()+" -> "+mapEntry.getValue());
				out.write(mapEntry.getKey()+" -> "+mapEntry.getValue());
				
				count++;
				
			}			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

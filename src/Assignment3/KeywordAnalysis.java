package Assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		List<String> fileContents = readFile(inputValues[0]);
				
		Map<String , Integer> mapContents = analyze(fileContents);
		
		Map<String,String> frequentWords = calculatePercentageOfFrequentWords(mapContents);
		
		writeValuesToAFile(frequentWords,inputValues[1]);
	}

	private static List<String> readFile(String inputValue) {
	         String line = null;
	         List<String> lines = new ArrayList<>();
		try {
			FileReader reader = new FileReader("D:\\Nam\\"+inputValue+".txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
	       
            while((line = bufferedReader.readLine()) != null) {
                 lines.addAll(Arrays.asList(line.split("\\s|\\.")));
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

	private static Map<String, Integer> analyze(List<String> fileContents) {
		
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
	
	private static Map<String, String> calculatePercentageOfFrequentWords(Map<String, Integer> mapContents) {
		
		Map<String,String> mapWithPercentage = new HashMap<>();
		Map<String, Integer> mapWithFrequentValues = new HashMap<>();
		
		int totalValues = mapContents.size();

		for(Map.Entry<String, Integer> mapEntry:mapContents.entrySet()) {
			if(mapEntry.getValue()>=4) {
				mapWithFrequentValues.put(mapEntry.getKey(), mapEntry.getValue());
			}
		}
		
		
		for(Map.Entry<String, Integer> mapEntry:mapWithFrequentValues.entrySet()) {
			Integer percentage = (int) ((mapEntry.getValue()*100)/totalValues);
			String percent = percentage.toString();
			mapWithPercentage.put(mapEntry.getKey(), percent+"("+mapEntry.getValue()+")");
		}
		
		return mapWithPercentage;
	}
	
	private static void writeValuesToAFile(Map<String, String> frequentWords, String inputValues) {
		
	//	int valuesToBePrinted = mapContents.size();
		
		FileWriter fStream;
		BufferedWriter out;
		
		try {
			fStream = new FileWriter("D:\\Nam\\"+inputValues+".txt");
			out = new BufferedWriter(fStream);
			
			for(Map.Entry<String, String> mapEntry : frequentWords.entrySet()) {
			out.write(mapEntry.getKey()+" -> "+mapEntry.getValue());
			out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

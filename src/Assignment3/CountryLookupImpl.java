package Assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CountryLookupImpl {

	public static void main(String[] args) {

      /*  if (args.length != 1) {
            throw new IllegalArgumentException();
        }
*/
		List<Country> listOfCountries = populateDB();
		
		writeDataToFile(listOfCountries,"D:\\Nam\\countryDB.txt");
		
		System.out.println("Welcome to World Database! (type exit to quit)");
		
		String userAddedCountryName = enterCountryName();
		
		if(StringUtils.isBlank(userAddedCountryName)) {
			
			System.out.println("Country name cannot be blank");
			userAddedCountryName = enterCountryName();		
		}
		
		fetchTheCountryDetails(userAddedCountryName);
	}

	private static List<Country> populateDB() {

		List<Country> countriesInfo = new ArrayList<>();
		countriesInfo.add(new Country("United States", "Washington D.C.", "America", "322 million", "55836 USD"));
		countriesInfo.add(new Country("Japan", "Tokyo", "Asia", "127 million", "32477 USD"));
		countriesInfo.add(new Country("Germany", "Berlin", "Europe", "82 million", "47000 USD"));
		countriesInfo.add(new Country("Australia", "Canberra", "Oceania", "24 million", "48800 USD"));
		countriesInfo.add(new Country("Egypt", "Cairo", "Africa", "92 million", "12560 USD"));
		
		return countriesInfo;
	}
	
	private static void writeDataToFile(List<Country> listOfCountries, String inputFile) {

		FileWriter fStream;
		BufferedWriter out;
		
		try {
			fStream = new FileWriter(inputFile);
			out = new BufferedWriter(fStream);
			
			for(Country c: listOfCountries) {
				out.write(c.getCountry_name()+","+c.getCapital()+","+c.getContinent()+","+c.getPopulation()+","+c.getGdp());
				out.newLine();
			}
			
			out.close();
			
		}
		catch(IOException e) {
			
			System.out.println("Database File does not exist");
			e.printStackTrace();
		}
	}
	
	private static String enterCountryName() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter country name");
		String enteredName = scan.nextLine();
		return enteredName;
	}
	
	private static void fetchTheCountryDetails(String userAddedCountryName) {
		
		Map<String, String> finalOutput = new LinkedHashMap<>();
		String line = null;
        List<String> lines = new ArrayList<>();
	try {
		FileReader reader = new FileReader("D:\\Nam\\countryDB.txt");
		BufferedReader bufferedReader = new BufferedReader(reader);
      
       while((line = bufferedReader.readLine()) != null) {
            lines.addAll(Arrays.asList(line.split("\\,")));
       }   
       
 //      System.out.println(lines);
       bufferedReader.close();
       
       if(lines.contains(userAddedCountryName)) {
    	   int indexOfTheValueFound = lines.indexOf(userAddedCountryName);
    	   
    	   finalOutput.put("Country", userAddedCountryName);
    	   finalOutput.put("Capital", lines.get(indexOfTheValueFound+1));
    	   finalOutput.put("Population", lines.get(indexOfTheValueFound+3));
    	   finalOutput.put("GDP", lines.get(indexOfTheValueFound+4));
    	   
    	   for(Map.Entry<String, String> mapEntry:finalOutput.entrySet()) {
    		   
    		   System.out.println(mapEntry.getKey()+" : "+mapEntry.getValue());
    	   }
       
       }

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch(IOException e) {
		e.printStackTrace();
	}

	}

}

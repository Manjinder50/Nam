package Assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryLookupImpl {

	public static void main(String[] args) {

		List<Country> listOfCountries = populateDB();
		
		writeDataToFile(listOfCountries,"D:\\Nam\\countryDB.txt");
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
			
			e.printStackTrace();
		}
	}

}

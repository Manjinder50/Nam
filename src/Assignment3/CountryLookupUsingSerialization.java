package Assignment3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CountryLookupUsingSerialization {

	public static void main(String[] args) {
		
		List<Country> listOfCountries = populateDB();
		
		//Serializing list object to text file
		try {
		writeDataToFile(listOfCountries,"D:\\Nam\\countryDB1.txt");
	
		
		System.out.println("Welcome to World Database! (type exit to quit)");
		
		String userAddedCountryName = enterCountryName();
		
		if(StringUtils.isBlank(userAddedCountryName)) {
			
			System.out.println("Country name cannot be blank");
			userAddedCountryName = enterCountryName();		
		}
		
		fetchTheCountryDetails(userAddedCountryName,"D:\\Nam\\countryDB1.txt");
		}catch(Exception exception) {
			exception.printStackTrace();
		}
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
	
	private static void writeDataToFile(List<Country> listOfCountries, String inputFile) throws IOException {

		
        FileOutputStream fos = null;
        ObjectOutputStream objOutputStream = null;
		try {
			//Create FileOutputStream to write file
			fos = new FileOutputStream(inputFile);
			
			//Create ObjectOutputStream to write object
			objOutputStream = new ObjectOutputStream(fos);
			
			 //Write object to file
			for(Object obj:listOfCountries) {
				objOutputStream.writeObject(obj);
				objOutputStream.reset();
				objOutputStream.flush();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot write to the specified file");
			e.printStackTrace();
		}finally {
			fos.close();
			objOutputStream.close();

		}
	}
	
	private static String enterCountryName() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter country name");
		String enteredName = scan.nextLine();
		return enteredName;
	}
	
	private static void fetchTheCountryDetails(String userAddedCountryName,String inputFile) throws IOException {
		
		List<Country> countriesWrittenInFile = new ArrayList<>();
		String country_name = null;
		boolean matchFound = false;
		Map<String, String> finalOutput = new LinkedHashMap<>();
		 Country country = null;
		 ObjectInputStream obj = null;
		 FileInputStream fis = null;
		try {
		fis = new FileInputStream(inputFile);
        //Create new ObjectInputStream object to read object from file
        obj = new ObjectInputStream(fis);
        
            while (fis.available() != -1 && matchFound==false) {
                //Read object from file
                country =  (Country) obj.readObject();
                countriesWrittenInFile.add(country);
  //          	countriesWrittenInFile = (List<Country>) obj.readObject();
                for(Country countries: countriesWrittenInFile) {
                	
                	 country_name = countries.getCountry_name();
                	
                	if(country_name.equalsIgnoreCase(userAddedCountryName))
               	 {
                      finalOutput.put("Country", country.getCountry_name());
                	   finalOutput.put("Capital", country.getCapital());
                	   finalOutput.put("Population", country.getPopulation());
                	   finalOutput.put("GDP", country.getGdp());
                	   
                	   for(Map.Entry<String, String> mapEntry:finalOutput.entrySet()) {
                		   
                		   System.out.println(mapEntry.getKey()+" : "+mapEntry.getValue());
                	   }
                	   
                	   matchFound=true;
                	
                }
                	else if(country_name.equalsIgnoreCase(userAddedCountryName)&&!countriesWrittenInFile.contains(country.getCountry_name())){
                		System.out.println("Country not in the list");
                		matchFound = true;
                	}
                	
                
            	    
             	  
            }
               
            }
        } catch (EOFException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            
            fis.close();
            obj.close();
            
		}
  
	}
}

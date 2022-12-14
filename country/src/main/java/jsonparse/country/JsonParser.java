package jsonparse.country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

	public static void main(String[] args) {
		byte[] jsonData = null;
		try {
			jsonData = Files.readAllBytes(Paths.get("country.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		CountryInfo[] cnInfoArray = null;
		try {
			cnInfoArray = objectMapper.readValue(jsonData, CountryInfo[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Number of countries:" + numOfCountries(cnInfoArray));
		
		System.out.println("Country with most off-lang(de):" + countryMostOfficialLangsWithGerman(cnInfoArray));
		
		System.out.println("Count of off-lang:" + countOfficialLangs(cnInfoArray));
		
		System.out.println("Country with most off-lang:" + countryMostOfficialLangs(cnInfoArray));
		
		System.out.println("Most common language:");
		mostCommonOfficialLang(cnInfoArray);
	}
	
	//number of countries
	private static int numOfCountries(CountryInfo[] cnInfoArray) {
		Set<String> uniqueCountries = new HashSet<>();
		for(CountryInfo cn : cnInfoArray) {
			uniqueCountries.add(cn.getCountry());
		}
		return uniqueCountries.size();
		
	}
	
	//country having most official languages where german is one of them
	private static String countryMostOfficialLangsWithGerman(CountryInfo[] cnInfoArray) {
		String country = null;
		int numOfLangs = 0;
		String germanLang = "de";
		for(CountryInfo cn : cnInfoArray) {
			if(cn.getLanguages().contains(germanLang)) {
				if(cn.getLanguages().size() > numOfLangs) {
					numOfLangs = cn.getLanguages().size();
					country = cn.getCountry();
				}
			}
		}
		return country;
	}

	//count all official langs
	private static int countOfficialLangs(CountryInfo[] cnInfoArray) {
		Set<String> uniqueLangs = new HashSet<>();
		for(CountryInfo cn : cnInfoArray) {
			uniqueLangs.addAll(cn.getLanguages());
		}
		return uniqueLangs.size();
	}
	
	//country with most official langs
	
	private static String countryMostOfficialLangs(CountryInfo[] cnInfoArray) {
		String country = null;
		int numOfLangs = 0;
		for(CountryInfo cn : cnInfoArray) {
			if(cn.getLanguages().size() > numOfLangs) {
				numOfLangs = cn.getLanguages().size();
				country = cn.getCountry();
			}
		}
		return country;
	}
	
	//most common official language
	private static void mostCommonOfficialLang(CountryInfo[] cnInfoArray) {
		Map<String, Integer> langToCount = new HashMap<>();
		Integer maxCount =0;
		for(CountryInfo cn : cnInfoArray) {
			for(String lang : cn.getLanguages()) {
				Integer count = -1;
				if((count = langToCount.get(lang)) == null) {
					langToCount.put(lang, 1);
				} else {
					langToCount.put(lang, count+1);
				}
				maxCount = langToCount.get(lang) > maxCount ? langToCount.get(lang) : maxCount;
			}
		}
		for(String lang : langToCount.keySet()) {
			if(langToCount.get(lang) == maxCount) {
				System.out.print(lang + ",");
			}
		}
	}
}

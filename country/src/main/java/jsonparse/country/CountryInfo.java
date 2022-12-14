package jsonparse.country;

import java.util.List;

public class CountryInfo {

	private String country;
	private List<String> languages;
	public String getCountry() {
		return country;
	}
	public void setCountry(String countryName) {
		this.country = countryName;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> offLangs) {
		this.languages = offLangs;
	}
	
}

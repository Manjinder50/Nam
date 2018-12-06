package Assignment3;

import java.io.Serializable;

public class Country implements Serializable {

	private String country_name;
	
	private String capital;

	private String continent;

	private String population;

	private String gdp;

	public Country(String country_name, String capital, String continent, String population, String gdp) {
		super();
		this.country_name = country_name;
		this.capital = capital;
		this.continent = continent;
		this.population = population;
		this.gdp = gdp;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getGdp() {
		return gdp;
	}

	public void setGdp(String gdp) {
		this.gdp = gdp;
	}

	@Override
	public String toString() {
		return "Country [country_name=" + country_name + ", capital=" + capital + ", continent=" + continent
				+ ", population=" + population + ", gdp=" + gdp + "]";
	}
}

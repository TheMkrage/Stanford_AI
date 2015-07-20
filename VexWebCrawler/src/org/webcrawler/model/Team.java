package org.webcrawler.model;

public class Team {
	private String number;
	private String name;
	private String organization;
	private String location;

	public Team(String num, String name, String org, String loc) {
		this.number = num;
		this.name = name;
		this.organization = org;
		setLocation(loc);
	}

	// Getters and Setters
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		location = location.replace("United States", "US");
		location = location.replace("Alabama","AL");
		location = location.replace("Alaska","AK");
		location = location.replace("Alberta","AB");
		location = location.replace("American Samoa","AS");
		location = location.replace("Arizona","AZ");
		location = location.replace("Arkansas","AR");
		location = location.replace("Armed Forces (AE)","AE");
		location = location.replace("Armed Forces Americas","AA");
		location = location.replace("Armed Forces Pacific","AP");
		location = location.replace("British Columbia","BC");
		location = location.replace("California","CA");
		location = location.replace("Colorado","CO");
		location = location.replace("Connecticut","CT");
		location = location.replace("Delaware","DE");
		location = location.replace("District Of Columbia","DC");
		location = location.replace("Florida","FL");
		location = location.replace("Georgia","GA");
		location = location.replace("Guam","GU");
		location = location.replace("Hawaii","HI");
		location = location.replace("Idaho","ID");
		location = location.replace("Illinois","IL");
		location = location.replace("Indiana","IN");
		location = location.replace("Iowa","IA");
		location = location.replace("Kansas","KS");
		location = location.replace("Kentucky","KY");
		location = location.replace("Louisiana","LA");
		location = location.replace("Maine","ME");
		location = location.replace("Manitoba","MB");
		location = location.replace("Maryland","MD");
		location = location.replace("Massachusetts","MA");
		location = location.replace("Michigan","MI");
		location = location.replace("Minnesota","MN");
		location = location.replace("Mississippi","MS");
		location = location.replace("Missouri","MO");
		location = location.replace("Montana","MT");
		location = location.replace("Nebraska","NE");
		location = location.replace("Nevada","NV");
		location = location.replace("New Brunswick","NB");
		location = location.replace("New Hampshire","NH");
		location = location.replace("New Jersey","NJ");
		location = location.replace("New Mexico","NM");
		location = location.replace("New York","NY");
		location = location.replace("Newfoundland","NF");
		location = location.replace("North Carolina","NC");
		location = location.replace("North Dakota","ND");
		location = location.replace("Northwest Territories","NT");
		location = location.replace("Nova Scotia","NS");
		location = location.replace("Nunavut","NU");
		location = location.replace("Ohio","OH");
		location = location.replace("Oklahoma","OK");
		location = location.replace("Ontario","ON");
		location = location.replace("Oregon","OR");
		location = location.replace("Pennsylvania","PA");
		location = location.replace("Prince Edward Island","PE");
		location = location.replace("Puerto Rico","PR");
		location = location.replace("Quebec","PQ");
		location = location.replace("Rhode Island","RI");
		location = location.replace("Saskatchewan","SK");
		location = location.replace("South Carolina","SC");
		location = location.replace("South Dakota","SD");
		location = location.replace("Tennessee","TN");
		location = location.replace("Texas","TX");
		location = location.replace("Utah","UT");
		location = location.replace("Vermont","VT");
		location = location.replace("Virgin Islands","VI");
		location = location.replace("Virginia","VA");
		location = location.replace("Washington","WA");
		location = location.replace("West Virginia","WV");
		location = location.replace("Wisconsin","WI");
		location = location.replace("Wyoming","WY");
		location = location.replace("Yukon Territory","YT");
		this.location = location;
	}
}

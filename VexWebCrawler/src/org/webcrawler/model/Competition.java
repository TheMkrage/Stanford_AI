package org.webcrawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Competition {

	private String competitionName;
	private String location;
	private String date;
	private String link;
	private String season;
	private int row;
	// the matches at the competition
	private ArrayList<Match> matches;
	private ArrayList<Team> teams;
	private ArrayList<Award> awards;
	private boolean occurred = true;

	public ArrayList<Award> getAwards() {
		return awards;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
		if(matches.isEmpty()) {
			this.setOccurred(false);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Competition(String competitionName, String location, String date,
			String link, String season) {
		this.competitionName = competitionName;
		this.location = location;
		this.date = date;
		this.link = link;
		this.season = season;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Competition() {

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;

		/*
		 * WebCrawler crawler = new WebCrawler(link);
		 * crawler.getCompetitionInfo(this);
		 * 
		 * if (getLocation().contains("Califor")) { // get the matches and teams
		 * at this link
		 * 
		 * System.out.println("CONTAINS CALIFORNIA " + this);
		 * setMatches(crawler.getMatchArray());
		 * setTeams(crawler.getTeamsArray()); System.out.println("GOT DATA");
		 * uploadData(); }
		 */

	}

	public void uploadData() {
		Firebase ref = new Firebase(
				"https://vexscout.firebaseio.com/competitions2015/"
						+ getCompetitionName());
		System.out.println("\n UPLOADING AT "
				+ "https://vexscout.firebaseio.com/competitions2015/"
				+ getCompetitionName());
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("name", getCompetitionName());
		outMap.put("link", getLink());
		outMap.put("location", getLocation());
		outMap.put("date", getDate());
		outMap.put("season", getSeason());

		ref.updateChildren(outMap, new Firebase.CompletionListener() {
			@Override
			public void onComplete(FirebaseError firebaseError,
					Firebase firebase) {
				if (firebaseError != null) {
					System.out.println("Data could not be saved. "
							+ firebaseError.getMessage());
				} else {
					System.out.println("Data saved successfully. "
							+ firebase.toString());
				}
			}
		});

		for (Match m : getMatches()) {
			ref = new Firebase(
					"https://vexscout.firebaseio.com/competitions2015/"
							+ getCompetitionName() + "/matches/"
							+ m.getMatchName());

			System.out.println("RUNNING + " + getCompetitionName());
			Map<String, Object> inMap = new HashMap<String, Object>();

			inMap.put("matchnum", m.getMatchName());
			inMap.put("blue score", m.getBlueScore());
			inMap.put("red score", m.getRedScore());

			inMap.put("red team 0", m.getRedTeams()[0]);
			inMap.put("red team 1", m.getRedTeams()[1]);
			inMap.put("red team 2", m.getRedTeams()[2]);

			inMap.put("blue team 0", m.getBlueTeams()[0]);
			inMap.put("blue team 1", m.getBlueTeams()[1]);
			inMap.put("blue team 2", m.getBlueTeams()[2]);

			ref.updateChildren(inMap, new Firebase.CompletionListener() {
				@Override
				public void onComplete(FirebaseError firebaseError,
						Firebase firebase) {
					if (firebaseError != null) {
						System.out.println("Data could not be saved. "
								+ firebaseError.getMessage());
					} else {
						System.out.println("Data saved successfully. "
								+ firebase.toString());
					}
				}
			});

		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams2) {
		this.teams = teams2;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {

		this.competitionName = competitionName;
		// System.out.println("BEFORE: " + this.competitionName);
		this.competitionName = this.competitionName.replaceAll("#", "");
		// System.out.println("BEFORE 1: " + this.competitionName);
		this.competitionName = this.competitionName.replaceAll("/", "");
		// System.out.println("BEFORE 2: " + this.competitionName);
		// this.competitionName = this.competitionName.replaceAll("\\", "");
		this.competitionName = this.competitionName.replaceAll(
				Pattern.quote("."), "");
		// System.out.println("HERE WE GO: " + this.competitionName);
		// System.out.println(this.competitionName);
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		if (!date.equals("League")) {
			String[] dates = date.split("\\s+");
			dates[1] = dates[1].split(",")[0];
			String newDate = dates[2] + "-" + getMonthFor(dates[0])
					+ "-" + getNumberFor(dates[1]);
			date = newDate;
		}
		this.date = date;

	}

	private String getNumberFor(String str) {
		if (Integer.parseInt(str) < 10) {
			return "0" + str;
		}
		return str;
	}

	private String getMonthFor(String str) {
		switch (str) {

		case "January":
			return "01";
		case "February":
			return "02";
		case "March":
			return "03";
		case "April":
			return "04";
		case "May":
			return "05";
		case "June":
			return "06";
		case "July":
			return "07";
		case "August":
			return "08";
		case "September":
			return "09";
		case "October":
			return "10";
		case "November":
			return "11";
		case "December":
			return "12";
		default:
			return "00";
		}
	}

	public String toString() {
		return "Comp: " + competitionName + " Location: " + location + " Date "
				+ date + " link " + link;
	}

	public String getObjectFor(int n) {
		final String ID_0 = getCompetitionName();
		final String ID_1 = getDate();
		final String ID_2 = getLocation();

		switch (n) {

		case 0:
			return ID_0;
		case 1:
			return ID_1;
		case 2:
			return ID_2;
		default:
			return "g";
		}
	}

	public void setAwards(ArrayList<Award> awards) {
		this.awards = awards;

	}

	public boolean isOccurred() {
		return occurred;
	}

	public void setOccurred(boolean occurred) {
		this.occurred = occurred;
	}

}

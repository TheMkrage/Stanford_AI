package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private String URL;
	private Elements pageElements;

	public WebCrawler(String URL) {
		this.URL = URL;
		Document d = new Document(URL);

		System.out.println(URL);
		// Tries to connect to website, until it successfully connects
		boolean websiteNotConnected = true;
		while (websiteNotConnected) {
			try {
				d = Jsoup.connect(URL).timeout(0).get();
				websiteNotConnected = false;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				System.out.println("Did not work for connecting to matchPage");
				websiteNotConnected = true;
			}
		}
		System.out.println("Connected to comp page" + URL);
		// gets all element on the webpage specified
		pageElements = d.getAllElements();
	}

	public ArrayList<String> getTeamsArray() {
		ArrayList<String> teams = new ArrayList<String>();
		for (int i = 0; i < pageElements.size(); i++) {
			Element currentElement = pageElements.get(i);
			if (currentElement.attr("class").contains("rankcol2")) {
				System.out.println("Added Team (getTeamsArray): "
						+ currentElement.text());
				teams.add(currentElement.text());
			}
		}
		return teams;
	}

	public ArrayList<Match> getMatchArray() {
		ArrayList<Integer> matchNumbers = new ArrayList<Integer>();

		ArrayList<Match> matchArray = new ArrayList<Match>();

		int firstElim = 0;
		for (int i = 0; i < pageElements.size(); i++) {
			Element currentMatchElement = pageElements.get(i);

			// if this Element is a match, all matches contain a "#" and
			// "matchbit"
			if (currentMatchElement.attr("class").contains("matchbit")
					&& currentMatchElement.text().contains("#")) {

				// The match that the data will be stored in
				Match match = new Match();
				int elementNumber = 1;
				int elementsFound = 0;

				// If this match is the first QF, set this matchNumber to the
				// first elim
				if (currentMatchElement.text().contains("QF #1-1")) {
					firstElim = i;
				}

				// Each match has 9 elements, this while loop searches through
				// divs untill all 9 pieces of data are found
				while (elementsFound < 9) {
					Element matchBeingTested = pageElements.get(i
							+ elementNumber);
					if (matchBeingTested.text().equals(" ")) {
						System.out.println("BLA");

					} else {
						try {
							// the matchName tag is tested
							if (matchBeingTested.attr("class").contains(
									"matchcol1")) {
								elementsFound++;
								match.setMatchName(matchBeingTested.text());
								// next, matchRed Tag is checked, matchRedTag
								// can
								// either
								// be the team on red or the redscore
							} else if (matchBeingTested.attr("class").contains(
									"matchcol2 blue")) {
								if (match.isBlueTeamsDone()) {
									match.setBlueScore(Integer
											.parseInt(matchBeingTested.text()));
									elementsFound++;
								} else {
									match.addBlueTeam(matchBeingTested.text());
									elementsFound++;
								}

							} else if (matchBeingTested.attr("class").contains(
									"matchcol2 red")) {
								if (match.isRedTeamsDone()) {
									match.setRedScore(Integer
											.parseInt(matchBeingTested.text()));
									elementsFound++;
								} else {
									match.addRedTeam(matchBeingTested.text());
									elementsFound++;
								}
								// System.out.println(elementNumber + "   :   "
								// +
								// matchBeingTested.text());
							} else if (matchBeingTested.attr("class").contains(
									"matchcol2 red sitting")) {
								match.addRedTeam(matchBeingTested.text());
								elementsFound++;
							} else if (matchBeingTested.attr("class").contains(
									"matchcol2 blue sitting")) {
								match.addBlueTeam(matchBeingTested.text());
								elementsFound++;
								// The empty collumn between the two teams
								// signifies
								// that the team are done
							} else if (matchBeingTested.attr("class").contains(
									"matchcol2 empty")) {
								if (match.isRedTeamsDone()) {
									match.setBlueTeamsDone(true);
									elementsFound++;
								} else {
									match.setRedTeamsDone(true);
									elementsFound++;
								}
							}
							elementNumber++;
						} catch (NumberFormatException e) {
							System.out.println("IGNORE IT");
							elementNumber++;
						}

					}
					matchArray.add(match);
					// System.out.println(match.toString());
				}
				// System.out.println(match.toString());
			}

		}
		// System.out.println(matchArray.get(0).toString());
		return matchArray;
	}

	public void getCompetitionInfo(Competition comp) {
		int elementsFound = 0;
		String state = "";
		String city = "";
		for (int i = 0; elementsFound < 2; i++) {
			Element currentElement = pageElements.get(i);

			if (currentElement.text().contains("State:")
					&& currentElement.nodeName().contains("tr")) {
				System.out.println("NEW ELEMENT: " + currentElement.text()
						+ "\n");
				state = currentElement.text();
				elementsFound++;
			}

			if (currentElement.text().contains("City:")
					&& currentElement.nodeName().contains("tr")) {
				System.out.println("NEW ELEMENT: CITY " + currentElement.text()
						+ "\n");
				city = currentElement.text();
				elementsFound++;
			}

			// timeout by seeing is all elements have been searching
			if (i == pageElements.size()) {
				elementsFound = 100;
			}
		}

		comp.setLocation(city + ", " + state);
	}

	// STATIC METHODS
	public static ArrayList<Match> getMatchArrayFromURL(String URL) {
		ArrayList<Integer> matchNumbers = new ArrayList<Integer>();

		ArrayList<Match> matchArray = new ArrayList<Match>();

		Document d = new Document(URL);

		// Tries to connect to website, until it successfully connects
		boolean websiteNotConnected = true;
		while (websiteNotConnected) {
			try {
				d = Jsoup.connect(URL).get();
				websiteNotConnected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Did not work");
				websiteNotConnected = true;
			}
		}

		// gets all element on the webpage specified
		Elements matchesElements = d.getAllElements();

		int firstElim = 0;
		for (int i = 0; i < matchesElements.size(); i++) {
			Element currentMatchElement = matchesElements.get(i);

			// if this Element is a match, all matches contain a "#" and
			// "matchbit"
			if (currentMatchElement.attr("class").contains("matchbit")
					&& currentMatchElement.text().contains("#")) {

				// The match that the data will be stored in
				Match match = new Match();
				int elementNumber = 1;
				int elementsFound = 0;

				// If this match is the first QF, set this matchNumber to the
				// first elim
				if (currentMatchElement.text().contains("QF #1-1")) {
					firstElim = i;
				}

				// Each match has 9 elements, this while loop searches through
				// divs untill all 9 pieces of data are found
				while (elementsFound < 9) {
					Element matchBeingTested = matchesElements.get(i
							+ elementNumber);
					// the matchName tag is tested
					if (matchBeingTested.attr("class").contains("matchcol1")) {
						elementsFound++;
						match.setMatchName(matchBeingTested.text());
						// next, matchRed Tag is checked, matchRedTag can either
						// be the team on red or the redscore
					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 blue")) {
						if (match.isBlueTeamsDone()) {
							match.setBlueScore(Integer
									.parseInt(matchBeingTested.text()));
							elementsFound++;
						} else {
							match.addBlueTeam(matchBeingTested.text());
							elementsFound++;
						}

					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 red")) {
						if (match.isRedTeamsDone()) {
							match.setRedScore(Integer.parseInt(matchBeingTested
									.text()));
							elementsFound++;
						} else {
							match.addRedTeam(matchBeingTested.text());
							elementsFound++;
						}
						// System.out.println(elementNumber + "   :   " +
						// matchBeingTested.text());
					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 red sitting")) {
						match.addRedTeam(matchBeingTested.text());
						elementsFound++;
					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 blue sitting")) {
						match.addBlueTeam(matchBeingTested.text());
						elementsFound++;
						// The empty collumn between the two teams signifies
						// that the team are done
					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 empty")) {
						if (match.isRedTeamsDone()) {
							match.setBlueTeamsDone(true);
							elementsFound++;
						} else {
							match.setRedTeamsDone(true);
							elementsFound++;
						}
					}
					elementNumber++;

				}
				matchArray.add(match);
				System.out.println(match.toString());

				// System.out.println(match.toString());
			}

		}
		// System.out.println(matchArray.get(0).toString());
		return matchArray;
	}

	static String[][] links = {
			{		"NBN",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/vrc-2015-2016?limit=all" }/*,
			{
					"Skyrise",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/vrc-season-2014-2015?limit=all" }/*,
			{
					"Toss Up",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/vrc-season-2013-2014?limit=all" },
			{
					"Sack Attack",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/vrc-season-2012-2013?limit=all" },
			{
					"Gateway",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/vrc-season-2011-2012?limit=all" },
			{
					"Round Up",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/2010-2011-round-up?limit=all" },
			{
					"Clean Sweep",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/2009-2010-clean-sweep?limit=all" },
			{
					"Elevation",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/2008-2009-elevation?limit=all" },
			{
					"Bridge Battle",
					"http://www.robotevents.com/robot-competitions/archived-seasons/vrc/2007-2008?limit=all" }*/ };

	public static ArrayList<String[]> getCompetitions() {
		ArrayList<String[]> competitions = new ArrayList<String[]>();

		// ordered By Location
		// String URL =
		// "http://www.robotevents.com/robot-competitions/vex-robotics-competition?order=re_sort_location&dir=asc&limit=all";

		// ordered by Date
		// String URL =
		// "http://www.robotevents.com/robot-competitions/vex-robotics-competition?limit=all&order=re_sort&dir=asc";

		for (String[] link : links) {
			String URL = link[1];
			String curSeason = link[0];
			Document d = new Document(URL);

			// Tries to connect to website, until it successfully connects
			boolean websiteNotConnected = true;
			while (websiteNotConnected) {
				try {
					d = Jsoup.connect(URL).timeout(0).get();
					websiteNotConnected = false;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out
							.println("Did not work for connecting to competitions");
					websiteNotConnected = true;
				}
			}

			System.out.println("Connected, getting Competitions Now");

			// gets all element on the webpage specified
			Elements matchesElements = d.getAllElements();
			for (Element e : matchesElements) {
				// System.out.println("ATTR:" + e.attr("class"));
				if (e.attr("class").equals("listing-type-list catalog-listing")) {
					Elements tableElements = e.getElementsByAttribute("href");
					for (Element e1 : tableElements) {
						if (e1.attr("href").contains("robotevents")) {
							if (!CompWriter.completedLinks.isLinkInArray(e1.attr("href"))) {
								System.out.println("ADDED: " + e1.attr("href"));
								String[] temp = { curSeason, e1.attr("href") };
								competitions.add(temp);
							}
						}
					}
				}
			}
		}

		return competitions;
	}

	public static ArrayList<String> getTeamsArrayAtURL(String URL) {
		ArrayList<String> teams = new ArrayList<String>();
		Document d = new Document(URL);

		// Tries to connect to website, until it successfully connects
		boolean websiteNotConnected = true;
		while (websiteNotConnected) {
			try {
				d = Jsoup.connect(URL).get();
				websiteNotConnected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Did not work for gettingTeams");
				websiteNotConnected = true;
			}
		}
		System.out.println("Connected to " + URL + "\nGetting Teams Now");
		// gets all element on the webpage specified
		Elements pageElements = d.getAllElements();

		for (int i = 0; i < pageElements.size(); i++) {
			Element currentElement = pageElements.get(i);
			if (currentElement.attr("class").contains("rankcol2")) {
				System.out.println("Added Team: " + currentElement.text());
				teams.add(currentElement.text());
			}
		}
		return teams;
	}
}

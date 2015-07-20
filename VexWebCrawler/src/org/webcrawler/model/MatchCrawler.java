package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MatchCrawler {
	public static ArrayList<Match> getMatchArrayFromURL(Document d) {
		ArrayList<Integer> matchNumbers = new ArrayList<Integer>();

		ArrayList<Match> matchArray = new ArrayList<Match>();

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
							try {
								match.setBlueScore(Integer
										.parseInt(matchBeingTested.text()));
							} catch (Exception e) {
								match.setBlueScore(0);
							}
							
							elementsFound++;
						} else {
							match.addBlueTeam(matchBeingTested.text());
							elementsFound++;
						}

					} else if (matchBeingTested.attr("class").contains(
							"matchcol2 red")) {
						if (match.isRedTeamsDone()) {
							try {
								match.setRedScore(Integer
										.parseInt(matchBeingTested.text()));
							} catch (Exception e) {
								match.setRedScore(0);
							}
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

				// System.out.println(match.toString());
			}

		}
		// System.out.println(matchArray.get(0).toString());
		return matchArray;
	}
}

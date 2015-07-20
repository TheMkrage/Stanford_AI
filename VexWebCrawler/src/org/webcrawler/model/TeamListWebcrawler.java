package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeamListWebcrawler {

	// this method is used to return all the teams that were registered to go to
	// a certain competition from URL
	public static ArrayList<Team> getTeamsFromList(Document d, Competition comp) {

		// this list the team will go
		ArrayList<Team> teamArray = new ArrayList<Team>();

		// begin observing html
		Elements pageElements = d.getAllElements();
		for (int i = 0; i < pageElements.size(); i++) {
			Element curEl = pageElements.get(i);

			// find the team table (in team table tab)
			if (curEl.nodeName().contains("table")
					&& curEl.attr("id").contains("TeamTable")) {
				Elements table = curEl.getAllElements();

				// when you find table, look through all elements
				for (int x = 0; x < table.size(); x++) {
					Element curTableEl = table.get(x);
					// each cell succeeds a <tr>
					if (curTableEl.nodeName().contains("tr")) {
						// the first <td> is the team's num
						if (curTableEl.getAllElements().get(1).nodeName()
								.contains("td")) {
							String numOnly = curTableEl.getAllElements().get(1)
									.text().replaceAll("\\p{Alpha}", "");
							try {
								Team temp = new Team(curTableEl
										.getAllElements().get(1).text(),
										curTableEl.getAllElements().get(2)
												.text(),
										curTableEl.getAllElements().get(3)
												.text(), curTableEl
												.getAllElements().get(4).text());
								teamArray.add(temp);
							} catch (Exception e) {
								//System.out.println("No Teams Registered!");
							
							}
						}
					}
				}
			}
		}
		return teamArray;
	}
}

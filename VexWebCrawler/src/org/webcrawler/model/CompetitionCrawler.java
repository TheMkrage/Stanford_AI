package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CompetitionCrawler {
	
	private static String[] seasons= {"Nothing but Net","Skyrise","Toss Up","Sack Attack","Gateway","Round Up","Clean Sweep"};
	//private static String[] finalCompetitions
	private static String curSeason = seasons[0];
	public static Competition getCompetitionFromURL(Document d, String URL) {
	
		// this list the team will go
		Competition comp = new Competition();
		comp.setLink(URL);
		
		// gets only the element of the info table
		Element temp = d.getElementById("reTable");
		Elements infoTable = temp.getElementsByTag("td");
		comp.setCompetitionName(infoTable.get(0).text());
		if (infoTable.get(1).text().contains(",")) {
			comp.setDate(infoTable.get(1).text());
		}else {
			comp.setDate("League");
		}
		// this is currently just the state
		comp.setLocation(infoTable.get(5).text());
		return comp;
	}

}

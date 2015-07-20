package org.webcrawler.threads;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.webcrawler.frame.MainFrame;
import org.webcrawler.model.AwardCrawler;
import org.webcrawler.model.Competition;
import org.webcrawler.model.CompetitionCrawler;
import org.webcrawler.model.MatchCrawler;
import org.webcrawler.model.Team;
import org.webcrawler.model.TeamListWebcrawler;
import org.webcrawler.model.TeamUploader;

public class CrawlingThread extends Thread {

	private int TIMEOUT = 60000;

	public int getTIMEOUT() {
		return TIMEOUT;
	}

	public void setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}

	@Override
	public void run() {
		while (ThreadManager.isRunning) {
			String[] comp = ThreadManager.getNextLink();
			Document d = new Document(comp[1]);

			int tries = 0;
			// Tries to connect to website, until it successfully connects
			boolean websiteNotConnected = true;

			while (websiteNotConnected && tries < 5) {
				Response r = null;
				try {
					// System.out.println(comp);

					d = Jsoup.connect(comp[1]).timeout(0).get();
					websiteNotConnected = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Did not work for " + comp[1] + " "
							+ tries);
					tries++;
					websiteNotConnected = true;

				}

			}
			// skip if connection fails
			if (websiteNotConnected) {

				ThreadManager.addSkipLink(comp);
				System.out.println("SKIPPED!");
				MainFrame.getInstance().addSkip();
			}

			if (!websiteNotConnected) {
				uploadCompetition(d, comp);
			}
		}

	}

	private void uploadCompetition(Document d, String[] comp) {
		try {
			// System.out.println("BEGINNING " + comp);
			Competition competition = CompetitionCrawler.getCompetitionFromURL(
					d, comp[1]);
			ArrayList<Team> teams = TeamListWebcrawler.getTeamsFromList(d, competition);
			competition.setMatches(MatchCrawler.getMatchArrayFromURL(d));
			if (competition.isOccurred()) {
				competition.setAwards(AwardCrawler.getAwards(d, teams));
			}
			competition.setSeason(comp[0]);
			competition.setTeams(teams);
			// COMP INFO
			TeamUploader.uploadCompetitionInfo(competition);
			// uploads
			for (Team team : teams) {
				TeamUploader.uplaodDataAboutTeamFromCompetition(team,
						competition);
				if (competition.isOccurred()) {
					TeamUploader.uploadMatchesForTeamAtCompetition(team,
							competition);
					TeamUploader.uploadAwardsFromCompetition(team, competition);
				}

			}
			
			//System.out.println("Successfully Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

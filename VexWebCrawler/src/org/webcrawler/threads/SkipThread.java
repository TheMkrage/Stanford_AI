package org.webcrawler.threads;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.webcrawler.frame.MainFrame;
import org.webcrawler.model.AwardCrawler;
import org.webcrawler.model.Competition;
import org.webcrawler.model.CompetitionCrawler;
import org.webcrawler.model.MatchCrawler;
import org.webcrawler.model.Team;
import org.webcrawler.model.TeamListWebcrawler;
import org.webcrawler.model.TeamUploader;

public class SkipThread extends Thread {
	public void run() {
		while (true) {
			while (ThreadManager.isRunning) {
				String[] comp = ThreadManager.getNextSkipLink();
				Document d = new Document(comp[1]);

				int tries = 0;
				// Tries to connect to website, until it successfully connects
				boolean websiteNotConnected = true;

				while (websiteNotConnected ) {
					try {
						System.out.println(comp);

						d = Jsoup.connect(comp[1]).timeout(0).get();
						websiteNotConnected = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Did not work for " + comp + tries);
						websiteNotConnected = true;
						tries++;
					}

				}
				// skip if connection fails
				if (!websiteNotConnected) {
					uploadCompetition(d, comp);
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void uploadCompetition(Document d, String[] comp) {
		try {
			//System.out.println("BEGINNING " + comp);
			Competition competition = CompetitionCrawler
					.getCompetitionFromURL(d, comp[1]);
			ArrayList<Team> teams = TeamListWebcrawler
					.getTeamsFromList(d, competition);
			competition
					.setMatches(MatchCrawler.getMatchArrayFromURL(d));
			if (competition.isOccurred()) {
				competition.setAwards(AwardCrawler.getAwards(d, teams));
			}
			competition.setSeason(comp[0]);
			competition.setTeams(teams);
			// uploads
			for (Team team : teams) {
				TeamUploader.uplaodDataAboutTeamFromCompetition(team,
						competition);
				TeamUploader.uploadMatchesForTeamAtCompetition(team,
						competition);
				TeamUploader.uploadAwardsFromCompetition(team,
						competition);
				

			}
			
			// COMP INFO
			TeamUploader.uploadCompetitionInfo(competition);
			System.out.println("Successfully Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
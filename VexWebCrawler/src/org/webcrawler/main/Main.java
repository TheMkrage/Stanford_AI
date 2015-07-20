package org.webcrawler.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.webcrawler.frame.MainFrame;
import org.webcrawler.model.CompWriter;
import org.webcrawler.model.RobotSkillsCrawler;

import com.firebase.client.Firebase;

public class Main {
	private final static int MAX_THREADS = 80;

	public static void main(String[] args) throws InterruptedException {
		// if your not using the JFrame
		// if (true) {
		
		
		RobotSkillsCrawler.doSkills();

		// String[] com = {
		// "http://www.robotevents.com/robot-competitions/vex-robotics-competition/re-vrc-15-2189.html",
		// "http://www.robotevents.com/robot-competitions/vex-robotics-competition/re-vrc-14-1475.html"
		// };

		/*
		 * System.out.println("YEET"); for (String comp : com) { Document d =
		 * new Document(comp);
		 * 
		 * int tries = 0; // Tries to connect to website, until it successfully
		 * connects boolean websiteNotConnected = true; while
		 * (websiteNotConnected && tries < 1) { try { d =
		 * Jsoup.connect(comp).timeout(25000).get(); websiteNotConnected =
		 * false; } catch (IOException e) { // TODO Auto-generated catch block
		 * System.out.println("Did not work " + tries); websiteNotConnected =
		 * true; tries++; System.out.println("SKIPPED!"); } } //skip if
		 * connection fails if (!websiteNotConnected) { try {
		 * System.out.println("BEGINNING " + comp); Competition competition =
		 * CompetitionCrawler .getCompetitionFromURL(d, comp); ArrayList<Team>
		 * teams = TeamListWebcrawler .getTeamsFromList(d); competition
		 * .setMatches(MatchCrawler.getMatchArrayFromURL(d));
		 * competition.setAwards(AwardCrawler.getAwards(d, teams));
		 * 
		 * // uploads for (Team team : teams) {
		 * TeamUploader.uplaodDataAboutTeamFromCompetition(team, competition);
		 * TeamUploader.uploadMatchesForTeamAtCompetition(team, competition);
		 * TeamUploader.uploadAwardsFromCompetition(team, competition);
		 * 
		 * } System.out.println("Successfully Uploaded\n"); } catch (Exception
		 * e) { e.printStackTrace(); } } }
		 */
		// }else {
		MainFrame frame = MainFrame.getInstance();
		// }
	}

	private static void removeAll() {
		Firebase ref = new Firebase("https://vexscout.firebaseio.com/");
		ref.removeValue();
	}
}

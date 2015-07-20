package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RobotSkillsCrawler {

	public static void doSkills() {
		for (int i = 0; i < robotSkills.length; i++) {
			TeamUploader.uploadSkills(getRobotSkills(i),
					getProgrammingSkills(i), robotSkills[i][0]);
		}
	}

	private static String[][] robotSkills = {

			{
					"NBN",
					"http://www.robotevents.com/robot-competitions/vex-robotics-competition/robot-skills?season=2015-2016&region=&grade=" }/*,
			{
					"Skyrise",
					"http://www.robotevents.com/robot-competitions/vex-robotics-competition/robot-skills?season=2014-2015&region=&grade=" }*/ };
	private static String[][] programmingSkills = {
			{
					"NBN",
					"http://www.robotevents.com/robot-competitions/vex-robotics-competition/programming-skills?season=2015-2016&region=&grade=" }/*,
			{
					"Skyrise",
					"http://www.robotevents.com/robot-competitions/vex-robotics-competition/programming-skills?season=2014-2015&region=&grade=" }*/ };

	public static ArrayList<Skills> getRobotSkills(int i) {
		ArrayList<Skills> rSkills = new ArrayList<Skills>();
		String[] link = robotSkills[i];
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

		System.out.println("Connected, getting RobotSkills Now");
		Elements robotSkillsTable = d.getAllElements();
		// System.out.println(robotSkillsTable);
		for (Element curEl : robotSkillsTable) {
			if (curEl.nodeName().contains("table")
					&& curEl.attr("id").contains("TeamTable")) {
				Elements table = curEl.getAllElements();

				// when you find table, look through all elements
				for (int x = 0; x < table.size(); x++) {
					Element curTableEl = table.get(x);
					// each cell succeeds a <tr>
					if (curTableEl.nodeName().contains("tr")
							&& table.get(x + 1).nodeName().contains("td")
							&& !table.get(x + 1).text()
									.contains("no results to display")) {
						System.out.println(curTableEl);
						Skills skill = new Skills();
						skill.setRank(table.get(x + 1).text());
						skill.setScore(table.get(x + 2).text());
						skill.setTeam(table.get(x + 3).text());
						rSkills.add(skill);
					}
				}
			}
		}
		System.out.println(rSkills);
		return rSkills;
	}

	public static ArrayList<Skills> getProgrammingSkills(int i) {
		ArrayList<Skills> rSkills = new ArrayList<Skills>();
		String[] link = programmingSkills[i];
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

		System.out.println("Connected, getting RobotSkills Now");
		Elements robotSkillsTable = d.getAllElements();
		// System.out.println(robotSkillsTable);
		for (Element curEl : robotSkillsTable) {
			if (curEl.nodeName().contains("table")
					&& curEl.attr("id").contains("TeamTable")) {
				Elements table = curEl.getAllElements();

				// when you find table, look through all elements
				for (int x = 0; x < table.size(); x++) {
					Element curTableEl = table.get(x);
					// each cell succeeds a <tr>
					if (curTableEl.nodeName().contains("tr")
							&& table.get(x + 1).nodeName().contains("td")
							&& !table.get(x + 1).text()
									.contains("no results to display")) {
						System.out.println(curTableEl);
						Skills skill = new Skills();
						skill.setRank(table.get(x + 1).text());
						skill.setScore(table.get(x + 2).text());
						skill.setTeam(table.get(x + 3).text());
						rSkills.add(skill);
					}
				}
			}

		}
		System.out.println(rSkills);
		return rSkills;

	}
}

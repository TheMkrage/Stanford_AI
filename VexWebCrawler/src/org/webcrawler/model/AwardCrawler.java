package org.webcrawler.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AwardCrawler {
	public static ArrayList<Award> getAwards(Document d, ArrayList<Team> teams) {
		ArrayList<Award> list = new ArrayList<Award>();
		try {
			Element temp = d.getElementById("awards_table");
			if (temp.getAllElements() != null) {
				Elements awardTable = temp.getAllElements();
				// System.out.println("awards_table: " + temp);
				for (int i = 0; i < awardTable.size(); i++) {
					Element cur = awardTable.get(i);
					for (Team team : teams) {

						if (cur.nodeName().equals("th")) {
							// System.out.println("\ntesting " +
							// team.getNumber()
							// + " for " + cur.text());
							boolean doneLookingForAward = false;
							String awardName = cur.text();
							try {
								for (int x = i; !doneLookingForAward
										&& x < 7 + i; x++) {
									Element cur2 = awardTable.get(x);
									// System.out.println("fdsaa: " + cur2);
									if (cur2.nodeName().equals("b")
											&& cur2.text().equals(
													team.getNumber())) {
										// System.out.println("it worked");
										list.add(new Award(awardName, team));
										doneLookingForAward = true;
										if (awardName.contains("Tournament")) {
											Element teamTwo = awardTable
													.get(x + 4);
											for (Team team2 : teams) {
												if (team2.getNumber().equals(
														teamTwo.text())) {
													list.add(new Award(
															awardName, team2));
													Element teamThree = awardTable
															.get(x + 8);
													if (teamThree.nodeName()
															.equals("b")) {
														for (Team team3 : teams) {
															if (team3
																	.getNumber()
																	.equals(teamThree
																			.text())) {
																list.add(new Award(
																		awardName,
																		team3));
															}
														}
													}
												}
											}
										}
									}
								}
							} catch (Exception e) {
								// System.out.println("No Awards Entered");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// System.out.println("No Awards Entered");
		}
		// System.out.println("LIST: " + list);
		return list;
	}
}

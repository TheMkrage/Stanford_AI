package org.webcrawler.model;

import java.util.ArrayList;
//import java.util.ByteMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class TeamUploader {
	public static void uplaodDataAboutTeamFromCompetition(Team team,
			Competition comp) throws InterruptedException {
		WritingObject teamFile = new WritingObject("competitions",
				comp.getCompetitionName() + "Teams.txt");
		final Semaphore semaphore = new Semaphore(0);
		Firebase ref = new Firebase("https://vexscout.firebaseio.com/teams/"
				+ team.getNumber());
		ByteMap map = new ByteMap();
		if (!CompWriter.matchesUploadedLinks.isLinkInArray(comp.getLink())
				&& !teamFile.isLinkInArray(team.getNumber())) {
			if (!CompWriter.infoUploadedTeams.isLinkInArray(team.getNumber())) {
				map.put("name", team.getName());
				map.put("loc", team.getLocation());
				map.put("org", team.getOrganization());
				map.put("num", team.getNumber());
				CompWriter.infoUploadedTeams.writeLink(team.getNumber());
				ref.updateChildren(map, new Firebase.CompletionListener() {
					@Override
					public void onComplete(FirebaseError firebaseError,
							Firebase firebase) {
						semaphore.release();
						if (firebaseError != null) {
							System.out.println("Data could not be saved. "
									+ firebaseError.getMessage());
						} else {
							System.out.println("UPLOADED DATA FOR TEAM "
									+ firebase.toString());
						}
					}
				});
			}
			ref = new Firebase("https://vexscout.firebaseio.com//teams/"
					+ team.getNumber() + "/comps/" + comp.getSeason() + "/"
					+ comp.getCompetitionName());
			map = new ByteMap();
			map.put("loc", comp.getLocation());
			map.put("name", comp.getCompetitionName());
			// map.put("link", comp.getLink());
			map.put("date", comp.getDate());
			map.put("season", comp.getSeason());
			ref.updateChildren(map, new Firebase.CompletionListener() {
				@Override
				public void onComplete(FirebaseError firebaseError,
						Firebase firebase) {
					semaphore.release();
					if (firebaseError != null) {
						System.out.println("Data could not be saved. "
								+ firebaseError.getMessage());
					} else {
						System.out.println("UPLOADED DATA ABOUT COMP "
								+ firebase.toString());
					}
				}
			});
			semaphore.acquire();
			teamFile.writeLink(team.getNumber());
		}
	}

	public static void uploadMatchesForTeamAtCompetition(Team team,
			Competition comp) {
		ArrayList<Match> matchTeamIn = new ArrayList<Match>();

		for (Match match : comp.getMatches()) {
			if (match.getRedTeams()[0].equals(team.getNumber())
					|| match.getRedTeams()[1].equals(team.getNumber())
					|| match.getBlueTeams()[0].equals(team.getNumber())
					|| match.getBlueTeams()[1].equals(team.getNumber())) {
				matchTeamIn.add(match);
			} else {
				// try the other robot spots that could be null
				try {
					if (match.getRedTeams()[2].equals(team.getNumber())) {
						matchTeamIn.add(match);
					}
				} catch (Exception e) {

				}
				try {
					if (match.getBlueTeams()[2].equals(team.getNumber())) {
						matchTeamIn.add(match);
					}
				} catch (Exception e) {

				}
			}
		}
		// tells how far robot got, 0 for no eleims, 1 for qf, 2 for sf, 3 for
		// finalist, 4 for champions
		int elimsCode = 0;
		for (Match m : matchTeamIn) {
			if (m.getMatchName().contains("QF")) {
				elimsCode = compareElimCode(1, elimsCode);
			} else if (m.getMatchName().contains("SF")) {
				elimsCode = compareElimCode(2, elimsCode);
			} else if (m.getMatchName().contains("Final")) {
				elimsCode = compareElimCode(3, elimsCode);
			}

			Firebase ref = new Firebase(
					"https://vexscout.firebaseio.com/teams/" + team.getNumber()
							+ "/comps/" + comp.getSeason() + "/"
							+ comp.getCompetitionName() + "/matches/"
							+ m.getMatchName());

			ByteMap inMap = new ByteMap();
			if (!CompWriter.matchesUploadedLinks.isLinkInArray(comp.getLink())) {
				inMap.put("num", m.getMatchName());
				inMap.put("bs", m.getBlueScore());
				inMap.put("rs", m.getRedScore());

				inMap.put("r0", m.getRedTeams()[0]);
				inMap.put("r1", m.getRedTeams()[1]);
				inMap.put("r2", m.getRedTeams()[2]);

				inMap.put("b0", m.getBlueTeams()[0]);
				inMap.put("b1", m.getBlueTeams()[1]);
				inMap.put("b2", m.getBlueTeams()[2]);

				ref.updateChildren(inMap, new Firebase.CompletionListener() {
					@Override
					public void onComplete(FirebaseError firebaseError,
							Firebase firebase) {
						if (firebaseError != null) {
							System.out.println("Data could not be saved. "
									+ firebaseError.getMessage());
						} else {
							System.out.println("Matches Uploaded "
									+ firebase.toString());
						}
					}
				});
			}
		}
	}

	// check if suggested value is bigger than current
	private static int compareElimCode(int i, int elimsCode) {
		if (i > elimsCode)
			return i;
		return elimsCode;
	}

	public static void uploadAwardsFromCompetition(Team team, Competition comp)
			throws InterruptedException {
		final Semaphore semaphore = new Semaphore(0);
		Firebase ref = new Firebase("https://vexscout.firebaseio.com/teams/"
				+ team.getNumber() + "/comps/" + comp.getSeason() + "/"
				+ comp.getCompetitionName() + "/awards");
		ByteMap map = new ByteMap();

		Integer awardCounts = 0;
		// System.out.println("FDS" + comp.getAwards());
		for (Award a : comp.getAwards()) {
			// System.out.println("AWARD: " + a.getAwardName() + " "
			// + a.getTeamWon().getNumber());
			// System.out.println("FOR Team : " + team.getNumber());
			if (a.getTeamWon().getNumber().equals(team.getNumber())) {
				// System.out.println(a.getTeamWon() + " won " +
				// a.getAwardName());
				map.put(awardCounts.toString(), a.getAwardName());
				awardCounts++;
			}
		}
		//
		// System.out.println("map: " + map);
		if (!map.isEmpty()) {
			CompWriter.completedLinks.writeLink(comp.getLink());
			ref.updateChildren(map, new Firebase.CompletionListener() {
				@Override
				public void onComplete(FirebaseError firebaseError,
						Firebase firebase) {
					if (firebaseError != null) {
						System.out.println("Data could not be saved. "
								+ firebaseError.getMessage());
					} else {
						System.out.println("Awards Uploaded "
								+ firebase.toString());
						semaphore.release();
					}
				}
			});
			semaphore.acquire();
		}
	}

	public static void uploadCompetitionInfo(Competition comp) {
		Firebase ref = new Firebase(
				"https://vexscoutcompetitions.firebaseio.com/"
						+ comp.getSeason() + "/" + comp.getCompetitionName());
		ByteMap map = new ByteMap();

		if (!CompWriter.infoUploadedLinks.isLinkInArray(comp.getLink())) {
			// Basic Compeititon Data
			map = new ByteMap();
			map.put("loc", comp.getLocation());
			map.put("name", comp.getCompetitionName());
			// map.put("link", comp.getLink());
			map.put("date", comp.getDate());
			map.put("season", comp.getSeason());
			map.put("occurred", comp.isOccurred());
			ref.updateChildren(map);
			CompWriter.infoUploadedLinks.writeLink(comp.getLink());
		}

		if (comp.isOccurred()
				&& !CompWriter.matchesUploadedLinks.isLinkInArray(comp
						.getLink())) {
			boolean done = true;
			// Match Data
			for (Match m : comp.getMatches()) {

				ByteMap inMap = new ByteMap();

				inMap.put("num", m.getMatchName());
				inMap.put("bs", m.getBlueScore());
				inMap.put("rs", m.getRedScore());
				if (String.valueOf(m.getBlueScore()).isEmpty()
						|| String.valueOf(m.getRedScore()).isEmpty()) {
					done = false;
				}

				inMap.put("r0", m.getRedTeams()[0]);
				inMap.put("r1", m.getRedTeams()[1]);
				inMap.put("r2", m.getRedTeams()[2]);

				inMap.put("b0", m.getBlueTeams()[0]);
				inMap.put("b1", m.getBlueTeams()[1]);
				inMap.put("b2", m.getBlueTeams()[2]);

				ref.child("matches")
						.child(m.getMatchName())
						.updateChildren(inMap,
								new Firebase.CompletionListener() {
									@Override
									public void onComplete(
											FirebaseError firebaseError,
											Firebase firebase) {
										if (firebaseError != null) {
											System.out.println("Data could not be saved. "
													+ firebaseError
															.getMessage());
										} else {
											System.out
													.println("Competition Matches Uploaded. "
															+ firebase
																	.toString());
										}
									}
								});
			}

			// Awards

			Integer awardCounts = 0;
			for (Award a : comp.getAwards()) {
				map = new ByteMap();

				map.put("name", a.getAwardName());
				map.put("team", a.getTeamWon().getNumber());
				awardCounts++;
				if (map != null) {
					ref.child("awards")
							.child(awardCounts.toString())
							.updateChildren(map,
									new Firebase.CompletionListener() {
										@Override
										public void onComplete(
												FirebaseError firebaseError,
												Firebase firebase) {
											if (firebaseError != null) {
												System.out.println("Data could not be saved. "
														+ firebaseError
																.getMessage());
											} else {
												System.out.println("Awards Uploaded. "
														+ firebase.toString());
											}
										}
									});

				}
			}
			System.out.println("UPLOADED INFO ");
			if (done) {
				CompWriter.matchesUploadedLinks.writeLink(comp.getLink());
			}
		} else if (!comp.isOccurred()) {
			// System.out.println(comp.getTeams());
			Integer counter = 0;
			map = new ByteMap();
			WritingObject teamFile = new WritingObject("competitions",
					comp.getCompetitionName() + "TeamsForCompetitions.txt");
			for (Team t : comp.getTeams()) {
				if (!teamFile.isLinkInArray(t.getNumber())) {
					map.put(counter.toString(), t.getNumber());
					counter++;
					teamFile.writeLink(t.getNumber());
				}
			}
			if (counter != 0) {
				ref.child("teams").updateChildren(map,
						new Firebase.CompletionListener() {
							@Override
							public void onComplete(FirebaseError firebaseError,
									Firebase firebase) {
								if (firebaseError != null) {
									System.out
											.println("Data could not be saved. "
													+ firebaseError
															.getMessage());
								} else {
									System.out.println("Teams Uploaded. "
											+ firebase.toString());
								}
							}
						});
			}

		}
		//
		// System.out.println("map: " + map);

	}

	public static void uploadSkills(ArrayList<Skills> rs, ArrayList<Skills> ps,
			String season) {
		Firebase ref = new Firebase(
				"https://vexscoutcompetitions.firebaseio.com/" + season + "/rs");
		ByteMap map = new ByteMap();
		ByteMap inMap = new ByteMap();
		Integer counter = 0;
		for (Skills s : rs) {
			inMap = new ByteMap();
			inMap.put("rank", s.getRank());
			inMap.put("score", s.getScore());
			inMap.put("team", s.getTeam());
			map.put(counter.toString(), inMap);
			counter++;
			Firebase teamRef = new Firebase(
					"https://vexscout.firebaseio.com/teams/" + s.getTeam()
							+ "/comps/" + season + "/rs");
			teamRef.setValue(s.getScore());
		}
		counter = 0;
		ref.updateChildren(map);

		ref = new Firebase("https://vexscoutcompetitions.firebaseio.com/"
				+ season + "/ps");
		System.out.println(ref.toString());
		map = new ByteMap();
		for (Skills s : ps) {
			inMap = new ByteMap();
			inMap.put("rank", s.getRank());
			inMap.put("score", s.getScore());
			inMap.put("team", s.getTeam());
			map.put(counter.toString(), inMap);
			counter++;
			Firebase teamRef = new Firebase(
					"https://vexscout.firebaseio.com/teams/" + s.getTeam()
							+ "/comps/" + season + "/ps");
			teamRef.setValue(s.getScore());
		}
		System.out.println(map);
		ref.updateChildren(map);
		System.out.println("DONE");
	}
}

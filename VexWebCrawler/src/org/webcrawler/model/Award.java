package org.webcrawler.model;

public class Award {
	private String awardName;
	private Competition qualifyFor;
	private Team teamWon;

	public Award() {

	}

	public Award(String awardName, Team teamWon) {
		this.awardName = awardName;
		this.teamWon = teamWon;
	}

	public String toString() {
		return "TEAM: " + this.getTeamWon().getNumber() + " AWARD: "
				+ this.getAwardName();
	}

	public Team getTeamWon() {
		return teamWon;
	}

	public void setTeamWon(Team teamWon) {
		this.teamWon = teamWon;
	}

	// setters and getter
	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Competition getQualifyFor() {
		return qualifyFor;
	}

	public void setQualifyFor(Competition qualifyFor) {
		this.qualifyFor = qualifyFor;
	}
}

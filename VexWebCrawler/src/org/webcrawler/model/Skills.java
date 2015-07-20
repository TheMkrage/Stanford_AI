package org.webcrawler.model;

public class Skills {
	private String team;
	private String score;
	private String rank;
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String toString() {
		return "\n" + team + " " + score + " " + rank;
	}
}

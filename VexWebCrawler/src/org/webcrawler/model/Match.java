package org.webcrawler.model;

import java.util.regex.Pattern;

public class Match {
	private String[] redTeams = new String[3];
	private int redTeamSize = 0;
	private String[] blueTeams = new String[3];
	private int blueTeamSize = 0;
	private String matchName = new String();
	private int redScore;
	private int blueScore;
	private boolean redTeamsDone = false;
	private boolean blueTeamsDone = false;

	public String[] getRedTeams() {
		return redTeams;
	}

	public void addRedTeam(String redTeam) {
		if (redTeamSize < redTeams.length) {
			redTeams[redTeamSize] = redTeam;
			// System.out.println("added red team: " + redTeam);
			redTeamSize++;
			if (redTeamSize == 3) {
				redTeamsDone = true;
			}
		} else {
			redTeamsDone = true;
			setRedScore(Integer.parseInt(redTeam));
		}
	}

	public String[] getBlueTeams() {
		return blueTeams;
	}

	public void addBlueTeam(String blueTeam) {
		if (blueTeamSize < blueTeams.length) {
			blueTeams[blueTeamSize] = blueTeam;
			blueTeamSize++;
			if (blueTeamSize == 3) {
				blueTeamsDone = true;
			}
			// System.out.println("added blue team: " + blueTeam);
		} else {
			blueTeamsDone = true;
			setBlueScore(Integer.parseInt(blueTeam));
		}
	}

	public String getMatchName() {
		
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
		// removes bad characters
		// System.out.println("set match name to: " + matchName);
		this.matchName = this.matchName.replaceAll("#", "");
		this.matchName = this.matchName.replaceAll("/", "");
		//this.matchName = this.matchName.replaceAll("\\", "");
		this.matchName = this.matchName.replaceAll(Pattern.quote("."), "");
		
		this.matchName = this.matchName.replaceAll("inal", "");
		
		if(this.matchName.contains("ual")) {
			this.matchName = this.matchName.replaceAll("ual", "");
			int secondSpace = this.matchName.indexOf(" ", 2);	
			if (secondSpace != -1)
				this.matchName = this.matchName.substring(0, secondSpace);
		}
	}

	public boolean redWon() {
		return this.redScore > this.blueScore;
	}

	public boolean blueWon() {
		return this.redScore < this.blueScore;
	}

	public int getRedScore() {
		return redScore;
	}

	public void setRedScore(int redScore) {
		// System.out.println("redScore: " + redScore);
		this.redScore = redScore;
	}

	public int getBlueScore() {
		return blueScore;
	}

	public void setBlueScore(int blueScore) {
		this.blueScore = blueScore;
	}

	public boolean isRedTeamsDone() {
		return redTeamsDone;
	}

	public boolean isBlueTeamsDone() {
		return blueTeamsDone;
	}

	public void setRedTeamsDone(boolean redTeamsDone) {
		this.redTeamsDone = redTeamsDone;
	}

	public void setBlueTeamsDone(boolean blueTeamsDone) {
		this.blueTeamsDone = blueTeamsDone;
	}

	public String toString() {
		return "match: " + matchName + " Red1: " + redTeams[0] + " Red2: "
				+ redTeams[1] + " Red3: " + redTeams[2] + " Blue1: "
				+ blueTeams[0] + " Blue2: " + blueTeams[1] + " Blue3: "
				+ blueTeams[2] + " Red Score: " + redScore + " BlueScore: "
				+ blueScore;
	}

	public String getObjectFor(int n) {
		final String ID_0 = getMatchName();
		final String ID_1 = getRedTeams()[0];
		final String ID_2 = getRedTeams()[1];
		final String ID_3 = getRedTeams()[2];
		final String ID_4 = getRedTeams()[0];
		final String ID_5 = getRedTeams()[1];
		final String ID_6 = getRedTeams()[2];
		final String ID_7 = String.valueOf(getRedScore());
		final String ID_8 = String.valueOf(getBlueScore());
		switch (n) {

		case 0:
			return ID_0;
		case 1:
			return ID_1;
		case 2:
			return ID_2;
		case 3:
			return ID_3;
		case 4:
			return ID_4;
		case 5:
			return ID_5;
		case 6:
			return ID_6;
		case 7:
			return ID_7;
		case 8:
			return ID_8;
		default:
			return "g";
		}
	}

}
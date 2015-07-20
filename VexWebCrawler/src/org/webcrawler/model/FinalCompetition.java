package org.webcrawler.model;

public enum FinalCompetition {
	SkyriseFinal("2015 VEX Robotics World Championship Middle School Division Presented by The Northrop Grumman Foundation", "Skyrise"),
	TossUpFinal("International VEX Summer Games - Toss Up Division", "Toss Up"),
	SackAttackFinal("2013 VEX Robotics High School World Championship","Sack Attack"),
	GatewayFinal("2012 VEX Robotics Middle School World Championship", "Gateway"),
	RoundUpFinal("2011 VEX Robotics World Championship", "Round Up"),
	CleanSweepFinal("2010 VEX Robotics Competition World Championship", "Clean Sweep");
	
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getNewSeasonName() {
		return newSeasonName;
	}
	public void setNewSeasonName(String newSeasonName) {
		this.newSeasonName = newSeasonName;
	}
	private String compName;
	private String newSeasonName;
	
	FinalCompetition(String compName, String newSeasonName) {
		this.compName = compName;
		this.newSeasonName = newSeasonName;
	}
}

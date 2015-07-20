package org.webcrawler.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CompWriter {
	public static WritingObject completedLinks = new WritingObject("CompletedLinks.txt");
	public static WritingObject infoUploadedLinks = new WritingObject("InfoUploadedLinks.txt");
	public static WritingObject matchesUploadedLinks = new WritingObject("MatchesUploadedLinks.txt");
	public static WritingObject infoUploadedTeams = new WritingObject("InfoUploadedTeams.txt");
}

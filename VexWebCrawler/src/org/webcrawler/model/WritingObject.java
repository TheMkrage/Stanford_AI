package org.webcrawler.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WritingObject {
	private ArrayList<String> links;
	private String filename = "";
	private String dir = "";
	private File file;

	public WritingObject(String filename) {
		this.filename = filename;
		loadFromFiles();
		try {
			file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WritingObject(String dir, String filename) {
		this.filename = filename;
		this.dir = dir;
		try {
			file = new File(dir, filename);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadFromFiles();
	}

	public void writeLink(String str) {
		if (!links.contains(str)) {
			links.add(str);
			FileWriter writer = null;
			try {
				if (dir.isEmpty()) {
					writer = new FileWriter(new File(filename), true);
				}else {
					writer = new FileWriter(new File(dir, filename), true);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try (BufferedWriter bwriter = new BufferedWriter(writer)) {
				System.out.println("WRITING " + str + " to " + file.getName());
				bwriter.newLine();
				bwriter.write(str);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		}
	}

	public boolean isLinkInArray(String str) {
		if (!links.isEmpty() && links.contains(str)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getLinks() {
		return links;
	}

	public void loadFromFiles() {
		if (dir.isEmpty() || dir == "" ) {
			links = new ArrayList<String>();
			try (BufferedReader br = new BufferedReader(
					new FileReader(filename))) {
				for (String line; (line = br.readLine()) != null;) {
					links.add(line);
				}
			} catch (Exception e) {

			}
		}else {
			links = new ArrayList<String>();
			try (BufferedReader br = new BufferedReader(
					new FileReader(new File(dir, filename)))) {
				for (String line; (line = br.readLine()) != null;) {
					links.add(line);
				}
			} catch (Exception e) {

			}
			
		}
	}
}

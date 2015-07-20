package org.webcrawler.threads;

import java.util.ArrayList;

import org.webcrawler.frame.MainFrame;

public class ThreadManager {
	public static boolean isRunning = true;
	private static ArrayList<String[]> links;
	private static ArrayList<String[]> skippedLinks = new ArrayList<String[]>();
	private static int count = 0;
	private static int skipCount = 0;

	public static void setLinks(ArrayList<String[]> links1) {
		links = links1;
		
	}

	public static String[] getNextLink() {
		String[] link = links.get(count);
		count++;
		if (count == links.size()) {
			isRunning = false;
			System.out.println("DONE");
		}
		//System.out.println("count: " + count + " size: " + links.size());
		//System.out.println("PERCENT: " + count/links.size());
		MainFrame.getInstance().setPercentage(100*count/links.size());
		return link;

	}
	
	public static void addSkipLink(String[] link) {
		skippedLinks.add(link);
	}

	public static ArrayList<String[]> getSkippedLinks() {
		return skippedLinks;
	}

	public static void setSkippedLinks(ArrayList<String[]> skippedLinks) {
		ThreadManager.skippedLinks = skippedLinks;
	}

	public static String[] getNextSkipLink() {
		String[] link = skippedLinks.get(skipCount);
		skipCount++;
		MainFrame.getInstance().subtractSkips();
		return link;
	}

}

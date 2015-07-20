package org.webcrawler.bytes;

public class ByteCounter {
	private static int bytes = 0;
	public static void addBytes(String str) {
		bytes += str.length();
		System.out.println("Bytes Uploaded: " + bytes + " with new string: " + str);
	}
}

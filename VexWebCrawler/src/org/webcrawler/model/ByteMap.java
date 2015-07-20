package org.webcrawler.model;

import java.util.HashMap;

import org.webcrawler.bytes.ByteCounter;

public class ByteMap extends HashMap<String, Object> {
	public Object put(String str, Object o) {
		if (o instanceof Boolean) {
			ByteCounter.addBytes("a");
		}else {
			ByteCounter.addBytes((String) o); 
		}
		
		return super.put(str, o);
	}
}

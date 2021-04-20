package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
		Map<String, Integer> resultsMap = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase();
				if (line.length() < 15) {
					incOccurrence(resultsMap, "SHORTER_THAN_15");

				}
				if(line.contains("shaker") && line.contains("mover")){
					incOccurrence(resultsMap, "SHAKER_MENTIONS");
					incOccurrence(resultsMap, "MOVER_MENTIONS");
				}
				else if (line.contains("mover")) {
					incOccurrence(resultsMap, "MOVER_MENTIONS");

				} else if (line.contains("shaker")) {
					incOccurrence(resultsMap, "SHAKER_MENTIONS");

				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}

		System.out.println("this is the result map: " + resultsMap);
		return resultsMap;
		
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {
		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}

}

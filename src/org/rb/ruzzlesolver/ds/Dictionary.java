package org.rb.ruzzlesolver.ds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

	private Set<String> wordSet;
	
	public Dictionary() {
		wordSet = new HashSet<String>();
		readWordsFromFile();
	}
	
	public void readWordsFromFile(){

		try {
			FileReader fileReader;
			fileReader = new FileReader("wordList.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while((line=bufferedReader.readLine())!=null){
				wordSet.add(line.trim());
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isWordValid(String word){
		return wordSet.contains(word.toUpperCase());
	}

}

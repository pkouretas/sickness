package com.kouretas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SicknessExtractor {

	public List<String> extractIllnesses(String description) {
		// Break description into sentences
		List<String> newSentences = this.getSentences(description);
		
		// For each sentence, generate valid word sequences
		List<String> allValidWordSequences = new ArrayList<String>();
		newSentences.forEach(s -> allValidWordSequences.addAll(this.getWordCombinations(s)));

		SicknessMap medicalRecords =  SicknessMap.getInstance();
		List<String> results = allValidWordSequences.stream()
				.filter(potentialIllness -> medicalRecords.contains(potentialIllness))
				.map(actualIllness -> medicalRecords.getByKey(actualIllness))
				.collect(Collectors.toList());
		return results;
	}

	private List<String> getSentences(String document){
		List<String> sentences = Arrays.asList(document.split("\\."));
	
		return sentences.stream()
				.map(s -> s.trim())
				.collect(Collectors.toList());
	}
	
	private List<String> getWordCombinations(String sentence){
		List<String> words = Arrays.asList(sentence.split(" "));
		List<String> combinations = new ArrayList<String>();
		
		int wordCount = words.size();
		for(int i =0; i < wordCount; i++){
			List<String> startingFromI = getRestCombinations(words.subList(i, wordCount));
			combinations.addAll(startingFromI);
		}
		
		return combinations;
	}
	
	// Recursive
	private List<String> getRestCombinations(List<String> words){
		List<String> wordsCombinations = new ArrayList<String>();
		List<String> wordsSubList;
		
		if (words.size() > 0){ 
			String firstWord = words.get(0);
			wordsCombinations.add(firstWord);
			wordsSubList = words.subList(1, words.size());
			wordsCombinations.addAll(getRestCombinations(wordsSubList).stream()
					.map(w -> firstWord + " " + w)
					.collect(Collectors.toList()));
		}
		return wordsCombinations;
	}
}

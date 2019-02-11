package com.scrabble.service;

import java.util.List;
import java.util.Map;

import com.scrabble.pojo.BestScore;

public interface SrabbleImpl {
	
	
	public int calculateScore(String value);
	
	public BestScore getBestScore();
	
	public Map<Integer, List<String>> createHistogramByScore();

	public void play();

	public Map<Integer, List<String>> getTopBestScore(int topScore);
	
	
}

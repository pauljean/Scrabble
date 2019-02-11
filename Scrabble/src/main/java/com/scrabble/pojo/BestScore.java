package com.scrabble.pojo;

public class BestScore {

	private int bestScore = 0;
	private String bestWord;

	public BestScore() {

	}

	public BestScore(String bestWord, int bestScore) {

		this.bestWord = bestWord;
		this.bestScore = bestScore;

	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	public String getBestWord() {
		return bestWord;
	}

	public void setBestWord(String bestWord) {
		this.bestWord = bestWord;
	}

}

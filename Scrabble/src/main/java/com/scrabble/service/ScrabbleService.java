package com.scrabble.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrabble.pojo.BestScore;
import com.scrabble.utils.Stringconvert;

@Service
public class ScrabbleService implements SrabbleImpl {

	@Autowired
	private Stringconvert stringconvert;

	private ScrabbleRuleImpl scrabbleRuleImpl;
	private DictionaryImpl dictionaryImpl;
	private List<String> playStream;
	private BestScore bestScore;
	private Map<Integer, List<String>> histogram;

	@Override
	public void play() {

		histogram = new HashMap<Integer, List<String>>();
		bestScore = new BestScore();

		playStream.forEach(word -> {

			if (dictionaryImpl.checkWord(word)) {
				int score = calculateScore(word);

				if (score > bestScore.getBestScore()) {

					bestScore.setBestScore(score);
					bestScore.setBestWord(word);
				}

				if (histogram.containsKey(score)) {

					histogram.get(score).add(word);

				} else {
					List<String> lists = new ArrayList<>();
					lists.add(word);
					histogram.put(score, lists);
				}

			}
		});

	}

	@Override
	public int calculateScore(String word) {

		int score = 0;
		Character[] wordArray = stringconvert.toCharsArray(word);

		for (Character value : wordArray) {
			score += scrabbleRuleImpl.checkPoint(value.toString());

		}
		return score;
	}

	@Override
	public BestScore getBestScore() {

		return bestScore;

	}

	@Override
	public Map<Integer, List<String>> createHistogramByScore() {

		return histogram;

	}

	@Override
	public Map<Integer, List<String>> getTopBestScore(int topScore) {

		return histogram.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).limit(topScore)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

	}

	public Stringconvert getStringconvert() {
		return stringconvert;
	}

	public void setStringconvert(Stringconvert stringconvert) {
		this.stringconvert = stringconvert;
	}

	public ScrabbleRuleImpl getScrabbleRuleImpl() {
		return scrabbleRuleImpl;
	}

	public void setScrabbleRuleImpl(ScrabbleRuleImpl scrabbleRuleImpl) {
		this.scrabbleRuleImpl = scrabbleRuleImpl;
	}

	public DictionaryImpl getDictionaryImpl() {
		return dictionaryImpl;
	}

	public void setDictionaryImpl(DictionaryImpl dictionaryImpl) {
		this.dictionaryImpl = dictionaryImpl;
	}

	public List<String> getPlayStream() {
		return playStream;
	}

	public void setPlayStream(List<String> playStream) {
		this.playStream = playStream;
	}

}

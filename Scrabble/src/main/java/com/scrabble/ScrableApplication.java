package com.scrabble;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.scrabble.pojo.BestScore;
import com.scrabble.service.DictionaryService;
import com.scrabble.service.ScrabbleRuleA;
import com.scrabble.service.ScrabbleService;
import com.scrabble.utils.ScrabbleFileReader;

@SpringBootApplication
public class ScrableApplication {

	private String dictionary = "dictionary.txt";
	private String playedwords = "playedwords.txt";

	private List<String> dicoStream;
	private List<String> playStream;

	@Autowired
	private ScrabbleFileReader scrabbleFileReader;

	@Autowired
	private ScrabbleService scrabble;

	@Autowired
	private ScrabbleRuleA scrabbleRuleA;

	@Autowired
	private DictionaryService dictionaryService;

	Logger logger = LoggerFactory.getLogger(ScrableApplication.class);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ScrableApplication.class, args);

		ScrableApplication application = applicationContext.getBean(ScrableApplication.class);

		application.initGame();
		application.play();

	}

	private void initGame() {

		logger.info("--------------------init the Game-----------------------------");
		dicoStream = scrabbleFileReader.readFile(dictionary);
		playStream = scrabbleFileReader.readFile(playedwords);

		dictionaryService.setDicoStream(dicoStream);
		scrabble.setScrabbleRuleImpl(scrabbleRuleA);
		scrabble.setDictionaryImpl(dictionaryService);
		scrabble.setPlayStream(playStream);

		scrabble.play();

	}

	private void play() {

		scrabble.calculateScore("hello");

		int score = scrabble.calculateScore("hello");

		logger.info("hello ==> as score {} ", score);

		logger.info("-----------------------------------------------------------------");

		BestScore bestScore = scrabble.getBestScore();
		logger.info("best word is {} with score {}", bestScore.getBestWord(), bestScore.getBestScore());

		logger.info("-----------------------------------------------------------------");

		Map<Integer, List<String>> histogram = scrabble.createHistogramByScore();

		for (int hscore : histogram.keySet()) {

			logger.info("For score {} we have {} word", hscore, histogram.get(hscore).size());

		}

		logger.info("Histogram size is {}", histogram.size());

		logger.info("-----------------------------------------------------------------");

		Map<Integer, List<String>> topScore = scrabble.getTopBestScore(3);

		for (int hscore : topScore.keySet()) {

			logger.info("For score {} we have {} word", hscore, topScore.get(hscore));

		}

	}
}

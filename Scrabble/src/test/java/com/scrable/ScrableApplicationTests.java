package com.scrable;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scrabble.ScrableApplication;
import com.scrabble.service.DictionaryService;
import com.scrabble.service.ScrabbleRuleA;
import com.scrabble.service.ScrabbleService;
import com.scrabble.utils.ScrabbleFileReader;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = ScrableApplication.class)
public class ScrableApplicationTests {

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

	@Before
	public void contextLoads() {

		dicoStream = scrabbleFileReader.readFile(dictionary);
		playStream = scrabbleFileReader.readFile(playedwords);

		dictionaryService.setDicoStream(dicoStream);
		scrabble.setScrabbleRuleImpl(scrabbleRuleA);
		scrabble.setDictionaryImpl(dictionaryService);
		scrabble.setPlayStream(playStream);
		scrabble.play();

		
	}

	@Test
	public void calculateScoreTest() {
		Assert.assertEquals(8, scrabble.calculateScore("hello"));
	
	}
	
	@Test
	public void getBestScoreTest() {
		
		Assert.assertEquals(33, scrabble.getBestScore().getBestScore());
		Assert.assertEquals("whizzing", scrabble.getBestScore().getBestWord());
	}
	
	@Test
	public void createHistogramByScoreTest() {
		
		Assert.assertEquals(360, scrabble.createHistogramByScore().get(16).size());
		Assert.assertEquals(1459, scrabble.createHistogramByScore().get(8).size());
		
		Assert.assertEquals(29, scrabble.createHistogramByScore().size());
	}
	
	@Test
	public void getTopBestScoreTest() {
		
		Assert.assertEquals("[whizzing]", scrabble.getTopBestScore(3).get(33).toString());
		Assert.assertEquals("[buzzards]", scrabble.getTopBestScore(3).get(29).toString());
		
		
		Assert.assertEquals(3, scrabble.getTopBestScore(3).size());
	}

}

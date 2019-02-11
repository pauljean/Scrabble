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
import com.scrabble.service.ScrabbleRuleB;
import com.scrabble.service.ScrabbleService;
import com.scrabble.utils.ScrabbleFileReader;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = ScrableApplication.class)
public class ScrableApplicationTestsB {
	
	private String dictionary = "dictionary.txt";
	private String playedwords = "playedwords.txt";

	private List<String> dicoStream;
	private List<String> playStream;

	@Autowired
	private ScrabbleFileReader scrabbleFileReader;

	@Autowired
	private ScrabbleService scrabble;

	@Autowired
	private ScrabbleRuleB scrabbleRuleB;

	@Autowired
	private DictionaryService dictionaryService;

	@Before
	public void contextLoads() {

		dicoStream = scrabbleFileReader.readFile(dictionary);
		playStream = scrabbleFileReader.readFile(playedwords);

		dictionaryService.setDicoStream(dicoStream);
		scrabble.setScrabbleRuleImpl(scrabbleRuleB);
		scrabble.setDictionaryImpl(dictionaryService);
		scrabble.setPlayStream(playStream);
		//scrabble.play();

		
	}
	
	
	@Test
	public void calculateScoreTest() {
		Assert.assertEquals(23, scrabble.calculateScore("whizzing"));
		
		Assert.assertEquals(7, scrabble.calculateScore("delated"));
		
		Assert.assertEquals(26, scrabble.calculateScore("squeezes"));
	
	}
	
}

package com.scrabble.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


@Component
public class ScrabbleFileReader {
	
	public List<String>readFile(String fileName) {
		
		List<String> list = null;
	
			try {
				list = Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())).collect(Collectors.toList());
			} catch (IOException | URISyntaxException e) {
			
				e.printStackTrace();
			}

		return list;
		
	}

}

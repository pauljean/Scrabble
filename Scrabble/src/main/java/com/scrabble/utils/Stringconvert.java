package com.scrabble.utils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class Stringconvert {
	
	
    public Character[] toCharsArray(String value) {
        IntStream is = value.chars();
        Stream<Character> characterStream = is.mapToObj(c -> (char) c);
        return (Character[]) characterStream.toArray(Character[]::new);
    }

}

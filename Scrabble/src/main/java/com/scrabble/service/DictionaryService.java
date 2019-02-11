package com.scrabble.service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class DictionaryService implements DictionaryImpl {

	private List<String> dicoStream;

	@Override
	public boolean checkWord(String value) {

		Supplier<Stream<String>> streamSupplier = () -> dicoStream.stream();

		return streamSupplier.get().anyMatch(word -> word.equals(value));

	}

	public List<String> getDicoStream() {
		return dicoStream;
	}

	public void setDicoStream(List<String> dicoStream) {
		this.dicoStream = dicoStream;
	}

}

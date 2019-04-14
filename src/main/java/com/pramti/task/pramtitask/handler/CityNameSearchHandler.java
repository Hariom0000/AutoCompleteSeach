package com.pramti.task.pramtitask.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.pramti.task.pramtitask.businessobject.Trie;

/**
 * This class load the city file from path and create Trie tree that used for
 * searching the string
 * 
 * @author Hariom
 *
 */
@Component
public class CityNameSearchHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(CityNameSearchHandler.class);

	List<String> allString = new ArrayList<String>();

	@Autowired
	ResourceLoader resourceLoader;

	Trie trie = null;

	@PostConstruct
	public void readFileInMemory() {
		trie = new Trie();
		Resource resource = resourceLoader.getResource("classpath:City-Names.csv");
		try (FileReader fileReader = new FileReader(resource.getFile());
				BufferedReader reader = new BufferedReader(fileReader)) {
			// reading header
			String currentLine = reader.readLine();
			currentLine = reader.readLine();

			while (currentLine != null) {
				String[] words = currentLine.split(",");
				trie.insert(words[0]);
				allString.add(words[0]);
				currentLine = reader.readLine();
			}
		} catch (IOException fileLoadException) {
			LOGGER.error("Error while loading file {}", fileLoadException);
		}
	}

	public void autoComplete(String prefix, int autoCompleteLimit) {
		System.out.println(System.currentTimeMillis());
		trie.autocomplete(prefix, autoCompleteLimit);
		System.out.println(System.currentTimeMillis());
		searchFromList(prefix, autoCompleteLimit);
		System.out.println(System.currentTimeMillis());
	}

	private List<String> searchFromList(final String prefix, int autoCompleteLimit) {

		ArrayList<String> resultString = new ArrayList<>();
		allString.stream().filter(str -> str.startsWith(prefix)).map(str -> resultString.add(str))
				.limit(autoCompleteLimit);

		return resultString;
	}
}
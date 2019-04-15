package com.pramti.task.pramtitask.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	private final Logger logger = LoggerFactory.getLogger(CityNameSearchHandler.class);

	@Autowired
	ResourceLoader resourceLoader;

	Trie trie = null;

	/**
	 * This post construct method used to load the file on service startup.
	 */
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
				currentLine = reader.readLine();
			}
		} catch (IOException fileLoadException) {
			logger.error("Error while loading file {}", fileLoadException);
		}
	}

	/**
	 * This method use to search the auto complete city names  
	 * @param prefix
	 * @param autoCompleteLimit
	 * @return autoComplete city name
	 */
	public List<String> autoComplete(String prefix, int autoCompleteLimit) {
		return trie.autocomplete(prefix, autoCompleteLimit);
	}

}
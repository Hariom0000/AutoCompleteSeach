package com.pramti.task.pramtitask.service;

import java.util.List;

/**
 * This class use to search the string from city name file
 * @author Hariom
 *
 */
public interface CityNameSearchService {
	
	/**
	 * @param prefix
	 * @param autoCompleteLimit
	 * @return list of auto complete string 
	 */
	public List<String> autocomplete(String prefix, int autoCompleteLimit);
}

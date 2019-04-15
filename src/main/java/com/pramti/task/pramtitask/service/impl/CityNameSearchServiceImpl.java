package com.pramti.task.pramtitask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramti.task.pramtitask.handler.CityNameSearchHandler;
import com.pramti.task.pramtitask.service.CityNameSearchService;

/**
 * This class use to search the string from city name file
 * @author Hariom
 */
@Service
public class CityNameSearchServiceImpl implements CityNameSearchService {

	@Autowired
	private CityNameSearchHandler cityNameSearchHandler;

	/**
     * {@inheritDoc}
     */
	@Override
	public List<String> autocomplete(String prefix, int autoCompleteLimit) {
		return cityNameSearchHandler.autoComplete(prefix, autoCompleteLimit);
	}

}

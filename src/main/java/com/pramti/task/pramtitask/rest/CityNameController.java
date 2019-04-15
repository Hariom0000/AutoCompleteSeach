package com.pramti.task.pramtitask.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pramti.task.pramtitask.service.CityNameSearchService;

@Controller
public class CityNameController {

	@Autowired
	CityNameSearchService cityNameSearchService;

	@RequestMapping(value = "/suggest_cities", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<String>> auctoCompleteCityNames(@RequestParam("start") String start,
			@RequestParam("atmost") Integer atmost) {
		cityNameSearchService.autocomplete(start, atmost);
		return new ResponseEntity<>(cityNameSearchService.autocomplete(start, atmost),HttpStatus.OK);
	}

}

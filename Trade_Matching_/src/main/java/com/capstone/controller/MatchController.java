package com.capstone.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.models.MatchingModel;
import com.capstone.service.MatchInterfaceService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/osttra/match")

public class MatchController {
//	API 2 -> Search for a trade 
//	By Party & Trade Reference Number => List the specific trade and matching score / counterparty & counterparty trade reference number 

	
//	@Autowired
//	private MatchRepository matchRepo;
	
	@Autowired
	private MatchInterfaceService matchService;
	
	@ApiOperation(value = "Get all matches of a party w with scores.")
	@GetMapping("/Matches")
	public ResponseEntity<?> getMatches ( @RequestParam(required = true) String PartyA_tradeRefNum) {
		System.out.println("CONTROLLER:"+ PartyA_tradeRefNum);
//		System.out.println("Service in MatchController : "+ matchService);
		//HdfcDelhi004
		return matchService.getMatches(PartyA_tradeRefNum);
	}
	
	@ApiOperation(value = "Serach for a party &traderefNumber to Get all the matching parties with its matching scores.")
	@GetMapping("/getMatchingPartiesAndMatchingScores")
	public List<MatchingModel> getMatchingPartiesAndMatchingScores ( @RequestParam(required = false) String PartyA, @RequestParam(required = true) String PartyA_tradeRefNum) {
		System.out.println("Searching for a trade's matching parties and Scores:"+PartyA +PartyA_tradeRefNum);
		

		return matchService.getMatchingPartiesAndMatchingScores(PartyA_tradeRefNum);
	}
	
	
	
	

}

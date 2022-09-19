package com.osttra.tradeMatching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.MatchedTrade;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.service.MatchingService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/osttra/match")
public class MatchingController {
//  Api 1- > Matching after Insertion and Update of a trade.

//  API 2 -> Search for a trade
//  By Party & Trade Reference Number => List the specific trade and matching score / counterparty & counterparty trade reference number

//  @Autowired
//  private MatchRepository matchRepo;

	@Autowired
	private MatchingService matchService;
	@Autowired
	private TradeRepo tradeRepo;

	@ApiOperation(value = Constants.getAllMatchesOfAPartyWithScores)
	@GetMapping("/matches")
	public ResponseEntity<?> getMatches(@RequestParam(required = true) String PartyA_tradeRefNum) throws TradeNotFoundException {
		System.out.println("MATCHING_CONTROLLER: PARTY_A    ->" + PartyA_tradeRefNum);
		return matchService.getMatches(PartyA_tradeRefNum);
	}

	@ApiOperation(value = Constants.searchForAPartyAndTradeReferenceNumber)
	@GetMapping("/getMatchingPartiesAndMatchingScores")
	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(@RequestParam(required = false) String PartyA,
			@RequestParam(required = true) String PartyA_tradeRefNum) throws TradeNotFoundException {
		
		if(PartyA != null) {
			List<TradeData> trades = tradeRepo.findByParty(PartyA);
			if(trades.isEmpty()) {
				throw new TradeNotFoundException(Constants.noPartiesExist+" "+PartyA);
			}
		}
		System.out.println("Searching for a trade's matching parties and Scores:" + PartyA + PartyA_tradeRefNum);

		return matchService.getMatchingPartiesAndMatchingScores(PartyA_tradeRefNum);
	}

}

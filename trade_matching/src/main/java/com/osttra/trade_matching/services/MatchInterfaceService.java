package com.osttra.trade_matching.services;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.osttra.trade_matching.model.MatchingModel;


public interface MatchInterfaceService{

//	 public void createScore(MatchingModel matchScore);
	 public ResponseEntity<?> getMatches(String partyA_tradeRefNum);

	public List<MatchingModel> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum);

}

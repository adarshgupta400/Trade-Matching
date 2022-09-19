package com.osttra.tradeMatching.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.MatchedTrade;
@Service
public interface MatchingService {
//	 public void createScore(MatchingModel matchScore);
	public ResponseEntity<?> getMatches(String partyA_tradeRefNum) throws TradeNotFoundException;

	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum)
		throws TradeNotFoundException;
	
	void deleteAllCancelledTrades(String partyRef);
}

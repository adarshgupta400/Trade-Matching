package com.osttra.tradeMatching.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.matchAlgo.MatchingScore;
import com.osttra.tradeMatching.models.MatchedTrade;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;

@Component
public class MatchingServiceImpl implements MatchingService {

	@Autowired
	public MatchRepository matchRepo;

	@Autowired
	TradeRepo tradeRepo;
	
	@Override
	public ResponseEntity<HashMap<String, Integer>> getMatches(String partyA_tradeRefNum)
			throws TradeNotFoundException {
		
		TradeData tradeA = tradeRepo.findByTradeRefNum(partyA_tradeRefNum);
		if(tradeA==null) {
			//No such trade found 
			throw new TradeNotFoundException("No Such Trade Found with Trade Reference number: "+partyA_tradeRefNum);	
		} else if(! tradeA.getStatus().equals("Unconfirmed") ){
			System.out.println("Sorry ! "+tradeA.getTradeRefNum() + " is "+tradeA.getStatus());
			throw new TradeNotFoundException(Constants.CanCallMatcherOnlyOnUnconfirmed+"  "+partyA_tradeRefNum+" is "+tradeA.getStatus());
		}

		String counterParty = tradeA.getCounterParty();
		System.out.println("Counterparty : " + counterParty);
		//tradeList-> for storing counterparty trades.
		ArrayList<TradeData> tradesList = new ArrayList<TradeData>();
		tradeRepo.findByPartyAndStatus(counterParty, "Unconfirmed").forEach(tradesList::add);
		System.out.println("Getting all counter unconfirmed trades in list:---------------");
		for (TradeData t : tradesList)
			System.out.println(t.getTradeRefNum() + "    :" + t.getPartyFullname());

		if (tradesList.isEmpty()) {
			//case 1
//			throw new TradeNotFoundException(" New Trade is created with trade reference number "
//					+ "'"+partyA_tradeRefNum+"', No matching trade found.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		
		HashMap<String, Integer> getScores = new HashMap<String, Integer>();
		MatchingScore matchingScore = new MatchingScore();
//			int flagForStopingIteration = 0;

		for (TradeData cpTrade : tradesList) {

			int percent = matchingScore.calculateMatchingScore(tradeA, cpTrade);
			getScores.put(cpTrade.getTradeRefNum(), percent);
			System.out.println("Matching Score between "+tradeA.getTradeRefNum()+cpTrade.getTradeRefNum()+percent);

			MatchedTrade newMatchScore = new MatchedTrade();
			newMatchScore.setA_tradeRefNum(tradeA.getTradeRefNum());
			newMatchScore.setB_tradeRefNum(cpTrade.getTradeRefNum());
			newMatchScore.setMatchingScore(percent);

			if (percent < 100) {
				int lack = 100 - percent;
				newMatchScore.setStatusAfterMatch("Unconfirmed by " + lack + "%.");
				matchRepo.save(newMatchScore);
			} else if (percent == 100) {
				// Stop other matchings and Confirm both trades A, B in TradeData.

				System.out.println("Hey!  100% match found.");

				tradeA.setVersion(tradeA.getVersion() + 1);
				tradeA.setVersionTimestamp(new java.util.Date(System.currentTimeMillis()));
				tradeA.setConfirmationTimestamp(new java.util.Date(System.currentTimeMillis()));
				tradeA.setStatus("Confirmed");
				System.out.println("Hurray! " + tradeA.getTradeRefNum() + "  got  " + tradeA.getStatus()	+ " & upgraded version = " + tradeA.getVersion());

				cpTrade.setVersion(cpTrade.getVersion() + 1);
				cpTrade.setStatus("Confirmed");
				cpTrade.setVersionTimestamp(new java.util.Date(System.currentTimeMillis()));
				cpTrade.setConfirmationTimestamp(new java.util.Date(System.currentTimeMillis()));
				System.out.println("Hurray! " + cpTrade.getTradeRefNum() + " got " + cpTrade.getStatus()	+ " & upgraded version = " + cpTrade.getVersion());
				
				tradeRepo.save(tradeA);
				tradeRepo.save(cpTrade);
				break;
			}

		}
		System.out.println("SCORES--------->" + getScores);
		if (getScores.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(getScores, HttpStatus.CREATED);

	}

	@Override
	public List<MatchedTrade> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum)
		throws TradeNotFoundException{
		
		List<MatchedTrade> matchList = matchRepo.findAllByA_tradeRefNum(partyA_tradeRefNum);
		if(!matchList.isEmpty()) {
			return matchList;
		}
		throw new TradeNotFoundException(Constants.NoMatchingPartiesAndMatchingScores+partyA_tradeRefNum);

	}
	
	@Override
	public void deleteAllCancelledTrades(String tradeRefNum) {
        List<MatchedTrade> partyTrades=matchRepo.findAllByA_tradeRefNum(tradeRefNum);
        List<MatchedTrade> counterPartyTrades=matchRepo.findAllByB_tradeRefNum(tradeRefNum);
        matchRepo.deleteAll(partyTrades);
        matchRepo.deleteAll(counterPartyTrades);
        if(tradeRefNum.equals(null)) {
        	throw new NullPointerException(Constants.noTradeReferenceNumber);
        }
    }

	
}

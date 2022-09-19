package com.osttra.tradeMatching.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

@Service
public interface TradeService {

	ResponseEntity<?> insertTrade(TradeDataInput tradeData) throws NoTradeValidation, TradeNotFoundException;
	TradeData getTradeByTradeRefNum(String tradeRefNum) throws TradeNotFoundException;
	List<TradeData> getAllTrade() throws TradeNotFoundException;
	List<TradeData> getTradesByParty(String party) throws TradeNotFoundException;
	List<TradeData> getTradesByPartyStatus(String party, String status) throws TradeNotFoundException;
	
	ResponseEntity<?> cancelTrade(String tradeRefNum) throws TradeNotFoundException, NoTradeValidation;
	public void changeStateToExit();
	
	ResponseEntity<?> updateTrade(String tradeRefNum, TradeDataInput updatesInTrade) 
			throws NoTradeValidation,TradeNotFoundException;

}

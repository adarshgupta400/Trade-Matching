package com.osttra.tradeMatching.utils;

import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

@Service
public class TradeBuilderImpl implements TradeBuilder {

	@Override
	public TradeData convertTradeDataFormat(TradeDataInput newTrade) {
		
		
		System.out.println("Builder"+newTrade);
		TradeData insertedTradeData = new TradeData();
        java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
        
        insertedTradeData.setParty(newTrade.getParty());
        insertedTradeData.setPartyFullname(newTrade.getPartyFullname());
        insertedTradeData.setPartyInstitution(newTrade.getPartyInstitution());
        insertedTradeData.setCounterParty(newTrade.getCounterParty());
        insertedTradeData.setCounterPartyFullname(newTrade.getCounterPartyFullname());
        insertedTradeData.setCounterPartyInstitution(newTrade.getCounterPartyInstitution());
        insertedTradeData.setBuyer(newTrade.getBuyer());
        insertedTradeData.setSeller(newTrade.getSeller());
        insertedTradeData.setInstrumentId(newTrade.getInstrumentId());
        insertedTradeData.setNotionalAmount(newTrade.getNotionalAmount());
        insertedTradeData.setCurrency(newTrade.getCurrency());
        insertedTradeData.setTradeDate(newTrade.getTradeDate());
        insertedTradeData.setEffectiveDate(newTrade.getEffectiveDate());
        insertedTradeData.setMaturityDate(newTrade.getMaturityDate());
        insertedTradeData.setTradeId(newTrade.getTradeId());
        insertedTradeData.setConfirmationTimestamp(currentDate);
        insertedTradeData.setCreationTimestamp(currentDate);
        insertedTradeData.setVersionTimestamp(currentDate);
        insertedTradeData.setStatus("unconfirmed");
        insertedTradeData.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
        insertedTradeData.setVersion(1);
        
        return insertedTradeData;
	}

}

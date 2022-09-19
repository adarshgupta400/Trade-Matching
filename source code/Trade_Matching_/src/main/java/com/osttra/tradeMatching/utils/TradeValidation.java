package com.osttra.tradeMatching.utils;

import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

public interface TradeValidation {

	String checkValidTrade(TradeData newTrade) throws NoTradeValidation;
	public String checkValidTradeOnUpdate(TradeData newTrade, TradeDataInput newUpdate, String oldTradeRefNum)
			throws NoTradeValidation ;
}
package com.osttra.tradeMatching.utils;

import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

@Service
public interface TradeBuilder {

	TradeData convertTradeDataFormat(TradeDataInput tradeDataInput);
	
}

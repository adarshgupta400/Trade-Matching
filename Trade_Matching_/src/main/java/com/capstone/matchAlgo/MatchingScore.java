package com.capstone.matchAlgo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.capstone.models.TradeDataModel;
import com.capstone.service.MatchInterfaceService;


public class MatchingScore {
	
//	@Autowired
//	TradeRepository tradeRepo;

	@Autowired
	MatchInterfaceService matchService;

//	@Autowired
//	MatchRepository matchRepo;
	
	public int calculateMatchingScore(TradeDataModel tradeA,TradeDataModel cpTrade) {
		HashMap<String, Integer> scores= new HashMap<String, Integer>();
		//8 matchings
		int tradeDatePercent= 10;
		int effDatePercent= 10;
		int instumentIdPercent= 18;
		int notionalAmountPercent= 10;
		int maturitydatePercent= 8;
		int currencyPercent= 8;
		int sellerPercent= 18;
		int buyerPercent= 18;
		
		int percent=0;
		String oppositePartyRef = cpTrade.getTradeRefNum();
		
		System.out.println(tradeA.getTradeRefNum()+" trade matching score calculation with "+cpTrade.getTradeRefNum()+"------------------------------------");
		
		if(tradeA.getSeller() .equals( cpTrade.getSeller())) {percent+= sellerPercent;System.out.println("1-Seller Matched");}
		if(tradeA.getBuyer() .equals(cpTrade.getBuyer()))	{percent+= buyerPercent;System.out.println("2-Buyer Matched");}
		if(tradeA.getTradeDate() .equals(cpTrade.getTradeDate()) ) {percent+= tradeDatePercent;System.out.println("3-TradeDate Matched");}
		if(tradeA.getEffectiveDate().equals(cpTrade.getEffectiveDate())) {percent+= effDatePercent;System.out.println("4-Effective data Matched");}
		if(tradeA.getInstrumentId() .equals( cpTrade.getInstrumentId())) {percent+= instumentIdPercent;System.out.println("5-Instrument Id Matched");}
		if(tradeA.getNotionalAmount() == cpTrade.getNotionalAmount()) { percent+= notionalAmountPercent;System.out.println("6-Notional Amount Matched");}
		if(tradeA.getMaturityDate().equals(cpTrade.getMaturityDate())) {percent+= maturitydatePercent ;System.out.println("7-MaturityDate Matched");}
		if(tradeA.getCurrency().equals( cpTrade.getCurrency())) {percent+= currencyPercent;System.out.println("8-Currency Matched");}

		System.out.println("------------------------------------------------");
		scores.put( oppositePartyRef, percent );
		//Update Unvconfirmed Matching Score Table---
		

//		int matchNo = 1;
//		
//		MatchingModel newMatchScore = new MatchingModel();
//		newMatchScore.setMatchNo(matchNo++);
//		newMatchScore.setA_tradeRefNum(tradeA.getTradeRefNum());
//		newMatchScore.setB_tradeRefNum(cpTrade.getTradeRefNum());
//		newMatchScore.setMatchingScore(percent);
//		if(percent == 100) newMatchScore.setStatusAfterMatch("Confirm");
//		else {			   newMatchScore.setStatusAfterMatch("Unconfirm");}
//		
//		System.err.println(newMatchScore.toString());
//		
//		System.out.println("matchService:    - -----"+matchService);
////		matchService.createScore(newMatchScore);
////		System.out.println(matchRepo);
////		System.out.println(tradeRepo);
////		matchRepo.save(newMatchScore);
//		
//		
//		if(percent == 100 ) {
//			System.out.println("100% match found.");
//			//Stop other matchings and Confirm both trades A, B in TradeData. 
//			//repo.save to update status and version in TradeData.
//			tradeA.setVersion(tradeA.getVersion()+1);
//			tradeA.setStatus("Confirmed");
//			System.out.println("Hurray! "+tradeA.getTradeRefNum()+"  got  "+tradeA.getStatus()+ ", upgraded version"+tradeA.getVersion());
//			
//			cpTrade.setVersion(cpTrade.getVersion()+1);
//			cpTrade.setStatus("Confirmed");
//			System.out.println("Hurray! "+cpTrade.getTradeRefNum()+" got "+cpTrade.getStatus()+", upgraded version"+cpTrade.getVersion());
//		}
		
		
//		System.out.println(Arrays.asList(map)); // method 1
//		System.out.println(Collections.singletonList(map)); // method 
		
//		System.out.println(Arrays.asList(scores)); 
		
		return percent;
//		return scores;
	}

}

//Trade Properties	Data Type	Null Constraint	Is Matching Field	Match Weight
//TradeId	VARCHAR2(20)	Not Null - CK		
//Party	VARCHAR2(20)	 		
//TradeRefNum	VARCHAR2(40)	Not Null - PK		
//PartyInstitution	VARCHAR2(20)	Not Null		
//Counterpary	VARCHAR2(20)	Not Null		
//CounterPartyInstitution	VARCHAR2(20)	Not Null		
//PartyFullName	VARCHAR2(200)			
//CounterpartyFullName	VARCHAR2(200)			
//TradeDate	DATE	Not Null	Y	10%
//EffectiveDate	DATE	Not Null	Y	10%
//InstrumentId	VARCHAR2(40)	Not Null	Y	18%
//NotionalAmount	NUMBER	Not Null	Y	10%
//Maturity Date	DATE	Not Null	Y	8%
//Currency	VARCHAR2(3)	Not Null	Y	8%
//Seller	VARCHAR2(20)	Not Null	Y	18%
//Buyer	VARCHAR2(20)	Not Null	Y	18%
//CreationTimeStamp	TIMESTAMP	Not Null		
//VersionTimeStamp	TIMESTAMP	Not Null		
//ConfirmationTimeStamp	TIMESTAMP			
//Version	NUMBER			
//Status	VARCHAR2(50)	Not Null		
//				
//		UNCONFIRMED		
//		CONFIRMED		
//		CANCELLED		
//		EXIT		
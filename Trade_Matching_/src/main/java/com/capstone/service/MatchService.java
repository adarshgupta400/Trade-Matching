package com.capstone.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.matchAlgo.MatchingScore;
import com.capstone.models.MatchingModel;
import com.capstone.models.TradeDataModel;
import com.capstone.repo.MatchRepository;
import com.capstone.repo.TradeRepo;

@Service
public class MatchService implements MatchInterfaceService {

	@Autowired
	public MatchRepository matchRepo;

	@Autowired
	TradeRepo tradeRepo;

	@Override
	public ResponseEntity<HashMap<String, Integer>> getMatches(String partyA_tradeRefNum) {
		try {
			ArrayList<TradeDataModel> tradesList = new ArrayList<TradeDataModel>();
			TradeDataModel tradeA = tradeRepo.findById(partyA_tradeRefNum);
			if (tradeA != null) {
				System.out.println("TRADE A-----> : " + tradeA.getTradeRefNum());
				if (!tradeA.getStatus().equals("Unconfirm")) {
					System.out.println(tradeA.getTradeRefNum() + " is already matched & confirmed.");
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}

				String counterParty = tradeA.getCounterParty();
				System.out.println("Counterparty : " + counterParty);

//						tradeRepo.findByPartyContaining(counterParty).forEach(tradesList::add);
				tradeRepo.findByPartyAndStatus(counterParty, "Unconfirm").forEach(tradesList::add);
				System.out.println("Getting all counter unconfirmed trades in list:---------------");
				System.out.println(tradesList);

				if (tradesList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				HashMap<String, Integer> getScores = new HashMap<String, Integer>();
				MatchingScore matchingScore = new MatchingScore();
				int flagForStopingIteration = 0;

				for (TradeDataModel cpTrade : tradesList) {

					if (flagForStopingIteration == 1)  break;
					
					getScores.put(cpTrade.getTradeRefNum(), matchingScore.calculateMatchingScore(tradeA, cpTrade));
					// -----------------------Test HdfcDelhi006 , JpmorganNewyork003
					int percent = matchingScore.calculateMatchingScore(tradeA, cpTrade);

					MatchingModel newMatchScore = new MatchingModel();
					newMatchScore.setA_tradeRefNum(tradeA.getTradeRefNum());
					newMatchScore.setB_tradeRefNum(cpTrade.getTradeRefNum());
					newMatchScore.setMatchingScore(percent);
					if (percent < 100) {
						int lack= 100-percent;
						newMatchScore.setStatusAfterMatch("Unconfirm by "+lack+"%.");
						matchRepo.save(newMatchScore);
					} else if( percent == 100) {
						// Stop other matchings and Confirm both trades A, B in TradeData.
						flagForStopingIteration = 1;

						System.out.println("100% match found.");
						
						tradeA.setVersion(tradeA.getVersion() + 1);
						tradeA.setStatus("Confirm");
						System.out.println("Hurray! " + tradeA.getTradeRefNum() + "  got  " + tradeA.getStatus()+ " & upgraded version = " + tradeA.getVersion());

						cpTrade.setVersion(cpTrade.getVersion() + 1);
						cpTrade.setStatus("Confirm");
						System.out.println("Hurray! " + cpTrade.getTradeRefNum() + " got " + cpTrade.getStatus()+ " & upgraded version = " + cpTrade.getVersion());
						// repo.save to update status and version in TradeData.
						
						tradeRepo.save(tradeA);     tradeRepo.save(cpTrade);
						//-------------------------------
					}
				}
				System.out.println("SCORES--------->" + getScores);
				if (getScores.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(getScores, HttpStatus.OK);
//						System.out.println(Arrays.asList(getScores)); 

			}
		} catch (Exception e) {e.printStackTrace(); }

		return null;
	}

	@Override
	public List<MatchingModel> getMatchingPartiesAndMatchingScores(String partyA_tradeRefNum) {
		// SbiGurgaon002
		 return  matchRepo.findAllByA_tradeRefNum(partyA_tradeRefNum);
	
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

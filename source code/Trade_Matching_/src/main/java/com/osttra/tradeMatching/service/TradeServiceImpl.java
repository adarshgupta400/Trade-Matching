package com.osttra.tradeMatching.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.constant.StatusEnum.Status;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.utils.TradeBuilder;
import com.osttra.tradeMatching.utils.TradeValidation;
import com.osttra.tradeMatching.utils.UpdateTradeBuilder;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TradeValidation validTrade;

	@Autowired
	private MatchingService matchingService;

	@Autowired
	private TradeBuilder tradeBuilder;

	@Autowired
	private UpdateTradeBuilder updateTradeBuilder;

	@Override
	public TradeData getTradeByTradeRefNum(String tradeRefNum) throws TradeNotFoundException {
		TradeData showTrade = tradeRepository.findByTradeRefNum(tradeRefNum);

		if (showTrade != null) {
			return showTrade;

		}
		throw new TradeNotFoundException(Constants.noTradeReferenceNumber + "" + tradeRefNum);
	}

	@Override
	public ResponseEntity<?> insertTrade(TradeDataInput newTrade) throws NoTradeValidation, TradeNotFoundException {
		TradeData insertedTradeData = tradeBuilder.convertTradeDataFormat(newTrade);

		String isValid = validTrade.checkValidTrade(insertedTradeData);
		if (!isValid.equals(Constants.VALID)) {
			throw new NoTradeValidation(Constants.notValidateTrade + "" + isValid);
		}
		tradeRepository.save(insertedTradeData);

		// Calling Matcher
		String PartyA_tradeRefNum = insertedTradeData.getTradeRefNum();
		ResponseEntity<?> ob = matchingService.getMatches(PartyA_tradeRefNum);

		if (ob.getBody() == null) {
			return new ResponseEntity<>(insertedTradeData, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(ob, HttpStatus.CREATED);

	}

	@Override
	public List<TradeData> getAllTrade() throws TradeNotFoundException {
		List<TradeData> allTrade = tradeRepository.findAll();
		if (!allTrade.isEmpty()) {
			return allTrade;
		}
		throw new TradeNotFoundException(Constants.noTradeAndInsertData);
	}

	@Override
	public List<TradeData> getTradesByParty(String party) throws TradeNotFoundException {

		List<TradeData> trades = tradeRepository.findByParty(party);
		if (!trades.isEmpty()) {
			return trades;
		} else {
			throw new TradeNotFoundException(Constants.noPartiesExist + "" + party);
		}

	}

	@Override
	public List<TradeData> getTradesByPartyStatus(String party, String status) throws TradeNotFoundException {

		List<TradeData> trades = tradeRepository.findByPartyAndStatus(party, status);
		if (!trades.isEmpty()) {
			return trades;
		} else {
			throw new TradeNotFoundException(Constants.noPartiesExist + "" + party + " and " + status);
		}

	}

	@Override
	public ResponseEntity<?> cancelTrade(String tradeRefNum) throws TradeNotFoundException, NoTradeValidation {

		TradeData getTrade = tradeRepository.findByTradeRefNum(tradeRefNum);
		if(getTrade==null) {
			throw new TradeNotFoundException(Constants.noTradeReferenceNumber+tradeRefNum);
		}
		
		if(!getTrade.getStatus().equals("Unconfirmed")) {
			throw new TradeNotFoundException(Constants.cancelOnlyUnconfirm); 
		}		
		
			getTrade.setStatus(Status.Cancel.name());
			getTrade.setVersion(getTrade.getVersion() + 1);
			getTrade.setVersionTimestamp(new Date(System.currentTimeMillis()));
			matchingService.deleteAllCancelledTrades(tradeRefNum);
			return  new ResponseEntity<>(tradeRepository.save(getTrade), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateTrade(String tradeRefNum, TradeDataInput newTrade)
			throws NoTradeValidation, TradeNotFoundException {
		if (newTrade == null) {
			throw new TradeNotFoundException(Constants.BodyError);
		}

		TradeData existingTrade = tradeRepository.findByTradeRefNum(tradeRefNum);
		if (existingTrade == null) {// No trade found with given tradeRefNum
			throw new TradeNotFoundException(Constants.ERROR6 + " :" + tradeRefNum);
		} else if (!existingTrade.getStatus().equals(Status.Unconfirmed.name())) {
			throw new TradeNotFoundException(Constants.canUpdateOnlyUnconfirmed);
		} else if ((newTrade.getTradeDate() != null) && !existingTrade.getTradeDate().equals(newTrade.getTradeDate())) {
			throw new TradeNotFoundException(Constants.tradeDateNotUpdatable);
		}
		// Saving old oldTradeRefNum to check UniqueNess Validation.
		String oldTradeRefNum = tradeRefNum;

		// Calling Update TradeBuilder to build a trdaeData body from TradeDataInput
		existingTrade = updateTradeBuilder.tradeConstruct(existingTrade, newTrade);

		// Calling validator to check updated trade in TradeData
		String isValid = validTrade.checkValidTradeOnUpdate(existingTrade, newTrade, oldTradeRefNum);
		if (!isValid.equals(Constants.VALID)) {
			throw new NoTradeValidation(Constants.notValidateTrade + "" + isValid);
		}

		tradeRepository.save(existingTrade);

		// Calling Matcher
		String PartyA_tradeRefNum = existingTrade.getTradeRefNum();
		ResponseEntity<?> ob = matchingService.getMatches(PartyA_tradeRefNum);
		// System.out.println("Your old Trade: " + tradeRefNum + " got updated with
		// TradeRefNumber " + PartyA_tradeRefNum);
		if (ob.getBody() == null) {
			// System.out.println("Trade is Updated but :" + PartyA_tradeRefNum + " --> No
			// Matching Trades Found");
			// throw new
			// TradeNotFoundException(Constants.tradeUpdatedButNoMatchingFound+""+PartyA_tradeRefNum);
			return new ResponseEntity<>(existingTrade, HttpStatus.CREATED);
		} else {
			// throw new
			// TradeNotFoundException(Constants.tradeUpdatedAsWellAsMatchFound+""+PartyA_tradeRefNum);
			return new ResponseEntity<>(ob, HttpStatus.CREATED);
		}

	}

	// set status to exit and update version after maturity date is achieved
	@Scheduled(cron = "* * 16 * * *")
	public void changeStateToExit() {
		List<TradeData> trade = tradeRepository.findAll();
		LocalDate currentDate = LocalDate.now();

		for (TradeData td : trade) {
			if (td.getMaturityDate().isBefore(currentDate)
					&& (td.getStatus().equals("Unconfirmed") || td.getStatus().equals("Confirmed"))) {
				System.err.println("Maturity Date " + td.getMaturityDate());
				System.err.println("Current Date " + currentDate);
				System.err.println("Status  " + td.getStatus());
				System.err.println("Version  " + td.getVersion());
				td.setStatus("Exit");
				td.setVersion((td.getVersion() + 1));
				System.out.println("Set Status : " + td.getStatus());
				System.out.println("Set Version : " + td.getVersion());
				td.setVersionTimestamp(new java.util.Date(System.currentTimeMillis()));
			}
			// end of if

			tradeRepository.save(td);

		} // end of for
	}
}

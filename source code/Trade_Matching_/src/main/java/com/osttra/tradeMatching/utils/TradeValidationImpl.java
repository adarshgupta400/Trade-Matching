package com.osttra.tradeMatching.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.PartyValidation;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;


@Service
public class TradeValidationImpl implements TradeValidation {

	@Autowired
	private TradeRepo tradeRepository;

	@Autowired
	private ValidationRepo ValidationRepository; 
	
	
	
	@Override
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String checkValidTrade(TradeData newTrade) throws NoTradeValidation {
		int checkDate = 0;
		int checkTradeDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;
		//String returnMsg =Constants.ERROR;
		newTrade.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
		newTrade.setId(0L);
		TradeData td1 = tradeRepository.findByTradeRefNum(newTrade.getTradeRefNum());
		newTrade.setVersion(1);
		newTrade.setStatus(Constants.statusExample);
		// Insert new TimesStampp in Td at insertion

		// party validation
		PartyValidation pvm = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(newTrade.getParty(),
				newTrade.getPartyInstitution(), newTrade.getPartyFullname());
		PartyValidation pvm1 = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(
				newTrade.getCounterParty(), newTrade.getCounterPartyInstitution(), newTrade.getCounterPartyFullname());
		
		if (pvm != null && pvm1 != null) {
			if (!newTrade.getParty().equals(newTrade.getCounterParty()))
				validPartyAndCParty = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR1);
		}

		if (td1 == null) {
			validTRN = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR2);
		}
		
		if (newTrade.getEffectiveDate().compareTo(newTrade.getTradeDate()) >= 0
				&& newTrade.getMaturityDate().compareTo(newTrade.getEffectiveDate()) >= 0) {
				checkDate = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR3);
		}
		
		if(newTrade.getTradeDate().compareTo(LocalDate.now()) >=0) {
			checkTradeDate = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR7);
		}
		
		PartyValidation validBuyer = ValidationRepository.findByParty(newTrade.getBuyer());
		PartyValidation validSeller = ValidationRepository.findByParty(newTrade.getSeller());
		if (validBuyer != null && validSeller != null &&( newTrade.getBuyer().equals(newTrade.getParty()) || newTrade.getBuyer().equals(newTrade.getCounterParty())) &&(newTrade.getSeller().equals(newTrade.getParty()) || newTrade.getSeller().equals(newTrade.getCounterParty()))){
			validBuyerSellerparty = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR4);
		}

		if (!newTrade.getBuyer().equals(newTrade.getSeller())) {
			validBuyerSeller = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR5);
		}
		
		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1 && checkTradeDate == 1) {
			return Constants.VALID;
		}
		throw new NoTradeValidation(Constants.ERROR);
	}



	@Override
	public String checkValidTradeOnUpdate(TradeData newTrade, TradeDataInput newUpdate, String oldTradeRefNum) 
			throws NoTradeValidation{

		int checkDate = 0;
		int checkTradeDate = 0;
		int validTRN = 0;
		int validBuyerSeller = 0;
		int validPartyAndCParty = 0;
		int validBuyerSellerparty = 0;
		
		// party and Institute validation
		PartyValidation pvm = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(newTrade.getParty(),
				newTrade.getPartyInstitution(), newTrade.getPartyFullname());
		PartyValidation pvm1 = ValidationRepository.findByPartyAndPartyInstitutionAndPartyFullName(
				newTrade.getCounterParty(), newTrade.getCounterPartyInstitution(), newTrade.getCounterPartyFullname());
		//Party and Cparty cannot be same.
		if (pvm != null && pvm1 != null && !newTrade.getParty().equals(newTrade.getCounterParty())) {
				validPartyAndCParty = 1;
		}else {
			//returnMsg="Please enter the valid party details or counter party details";
			throw new NoTradeValidation(Constants.ERROR1);
		}
		
		//TradeId shoud be unique for that party. So that TradeRefNum can be Unique.
		//RefNum can be equal to  existingTradeRefNum but should not be equal to any other trade's TradeRefNumber.
		if(newTrade.getTradeRefNum().equals(oldTradeRefNum)) {
			validTRN = 1;
		} else { //TradeRefNumber Has been changed so check it should be unique.
			TradeData existTradeRefNum = tradeRepository.findByTradeRefNum(newTrade.getTradeRefNum());
			if(existTradeRefNum == null) { System.out.println("New TradeRefNum"); validTRN = 1;}
			else {
				throw new NoTradeValidation(Constants.ERROR2);
			}			
		}

		//TradeDate<= EffDate <= MaturityDate
		if (newTrade.getEffectiveDate().compareTo(newTrade.getTradeDate()) >= 0
				&& newTrade.getMaturityDate().compareTo(newTrade.getEffectiveDate()) >= 0) {
				checkDate = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR3);
		}
		
		//In Update, tradeDate will be past one, tradeDate cannot be changed .
		checkTradeDate = 1;
//		if( (newUpdate.getTradeDate() !=null) &&(newTrade.getTradeDate().equals(newUpdate.getTradeDate()) ) ) {
//			checkTradeDate = 1;
//		}else {
//			returnMsg = Constants.ERROR7;
//		}
		
		
		PartyValidation validBuyer = ValidationRepository.findByParty(newTrade.getBuyer());
		PartyValidation validSeller = ValidationRepository.findByParty(newTrade.getSeller());
		if (validBuyer != null && validSeller != null&&( newTrade.getBuyer().equals(newTrade.getParty()) || newTrade.getBuyer().equals(newTrade.getCounterParty())) &&(newTrade.getSeller().equals(newTrade.getParty()) || newTrade.getSeller().equals(newTrade.getCounterParty()))) {
			validBuyerSellerparty = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR4);
		}

		if (!newTrade.getBuyer().equals(newTrade.getSeller())) {
			validBuyerSeller = 1;
		}else {
			throw new NoTradeValidation(Constants.ERROR5);
		}
		
		if (validTRN == 1 && checkDate == 1 && validBuyerSeller == 1 && validPartyAndCParty == 1
				&& validBuyerSellerparty == 1 && checkTradeDate == 1) {
			return Constants.VALID;
		}
		throw new NoTradeValidation(Constants.ERROR);
	}

}

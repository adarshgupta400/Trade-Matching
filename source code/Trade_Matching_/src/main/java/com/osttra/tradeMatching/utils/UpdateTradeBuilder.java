package com.osttra.tradeMatching.utils;

import org.springframework.stereotype.Component;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;

@Component
public class UpdateTradeBuilder {

	public TradeData tradeConstruct(TradeData existingTrade, TradeDataInput newTrade) {

		// tradeDataInput fields from user so check for Null then change
		existingTrade.setParty(newTrade.getParty() == null ? existingTrade.getParty() : newTrade.getParty());
		existingTrade.setPartyFullname(
				newTrade.getPartyFullname() == null ? existingTrade.getPartyFullname() : newTrade.getPartyFullname());
		existingTrade.setPartyInstitution(newTrade.getPartyInstitution() == null ? existingTrade.getPartyInstitution()
				: newTrade.getPartyInstitution());
		existingTrade.setCounterParty(
				newTrade.getCounterParty() == null ? existingTrade.getCounterParty() : newTrade.getCounterParty());
		existingTrade.setCounterPartyFullname(
				newTrade.getCounterPartyFullname() == null ? existingTrade.getCounterPartyFullname()
						: newTrade.getCounterPartyFullname());
		existingTrade.setCounterPartyInstitution(
				newTrade.getCounterPartyInstitution() == null ? existingTrade.getCounterPartyInstitution()
						: newTrade.getCounterPartyInstitution());
		existingTrade.setBuyer(newTrade.getBuyer() == null ? existingTrade.getBuyer() : newTrade.getBuyer());
		existingTrade.setSeller(newTrade.getSeller() == null ? existingTrade.getSeller() : newTrade.getSeller());
		existingTrade.setInstrumentId(
				newTrade.getInstrumentId() == null ? existingTrade.getInstrumentId() : newTrade.getInstrumentId());
		existingTrade.setNotionalAmount(
				newTrade.getNotionalAmount() == 0L ? existingTrade.getNotionalAmount() : newTrade.getNotionalAmount());
		existingTrade
				.setCurrency(newTrade.getCurrency() == null ? existingTrade.getCurrency() : newTrade.getCurrency());
		existingTrade.setTradeDate(existingTrade.getTradeDate()); // Trade date cannot be changed.
		existingTrade.setEffectiveDate(
				newTrade.getEffectiveDate() == null ? existingTrade.getEffectiveDate() : newTrade.getEffectiveDate());
		existingTrade.setMaturityDate(
				newTrade.getMaturityDate() == null ? existingTrade.getMaturityDate() : newTrade.getMaturityDate());
		existingTrade.setTradeId(newTrade.getTradeId() == null ? existingTrade.getTradeId() : newTrade.getTradeId());

		// Have not got from user so set from old one automatically
		existingTrade.setId(existingTrade.getId());
		existingTrade.setConfirmationTimestamp(new java.util.Date(System.currentTimeMillis()));
		existingTrade.setCreationTimestamp(existingTrade.getCreationTimestamp()); // creationTime will not change
		existingTrade.setVersionTimestamp(new java.util.Date(System.currentTimeMillis()));
		existingTrade.setStatus("Unconfirmed"); // Use Status.UNCONFIRM.name() instead.
//		System.out.println(Status.UNCONFIRM.name());
		existingTrade.setVersion(existingTrade.getVersion() + 1);

		// Setting Up TrdaeRefNum According to new changes
		if (newTrade.getParty() == null && newTrade.getTradeId() == null) {
			existingTrade.setTradeRefNum(existingTrade.getParty(), existingTrade.getTradeId());
		} else if (newTrade.getParty() == null && newTrade.getTradeId() != null) {
			existingTrade.setTradeRefNum(existingTrade.getParty(), newTrade.getTradeId());
		} else if (newTrade.getParty() != null && newTrade.getTradeId() == null) {
			existingTrade.setTradeRefNum(newTrade.getParty(), existingTrade.getTradeId());
		} else {
			existingTrade.setTradeRefNum(newTrade.getParty(), newTrade.getTradeId());
		}

		return existingTrade;

	}

}

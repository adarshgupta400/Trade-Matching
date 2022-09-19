package com.osttra.tradeMatching.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.osttra.tradeMatching.constant.Constants;

public class TradeDataInput {
	//Excluding 7 fields-> Id, Version, Status, tradeRefNum, CreationTimestamp, VersionTimestamp, ConfirmationTimestamp.

		@Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.partyMessage)
		private String party;
	    
		@Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.tradeIdMessage)
	    private String tradeId;
	    
		@Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.partyInstitutionMessage)
	    private String partyInstitution;
	    
		@Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.counterPartyMessage)
	    private String counterParty;
	    
		@Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.CouterPartyInstitutionMessage)
	    private String counterPartyInstitution;
	    
		@Column(nullable = false, length = 200)
		@Size(max = 200)
		@NotBlank(message = Constants.partyFullNameMessage)
	    private String partyFullname;
	    
		@Column(nullable = false, length = 200)
		@Size(max = 20)
		@NotBlank(message = Constants.counterPartyFullNameMessage)
	    private String counterPartyFullname;
	    
		
	    private LocalDate tradeDate;
	    
	    private LocalDate effectiveDate;
	    
	    @Column(nullable = false, length = 40)
		@NotBlank
		@Size(max = 40)
		@NotBlank(message = Constants.instrumentIdMessage)
	    private String InstrumentId;
	    
	    @Column
		@NotNull(message = Constants.notionalAmountMessage)
		@Positive
	    private double notionalAmount;
	    
	    
	    private LocalDate maturityDate;
	    
	    @Column(nullable = false, length = 3)
		@NotBlank(message = Constants.currencyMessage)
		@Size(max = 3)
	    private String currency;
	    
	    @Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message =Constants.sellerMessage)
	    private String seller;
	    
	    @Column(nullable = false, length = 20)
		@Size(max = 20)
		@NotBlank(message = Constants.buyerMessage)
	    private String buyer;



	   public TradeDataInput() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	   public TradeDataInput(String party, String tradeId, String partyInstitution, String counterParty,
	            String counterPartyInstitution, String partyFullname, String counterPartyFullname, LocalDate tradeDate,
	            LocalDate effectiveDate, String instrumentId, double notionalAmount, LocalDate maturityDate,
	            String currency, String seller, String buyer) {
	        super();
	        this.party = party;
	        this.tradeId = tradeId;
	        this.partyInstitution = partyInstitution;
	        this.counterParty = counterParty;
	        this.counterPartyInstitution = counterPartyInstitution;
	        this.partyFullname = partyFullname;
	        this.counterPartyFullname = counterPartyFullname;
	        this.tradeDate = tradeDate;
	        this.effectiveDate = effectiveDate;
	        InstrumentId = instrumentId;
	        this.notionalAmount = notionalAmount;
	        this.maturityDate = maturityDate;
	        this.currency = currency;
	        this.seller = seller;
	        this.buyer = buyer;
	    }



	   public String getParty() {
	        return party;
	    }



	   public void setParty(String party) {
	        this.party = party;
	    }



	   public String getTradeId() {
	        return tradeId;
	    }



	   public void setTradeId(String tradeId) {
	        this.tradeId = tradeId;
	    }



	   public String getPartyInstitution() {
	        return partyInstitution;
	    }



	   public void setPartyInstitution(String partyInstitution) {
	        this.partyInstitution = partyInstitution;
	    }



	   public String getCounterParty() {
	        return counterParty;
	    }



	   public void setCounterParty(String counterParty) {
	        this.counterParty = counterParty;
	    }



	   public String getCounterPartyInstitution() {
	        return counterPartyInstitution;
	    }



	   public void setCounterPartyInstitution(String counterPartyInstitution) {
	        this.counterPartyInstitution = counterPartyInstitution;
	    }



	   public String getPartyFullname() {
	        return partyFullname;
	    }



	   public void setPartyFullname(String partyFullname) {
	        this.partyFullname = partyFullname;
	    }



	   public String getCounterPartyFullname() {
	        return counterPartyFullname;
	    }



	   public void setCounterPartyFullname(String counterPartyFullname) {
	        this.counterPartyFullname = counterPartyFullname;
	    }



	   public LocalDate getTradeDate() {
	        return tradeDate;
	    }



	   public void setTradeDate(LocalDate tradeDate) {
	        this.tradeDate = tradeDate;
	    }



	   public LocalDate getEffectiveDate() {
	        return effectiveDate;
	    }



	   public void setEffectiveDate(LocalDate effectiveDate) {
	        this.effectiveDate = effectiveDate;
	    }



	   public String getInstrumentId() {
	        return InstrumentId;
	    }



	   public void setInstrumentId(String instrumentId) {
	        InstrumentId = instrumentId;
	    }



	   public double getNotionalAmount() {
	        return notionalAmount;
	    }



	   public void setNotionalAmount(double notionalAmount) {
	        this.notionalAmount = notionalAmount;
	    }



	   public LocalDate getMaturityDate() {
	        return maturityDate;
	    }



	   public void setMaturityDate(LocalDate maturityDate) {
	        this.maturityDate = maturityDate;
	    }



	   public String getCurrency() {
	        return currency;
	    }



	   public void setCurrency(String currency) {
	        this.currency = currency;
	    }



	   public String getSeller() {
	        return seller;
	    }



	   public void setSeller(String seller) {
	        this.seller = seller;
	    }



	   public String getBuyer() {
	        return buyer;
	    }



	   public void setBuyer(String buyer) {
	        this.buyer = buyer;
	    }	
	
}

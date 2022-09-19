package com.osttra.tradeMatching.constant;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

	// check TradeValidationImpl class
	public static final String VALID = "valid";
	public static final String EXIT = "Exit";
	public static final String MESSAGE1 = "valid";
	public static final String UNCONFIRMED = "Unconfirmed";
	public static final String ERROR = "error";
	public static final String ERROR1 = "Please enter the valid party details or counter party details";
	public static final String ERROR2 = "Already Exists tradeId in database please enter valid TradeId";
	public static final String ERROR3 = "Please check Effective Date or Trade date or Maturity date";
	public static final String ERROR4 = "Please check buyer or seller details";
	public static final String ERROR5 = "Buyer and Seller are same please correct it";
	public static final String ERROR6 = "Please enter valid Trade Reference Number";
	public static final String ERROR7 = "Invalid Trade Date";

	// Check TradeServiceImpl class
	public static final String noTradeReferenceNumber = "Trade is not found with Trade Reference Number: ";
	public static final String notValidateTrade = "Trade is not validated: ";
	public static final String noPartiesExist = "No parties exist with: ";
	public static final String noTradeAndInsertData = "No Trade exist, please insert data";

	// check TradeData class
	public static final String tableNameTradeData = "trade_data";
	public static final String partyMessage = "party shouldn't be empty.";
	public static final String tradeIdMessage = "Trade id shouldn't be empty.";
	public static final String partyInstitutionMessage = "Party Institution shouldn't be empty.";
	public static final String counterPartyMessage = "Counter Party shouldn't be empty.";
	public static final String CouterPartyInstitutionMessage = "Counter Party Institution shouldn't be empty.";
	public static final String partyFullNameMessage = "Party Full Name shouldn't be empty.";
	public static final String counterPartyFullNameMessage = "Counter Party Full Name shouldn't be empty.";
	public static final String instrumentIdMessage = "Instrument ID can't be empty.";
	public static final String notionalAmountMessage = "Notational Amount can't be empty.";
	public static final String currencyMessage = "Currency shouldn't be empty. Example: INR";
	public static final String sellerMessage = "Seller can't be empty.";
	public static final String buyerMessage = "Buyer Can't be empty.";
	public static final String columnDefinitionVersion = "INT DEFAULT '1'";
	public static final String columnDefinitionStatus = "VARCHAR(40) DEFAULT 'Unconfirmed'";
	public static final String statusExample = "Unconfirmed";
	public static final String versionExample = "1";
	public static final String cancelOnlyUnconfirm = "Sorry! You can cancel only Unconfirm trades.";

	// check PartyValidation class
	public static final String tableNamePartyValidation = "party_info";
	public static final String condPartyFullNameMessage = "Party Full Name should be more than 2 and less than "
			+ "150 characters";

	// check TradeController class
	public static final String getAllTradesMessage = "View list of all available Trades.";
	public static final String getAllPartiesThroughParty = "Get All Trades Using Party";
	public static final String getAllPartiesThroughPartyAndStatus = "Get All Trades Using Party And Status";
	
	public static final String canUpdateOnlyUnconfirmed = "Sorry! Only unconfirmed trades can be updated.";
	public static final String tradeDateNotUpdatable = "Sorry! You cannot update TradeDate.";

	public static final String tradeUpdatedButNoMatchingFound="Trade is updated but no matching found with "
			+ "trade reference number: ";
	public static final String tradeUpdatedAsWellAsMatchFound="Trade is updated as well as Match found with"
			+ "trade reference number: ";
	public static final String BodyError = "Sorry! You will have to give atleast one field.";
	public static final String cancelAnExistingTrade ="Cancel an existing Trade.";
	public static final String updateAnExistingTrade = "Update an existing trade";
	
	//check MatchingServiceImpl class
		public static final String NoMatchingPartiesAndMatchingScores ="Matching Parties and Matching "
									+ "Scores not exist with Party Reference number: ";
	
	// matching  scores percentage
		public static final int tradeDatePercent = 10;
		public static final int effDatePercent = 10;
		public static final int instumentIdPercent = 18;
		public static final int notionalAmountPercent = 10;
		public static final int maturitydatePercent = 8;
		public static final int currencyPercent = 8;
		public static final int sellerPercent = 18;
		public static final int buyerPercent = 18;
		
	//Check Matching Controller class
	public static final String getAllMatchesOfAPartyWithScores = "Get all matches of a party with scores.";
	public static final String searchForAPartyAndTradeReferenceNumber ="Serach for a party & traderefNumber to get all the matching"
			+ " parties with its matching scores.";
	public static final String CanCallMatcherOnlyOnUnconfirmed="Ouuchh! Matcher can be only called for Unconfirmed trades.";

}

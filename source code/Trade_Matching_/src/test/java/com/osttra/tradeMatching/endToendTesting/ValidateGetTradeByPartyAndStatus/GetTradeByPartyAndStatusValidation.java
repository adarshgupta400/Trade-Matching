package com.osttra.tradeMatching.endToendTesting.ValidateGetTradeByPartyAndStatus;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.TradeRepo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
@DisplayName("Fetch Trade By (Party) Only Or (Party and Status) .")
public class GetTradeByPartyAndStatusValidation {

	@Autowired
	TradeRepo tradeRepo;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test
	@DisplayName("Test_Case_ValidParty_ValidStatus")
	void getTradeByValidPartyAndStatus() {
		// validating the test on the response based on number of fields in response body and status code.
		// Here total trades in response body should be equal to trades by that party in database.
		// Also all trades in response body should have party name and status same as searched one.

		String party = "SBIM";
		String status = "Unconfirmed";
		int allReturnedTrades = tradeRepo.findByPartyAndStatus(party, status).size();

		RestAssured.get("/trade/details/{party}/%7Bstatus%7D?status={status}", party, status)
				.then().assertThat().statusCode(200).and().body("size()", is(allReturnedTrades)).and()
				.body("party", everyItem(is(party))).and().body("status", everyItem(is(status)));


	}

	@Test
	@DisplayName("Test_Case_ValidPartyOnly_NoStatus")
	void getTradeByValidPartyOnlyAndNoStatus() {
		// validating the test on the response based on number of fields in response body and status code.
		// Here total trades in response body should be equal to trades by that party in database.
		// Also all trades in response body should have party name same as searched one.

		String party = "SBIM";
		int allReturnedTrades = tradeRepo.findByParty(party).size();

		RestAssured.get("/trade/details/{party}/%7Bstatus%7D", party).then().assertThat()
				.statusCode(200).and().body("size()", is(allReturnedTrades)).and().body("party", everyItem(is(party)));
	}

	@Test
	@DisplayName("Test_Case_InValidParty_InValidStatus")
	void getTradeByInValidPartyAndInValidStatus() {
		//Here returned body should have size only one i.e the error message.
		//Also validating that response body should not have any keys with names "party" and "status".

		String party = "invalid";
		String status = "invalid";

		RestAssured.get("/trade/details/{party}/%7Bstatus%7D?status={status}", party, status)
				.then().assertThat().statusCode(404).and().body("size()", is(1)).and().body("$", not(hasKey("party")))
				.and().body("$", not(hasKey("status")));

	}

	@Test
	@DisplayName("Test_Case_InvalidParty_ValidStatus")
	void getTradeByInValidPartyAndValidStatus() {

		//Here returned body should have size only one i.e the error message.
		//Also validating that response body should not have any keys with names "party" and "status".

		String party = "invalid";
		String status = "Unconfirmed";

		RestAssured.get("/trade/details/{party}/%7Bstatus%7D?status={status}", party, status)
				.then().assertThat().statusCode(404).and().body("size()", is(1)).and().body("$", not(hasKey("party")))
				.and().body("$", not(hasKey("status")));

	}

}

package com.osttra.tradeMatching.endToendTesting.validatePostMethod.ValidateTradeId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidateTradeIdTest {
	


	@Test
	void CreateTradeBySamePartyWithDuplicateTradeIdTest() {
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"SbiM\",\r\n"
				+ "  \"counterParty\": \"HDFCM\",\r\n"
				+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
				+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \"2022-09-09\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \"2022-10-09\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \"SBIM\",\r\n"
				+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
				+ "  \"partyInstitution\": \"SBI\",\r\n"
				+ "  \"seller\": \"HdfcM\",\r\n"
				+ "  \"tradeDate\": \"2022-09-09\",\r\n"
				+ "  \"tradeId\": \"46\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(1)).and().statusCode(500);
	}
}

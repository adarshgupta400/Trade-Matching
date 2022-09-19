package com.osttra.tradeMatching.endToendTesting.validatePostMethod.ValidatePostingBuyerSeller;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;

@SpringBootTest
public class CreateBuyerSellerTest {


	
	@Test
	void CreateTradeWithDuplicateBuyerSeller() {
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
				+ "  \"seller\": \"SbiM\",\r\n"
				+ "  \"tradeDate\": \"2022-09-09\",\r\n"
				+ "  \"tradeId\": \"46\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(1)).and().statusCode(500);
	}

	@Test
	void CreateTradeWithBuyerSellerNotFromPartyOrCounterParty() {
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"SBIG\",\r\n"
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
				+ "  \"seller\": \"HDFCG\",\r\n"
				+ "  \"tradeDate\": \"2022-09-09\",\r\n"
				+ "  \"tradeId\": \"46\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(1)).and().statusCode(500);
	}
	
	@Test
	void CreateTradeWithNullBuyerSeller() {
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"\",\r\n"
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
				+ "  \"seller\": \"\",\r\n"
				+ "  \"tradeDate\": \"2022-09-09\",\r\n"
				+ "  \"tradeId\": \"46\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(1)).and().statusCode(500);
		
	}
	
	@Test
	void CreateTradeWithOneValidAmongBuyerSeller() {
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"HDFCM\",\r\n"
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
				+ "  \"seller\": \"\",\r\n"
				+ "  \"tradeDate\": \"2022-09-09\",\r\n"
				+ "  \"tradeId\": \"46\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(1)).and().statusCode(500);
		
	}
}

package com.osttra.tradeMatching.endToendTesting.validatePostMethod.TradeDatesValidation;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
@SpringBootTest @DisplayName("Trade Date Validations")
public class TradeDatesValidationTest {
	LocalDate datenow = LocalDate.now();
	LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
	LocalDate endDate = LocalDate.now().plusYears(1);


	@Test @DisplayName("Test_Case_TD>EC>MD")
	void CreateTradeWithTDGreaterThanEDGreaterThanMD() {
		
		
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"HDFCM\",\r\n"
				+ "  \"counterParty\": \"HDFCM\",\r\n"
				+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
				+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
				+ "  \"currency\": \"USD\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+datenow+"\",\r\n"
				+ "  \"notionalAmount\": 1000,\r\n"
				+ "  \"party\": \"SBIM\",\r\n"
				+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
				+ "  \"partyInstitution\": \"SBI\",\r\n"
				+ "  \"seller\": \"SBIM\",\r\n"
				+ "  \"tradeDate\": \""+endDate+"\",\r\n"
				+ "  \"tradeId\": \"7770000\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()", is(greaterThanOrEqualTo(1))).and().statusCode(400).extract().response();
		
	}
	
	//Validating MaturityDate > TradeDate > EffectiveDate 
		@Test @DisplayName("Test_Case_MD>TD>ED")
		void CreateTradeWithMDGreaterThanTDGreaterThanED() {
			RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"HDFCM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"USD\",\r\n"
					+ "  \"effectiveDate\": \""+datenow+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+endDate+"\",\r\n"
					+ "  \"notionalAmount\": 1000,\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"SBIM\",\r\n"
					+ "  \"tradeDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"tradeId\": \"7770000\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
			then().
			assertThat().
			statusCode(400).and().body("size()", is(greaterThanOrEqualTo(1)));
			
		}
		
		@Test @DisplayName("Test_Case_ED>MD>TD")
		void CreateTradeWithEDGreaterThanMDGreaterThanTD() {
			RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"HDFCM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"USD\",\r\n"
					+ "  \"effectiveDate\": \""+endDate+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"notionalAmount\": 1000,\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"SBIM\",\r\n"
					+ "  \"tradeDate\": \""+datenow+"\",\r\n"
					+ "  \"tradeId\": \"7770000\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
			then().
			assertThat().
			body("size()", is(greaterThanOrEqualTo(1))).and().statusCode(400);
			
		}
		
		@Test @DisplayName("Test_Case_ED>TD>MD")
		void CreateTradeWithEDGreaterThanTDGreaterThanMD() {
			RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"HDFCM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"USD\",\r\n"
					+ "  \"effectiveDate\": \""+endDate+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+datenow+"\",\r\n"
					+ "  \"notionalAmount\": 1000,\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"SBIM\",\r\n"
					+ "  \"tradeDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"tradeId\": \"7770000\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
			then().
			assertThat().
			body("size()", is(greaterThanOrEqualTo(1))).and().statusCode(400);
			
		}
		
		@Test @DisplayName("Test_Case_TD>MD>ED")
		void CreateTradeWithTDGreaterThanMDGreaterThanED() {
			RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"HDFCM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"USD\",\r\n"
					+ "  \"effectiveDate\": \""+datenow+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"notionalAmount\": 1000,\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"SBIM\",\r\n"
					+ "  \"tradeDate\": \""+endDate+"\",\r\n"
					+ "  \"tradeId\": \"7770000\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
			then().
			assertThat().
			body("size()", is(greaterThanOrEqualTo(1))).and().statusCode(400);
			
		}
		
		@Test @DisplayName("Test_Case_TD<date.today")
		void CreateTradeWithTDLessThanTodaysDate() {
			RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"HDFCM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"USD\",\r\n"
					+ "  \"effectiveDate\": \""+datenow+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"notionalAmount\": 1000,\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"SBIM\",\r\n"
					+ "  \"tradeDate\": \""+datenow.minusMonths(2)+"\",\r\n"
					+ "  \"tradeId\": \"7770000\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
			then().
			assertThat().
			body("size()", is(greaterThanOrEqualTo(1))).and().statusCode(400);
			
		}
}

package com.osttra.tradeMatching.endToendTesting.validatePostMethod.ValidateNotionalAmount;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest @DisplayName("Notional Amount Validation")
public class NotionalAmountValidationTest {

	LocalDate datenow = LocalDate.now();
	LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
	LocalDate endDate = LocalDate.now().plusYears(1);
	int randomId = ((int)(Math.random() * 1200) + 1);
	
	@Test @DisplayName("Test_Case Negative notional amount")
	void CreateTradeWithNegativeNotionalAmountTest() {
		 RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"SbiM\",\r\n"
				+ "  \"counterParty\": \"HDFCM\",\r\n"
				+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
				+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": -100,\r\n"
				+ "  \"party\": \"SBIM\",\r\n"
				+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
				+ "  \"partyInstitution\": \"SBI\",\r\n"
				+ "  \"seller\": \"HdfcM\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		statusCode(400).and().body("size()", is(greaterThanOrEqualTo(1)));
		
	}
	
	@Test @DisplayName("Test_Case Zero Notional Amount")
	void CreateTradeWithZeroNotionalAmountTest() {
		int zeroNotionalAmount = 0;
		 RestAssured.given().header("content-type","application/json").and().body("{\r\n"
					+ "  \"buyer\": \"SbiM\",\r\n"
					+ "  \"counterParty\": \"HDFCM\",\r\n"
					+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
					+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
					+ "  \"currency\": \"INR\",\r\n"
					+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
					+ "  \"instrumentId\": \"Bonds\",\r\n"
					+ "  \"maturityDate\": \""+endDate+"\",\r\n"
					+ "  \"notionalAmount\": "+zeroNotionalAmount+",\r\n"
					+ "  \"party\": \"SBIM\",\r\n"
					+ "  \"partyFullname\": \"SBI MUMBAI\",\r\n"
					+ "  \"partyInstitution\": \"SBI\",\r\n"
					+ "  \"seller\": \"HdfcM\",\r\n"
					+ "  \"tradeDate\": \""+datenow+"\",\r\n"
					+ "  \"tradeId\": \""+randomId+"\"\r\n"
					+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		statusCode(400).and().body("size()", is(greaterThanOrEqualTo(1)));
		
	}
}
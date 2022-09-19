package com.osttra.tradeMatching.endToendTesting.ValidateGetTradeByTRN;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
@DisplayName("Fetch Trade On TRN(Trade Reference Number)")
public class GetTradeByTRNValidation {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test @DisplayName("Test_Case_ValidTRN")
	void getTradeByValidTRNTest() {
		//validating the test on the response based on number of fields in response body
		//and status code.
		//Here total fields in the returned body should be 22.
		//TRN should be same in response body as searched one.
		String tradeReferenceNo = "SBIM60";

		RestAssured.
		get("/trade/{tradeRefNum}",tradeReferenceNo).
		then().
		assertThat().
		statusCode(200).and()
		.body("size()",is(22)).and().body("tradeRefNum",is(tradeReferenceNo));
	
	}
	
	@Test @DisplayName("Test_Case_InValidTRN")
	void getTradeByInValidTRNTest() {
		//validating the test on the response based on number of fields in response body , status code and "tradeRefNum" key name.
		//Here body contains only the returned error message i.e size is 1.
		//Also validating that response body should not have any key with names "tradeRefNum".
		String invalidTRN = "SBIM1000xyz";
		
		RestAssured.
		get("/trade/{invalidTRN}",invalidTRN).
		then().
		assertThat().
		statusCode(404).and()
		.body("size()",is(1)).and().body("$", not(hasKey("tradeRefNum")));
		
	}
}

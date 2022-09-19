package com.osttra.tradeMatching.endToendTesting.ValidateFetchAllTrades;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osttra.tradeMatching.TradeMatchingApplication;
import com.osttra.tradeMatching.repo.TradeRepo;

import java.util.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
@DisplayName("Fetch All Trades")
public class fetchAllTradesTest {
	
	@Autowired
	private TradeRepo tradeRepo;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test @DisplayName("Test_Case_fetchAll")
	void fetchAllValidTrades() {
		//Record count
		int count = (int)tradeRepo.count();
		
		//validating the test on the response based on response body size and status code.
		//Here returned trades in response body should be equal to number of trades in database.
		RestAssured.
		get("/trade/details").
		then().
		assertThat().
		statusCode(200).and().body("size()",is(count));
		
		}
}

package com.osttra.tradeMatching.endToendTesting.validatePostMethod;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
public class ValidateCreateValidTrade {

	//Create A Valid Trade having No MAtching Trade in database(i.e matchingScore 0.)
//	@Test
	void CreateValidTradeWithNoMatchingCouterPartyTradeTest() {
		
		String trade = "{\r\n"
				+ "  \"buyer\": \"SBIM\",\r\n"
				+ "  \"counterParty\": \"SBIM\",\r\n"
				+ "  \"counterPartyFullname\": \"SBI MUMBAI\",\r\n"
				+ "  \"counterPartyInstitution\": \"SBI\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \"2022-10-12\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \"2022-12-12\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \"UBSL\",\r\n"
				+ "  \"partyFullname\": \"UBS LONDON\",\r\n"
				+ "  \"partyInstitution\": \"UBS\",\r\n"
				+ "  \"seller\": \"UBSL\",\r\n"
				+ "  \"tradeDate\": \"2022-09-12\",\r\n"
				+ "  \"tradeId\": \"00111\"\r\n"
				+ "}";

		Response res = RestAssured.given().header("content-type","application/json").and().body(trade).post("http://localhost:8080/trade/inserttrade");//.then().statusCode(201).extract().response();
		int sizeOfList = res.body().path("size()");
		System.out.println(res.asPrettyString());
		System.out.println(sizeOfList);
		System.out.println(res.statusCode());
		System.out.println(res.getStatusLine());
		assertEquals(res.statusCode(),201);
		assertEquals((sizeOfList/15),1);
		
	}
	
}

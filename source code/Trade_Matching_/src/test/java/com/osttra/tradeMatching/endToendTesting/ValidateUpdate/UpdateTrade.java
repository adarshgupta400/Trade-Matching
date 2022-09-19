package com.osttra.tradeMatching.endToendTesting.ValidateUpdate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osttra.tradeMatching.repo.TradeRepo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
@DisplayName("Update Trade")
public class UpdateTrade {
	@Autowired
	TradeRepo tradeRepo;

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test
	@DisplayName("Valid Fields & Valid TRN")
	void updateValidField() {
		String requestBody = "{\r\n"
                + "  \"buyer\": \"SBIMum\",\r\n"
                + "  \"counterParty\": \"SBIMum\",\r\n"
                + "  \"counterPartyFullname\": \"SBI MUMBAI\",\r\n"
                + "  \"counterPartyInstitution\": \"SBI\",\r\n"
                + "  \"currency\": \"USD\",\r\n"
                + "  \"effectiveDate\": \"2022-10-12\",\r\n"
                + "  \"instrumentId\": \"Securities\",\r\n"
                + "  \"maturityDate\": \"2022-12-12\",\r\n"
                + "  \"notionalAmount\": 80000,\r\n"
                + "  \"party\": \"HdfcDel\",\r\n"
                + "  \"partyFullname\": \"Hdfc Delhi\",\r\n"
                + "  \"partyInstitution\": \"Hdfc\",\r\n"
                + "  \"seller\": \"HdfcDel\",\r\n"
                + "  \"tradeDate\": \"2022-09-15\",\r\n"
                + "  \"tradeId\": \"14\"\r\n"
                + "}";

	   String trn = "HdfcDel14";
		Response res = RestAssured.given().header("Content-Type", "application/json").and()
				.body(requestBody)
				.when()
				.patch("http://localhost:8080/trade/updatetrade/{trn}",trn)
				.then()
				.assertThat()
				.statusCode(201)
				.log().all().extract().response();
		
		System.out.println(res.body().asPrettyString());
		System.out.println(res.statusCode());
	   Assertions.assertEquals(201, res.statusCode());


	}
	
	@Test
	@DisplayName("Invalid_BuyerSeller")
	void validTRN() {
		String requestBody = "{\r\n"
                + "  \"buyer\": \"SBIM\",\r\n"
                + "  \"counterParty\": \"SBIMum\",\r\n"
                + "  \"counterPartyFullname\": \"SBI MUMBAI\",\r\n"
                + "  \"counterPartyInstitution\": \"SBI\",\r\n"
                + "  \"currency\": \"USD\",\r\n"
                + "  \"effectiveDate\": \"2022-10-12\",\r\n"
                + "  \"instrumentId\": \"Securities\",\r\n"
                + "  \"maturityDate\": \"2022-12-12\",\r\n"
                + "  \"notionalAmount\": 0,\r\n"
                + "  \"party\": \"HdfcDel\",\r\n"
                + "  \"partyFullname\": \"Hdfc Delhi\",\r\n"
                + "  \"partyInstitution\": \"Hdfc\",\r\n"
                + "  \"seller\": \"HdfcDel\",\r\n"
                + "  \"tradeDate\": \"2022-09-14\",\r\n"
                + "  \"tradeId\": \"14\"\r\n"
                + "}";
		 String trn = "HdfcDel14";
			Response res = RestAssured.given().header("Content-Type", "application/json").and()
					.body(requestBody)
					.when()
					.patch("http://localhost:8080/trade/updatetrade/{trn}",trn)
					.then()
					.assertThat()
					.statusCode(400)
					.log().all().extract().response();
			
			System.out.println(res.body().asPrettyString());
			System.out.println(res.statusCode());
			 Assertions.assertEquals(400, res.statusCode());

		
		
	}
	@Test
	@DisplayName("Invalid_Date_Invalid_TRN")
	void invalidDate() {
		String requestBody = "{\r\n"
                + "  \"buyer\": \"SBIMum\",\r\n"
                + "  \"counterParty\": \"SBIMum\",\r\n"
                + "  \"counterPartyFullname\": \"SBI MUMBAI\",\r\n"
                + "  \"counterPartyInstitution\": \"SBI\",\r\n"
                + "  \"currency\": \"USD\",\r\n"
                + "  \"effectiveDate\": \"2022-10-12\",\r\n"
                + "  \"instrumentId\": \"Securities\",\r\n"
                + "  \"maturityDate\": \"2022-12-12\",\r\n"
                + "  \"notionalAmount\": 0,\r\n"
                + "  \"party\": \"HdfcDel\",\r\n"
                + "  \"partyFullname\": \"Hdfc Delhi\",\r\n"
                + "  \"partyInstitution\": \"Hdfc\",\r\n"
                + "  \"seller\": \"HdfcDel\",\r\n"
                + "  \"tradeDate\": \"2022-09-12\",\r\n"
                + "  \"tradeId\": \"14\"\r\n"
                + "}";
		 String trn = "HdfcDel14";
			Response res = RestAssured.given().header("Content-Type", "application/json").and()
					.body(requestBody)
					.when()
					.patch("http://localhost:8080/trade/updatetrade/{trn}",trn)
					.then()
					.assertThat()
					.statusCode(404)
					.log().all().extract().response();
			
			System.out.println(res.body().asPrettyString());
			System.out.println(res.statusCode());
			 Assertions.assertEquals(404, res.statusCode());
}
}

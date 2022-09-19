package com.osttra.tradeMatching.endToendTesting.validateCancel;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.osttra.tradeMatching.repo.TradeRepo;

import io.restassured.RestAssured;
import io.restassured.response.Response;


@SpringBootTest

@DisplayName("Cancel_Trade")
public class CancelTrade {
	@Autowired
	TradeRepo tradeRepo;

	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/";

	}
	@Test
	@DisplayName("Cancle_Valid_Trade")
	void cancleValidTrade() {
		
		String trn = "SbiMum5";
		Response res = RestAssured.patch("/trade/canceltrade/{trn}",trn)
		.then()
		.assertThat()
		.log()
		.all()
		.statusCode(201)
		.and()
		.body("tradeRefNum", is(trn))
		.extract()
		.response();
		
		System.out.println(res.statusCode());
	}
	
	@Test
	@DisplayName("Invalid_TRN")
	void cancelInvalidTrade() {
		String trn = "HdfcDel";
		Response res = RestAssured.patch("/trade/canceltrade/{trn}",trn)
				.then()
				.assertThat()
				.statusCode(404)
				.log()
				.all()
				.extract()
				.response();
		System.out.println(res.statusCode());
	}
	


}

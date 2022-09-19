package com.osttra.tradeMatching.Junit.BuilderTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;



import static org.mockito.Mockito.when;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;



import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;


import com.osttra.tradeMatching.utils.TradeBuilder;
import com.osttra.tradeMatching.utils.TradeBuilderImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TradeBuilderImplTest {
	
	
	TradeBuilder tradeBuilder;
	
	TradeDataInput insertedTrade;
	TradeData actualTrade1;
	TradeData expectedTrade;
	@BeforeEach
	void init() {
		insertedTrade=new TradeDataInput("SBIM","01","SBI","HDFCM","HDFC","SBI MUMBAI","HDFC MUMBAI",LocalDate.now(),LocalDate.now(),"Bonds",1892.0,LocalDate.now(),"INR","SBIM","HDFCM");
		
		 actualTrade1 = new TradeData(null, "SBIM", "01", "SBIM01", "SBI", "HDFCM", "HDFC", "SBI MUMBAI", "SBI MUMBAI",
		            LocalDate.now(), LocalDate.now(), "Bonds", 1080, LocalDate.now(), "INR", "SBIM", "HDFCM",
		            new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
		            new Date(System.currentTimeMillis()), 1, "Unconfirmed");
		 tradeBuilder = new TradeBuilderImpl();
	}
	
	@Test
	@DisplayName("Cancel trades with positive test case")
	public void testConvertTradeDataFormatWithPositive() {
		System.out.println(tradeBuilder.toString());
		expectedTrade=this.tradeBuilder.convertTradeDataFormat(insertedTrade);
		System.out.println(expectedTrade);
		assertTrue(expectedTrade!=null && actualTrade1!=null);
	}
	
	@Test
	@DisplayName("Cancel trades with negative test case")
	public void testConvertTradeDataFormatWithNegative() {
		System.out.println(tradeBuilder.toString());
		expectedTrade=this.tradeBuilder.convertTradeDataFormat(insertedTrade);
		System.out.println(expectedTrade);
		assertFalse(expectedTrade==null && actualTrade1==null);
	}
	
	@Test
	@DisplayName("Cancel trades with exception test case")
	public void testConvertTradeDataFormatWithException() {
		System.out.println(tradeBuilder.toString());
		 assertThrows(NullPointerException.class, () -> {
			 expectedTrade=this.tradeBuilder.convertTradeDataFormat(null); 
	        });

	}
	
	void clear() {
		insertedTrade=null;
		actualTrade1=null;
		tradeBuilder=null;
	}
	
}

package com.osttra.tradeMatching.Junit.BuilderTest;

import java.time.LocalDate;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.utils.UpdateTradeBuilder;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TradeBuilderUpdateTest {

	UpdateTradeBuilder updateTradeBuilder;
	TradeDataInput insertedData = new TradeDataInput(null, null, null, null, null, null, null, LocalDate.now(),
			LocalDate.now(), "Stocks", 1000, LocalDate.now(), null, null, null);

	TradeData actualTrade1 = new TradeData(null, "SBIM", "01", "SBIM01", "SBI", "HDFCM", "HDFC", "SBI MUMBAI",
			"SBI MUMBAI", LocalDate.now(), LocalDate.now(), "Bonds", 1080, LocalDate.now(), "INR", "SBIM", "HDFCM",
			new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
			new Date(System.currentTimeMillis()), 1, "Unconfirmed");
	TradeData expectedTrade;
	
	@BeforeEach
	void init() {
		insertedData= new TradeDataInput(null, null, null, null, null, null, null, LocalDate.now(),
				LocalDate.now(), "Stocks", 1000, LocalDate.now(), null, null, null);
		actualTrade1= new TradeData(null, "SBIM", "01", "SBIM01", "SBI", "HDFCM", "HDFC", "SBI MUMBAI",
				"SBI MUMBAI", LocalDate.now(), LocalDate.now(), "Bonds", 1080, LocalDate.now(), "INR", "SBIM", "HDFCM",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 1, "Unconfirmed");
		
	}

	@Test
	@DisplayName("Trade Construct with positive test case")
	public void testTradeConstructWithPositive() {
		updateTradeBuilder=new UpdateTradeBuilder();
		expectedTrade=this.updateTradeBuilder.tradeConstruct(actualTrade1 ,insertedData);
		assertTrue(expectedTrade!=null && expectedTrade.getInstrumentId().equals(insertedData.getInstrumentId()));
	}
	
	@Test
	@DisplayName("Trade Construct with negative test case")
	public void testTradeConstructWithNegative() {
		updateTradeBuilder=new UpdateTradeBuilder();
		expectedTrade=this.updateTradeBuilder.tradeConstruct(actualTrade1 ,insertedData);
		assertTrue(expectedTrade!=null && expectedTrade.getInstrumentId().equals(insertedData.getInstrumentId()));
	}
	
	@Test
	@DisplayName("Trade Construct with exception test case")
	public void testTradeConstructWithException() {
		updateTradeBuilder=new UpdateTradeBuilder();
		assertThrows(NullPointerException.class, () -> {
			expectedTrade=this.updateTradeBuilder.tradeConstruct(actualTrade1 ,null);
		});
		
	}
	
	@AfterEach
	public void clear() {
		updateTradeBuilder=null;
		insertedData=null;
		actualTrade1=null;
		expectedTrade=null;
	}
}

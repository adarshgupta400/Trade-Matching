package com.osttra.tradeMatching.Junit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.osttra.tradeMatching.models.MatchedTrade;
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;
import com.osttra.tradeMatching.service.MatchingServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

class MatchingServiceImplTest {

	@Mock
	TradeRepo tRepo;

	@Mock
	ValidationRepo vRepo;

	@Mock
	MatchRepository mRepo;

	@InjectMocks
	MatchingServiceImpl matchService;

	MatchedTrade actual1;
	MatchedTrade actual2;

	List<MatchedTrade> expectedTrade;
	List<MatchedTrade> actualTrade;

	@BeforeEach()
	public void setup() {
		actual1 = new MatchedTrade(1L, "Hdfc007", "SbiGurgaon003", 88, "Unconfirmed");
		actual2 = new MatchedTrade(1L, "Sbi089", "Hdfc100", 100, "Unconfirmed");
		expectedTrade = new ArrayList<>();
		actualTrade = new ArrayList<>();
		this.actualTrade = new ArrayList<>(Arrays.asList(actual2));

	}

	@Test
	@DisplayName("Positive Test for get matching parties and matching scores")
	public void testGetMatchingPartiesAndMatchingScoresWithPositive() throws TradeNotFoundException {
		expectedTrade.add(actual1);
		when(this.mRepo.findAllByA_tradeRefNum("Hdfc007")).thenReturn(expectedTrade);
		actualTrade = this.matchService.getMatchingPartiesAndMatchingScores("Hdfc007");
		assertTrue(expectedTrade != null && actualTrade != null && expectedTrade.size() == 1);
		MatchedTrade actualMTrade = actualTrade.get(0);
		MatchedTrade expectedMTrade = expectedTrade.get(0);
		System.out.println("Actual trade ref number : " + actualMTrade.getA_tradeRefNum());
		System.out.println("Expected trade ref number : " + expectedMTrade.getA_tradeRefNum());
		assertTrue(actualMTrade.getA_tradeRefNum().equals(expectedMTrade.getA_tradeRefNum()));
	}

	@Test
	@DisplayName("Negative Test for get matching parties and matching scores")
	public void testGetMatchingPartiesAndMatchingScoresWithNegative() throws TradeNotFoundException {
		expectedTrade.add(actual1);
		when(this.mRepo.findAllByA_tradeRefNum("Hdfc007")).thenReturn(expectedTrade);

		actualTrade = this.matchService.getMatchingPartiesAndMatchingScores("Hdfc007");
		assertFalse(expectedTrade != null && actualTrade != null && expectedTrade.size() == 3);
	}

	@Test
	@DisplayName("Null Test for get matching parties and matching scores")
	public void testGetMatchingPartiesAndMatchingScoresWithNullValue()  {
		expectedTrade.add(actual1);
		when(this.mRepo.findAllByA_tradeRefNum("Hdfc007")).thenReturn(expectedTrade);
		assertThrows(TradeNotFoundException.class, () -> {
			actualTrade = this.matchService.getMatchingPartiesAndMatchingScores(null);
		});

	}
	
	@Test
	@DisplayName("Positive Test for delete all Cancelled Trades")
	public void testDeleteAllCancelledTradesWithPositive() {
	
		expectedTrade.add(actual2);
		when(this.mRepo.findAllByA_tradeRefNum("Sbi089")).thenReturn(expectedTrade);
		when(this.mRepo.findAllByB_tradeRefNum("Hdfc100")).thenReturn(expectedTrade);
		matchService.deleteAllCancelledTrades("Sbi089");
		matchService.deleteAllCancelledTrades("Hdfc100");
		verify(mRepo,times(2)).deleteAll(expectedTrade);	
	}
	
	@Test
	@DisplayName("Negative Test for delete all Cancelled Trades")
	public void testDeleteAllCancelledTradesWithNegative() {
	
		expectedTrade.add(actual2);
		when(this.mRepo.findAllByA_tradeRefNum("Sbi089")).thenReturn(expectedTrade);
		when(this.mRepo.findAllByB_tradeRefNum("Hdfc100")).thenReturn(expectedTrade);
		matchService.deleteAllCancelledTrades("Sbi089");
		matchService.deleteAllCancelledTrades("Hdfc100");
		verify(mRepo,times(2)).deleteAll(expectedTrade);	
	assertFalse(expectedTrade == null && expectedTrade.size() == 3);
	}
	
	@Test
	@DisplayName("Null Value Test for delete all Cancelled Trades")
	public void testDeleteAllCancelledTradesWithNullValue() {
	
		expectedTrade.add(actual2);
		when(this.mRepo.findAllByA_tradeRefNum("Sbi089")).thenReturn(expectedTrade);
		when(this.mRepo.findAllByB_tradeRefNum("Hdfc100")).thenReturn(expectedTrade);
		assertThrows(NullPointerException.class, () -> {
		
			matchService.deleteAllCancelledTrades(null);
			matchService.deleteAllCancelledTrades(null);
        });      
	}
	
	
	
	@AfterEach
	void clear() {
		actual1=null;
		actual2=null;
		actualTrade=null;
		expectedTrade=null;
	}

}

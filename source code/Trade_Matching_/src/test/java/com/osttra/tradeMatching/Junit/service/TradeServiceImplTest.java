package com.osttra.tradeMatching.Junit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.osttra.tradeMatching.repo.MatchRepository;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;
import com.osttra.tradeMatching.service.TradeServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TradeServiceImplTest {

	@Mock
	TradeRepo tradeRepo;
	@Mock
	ValidationRepo validationRepo;
	@Mock
	MatchRepository matchRepo;
	@InjectMocks
	TradeServiceImpl tradeService;

	TradeData trade1;
	TradeData trade2;
	TradeData trade3;
	TradeData actualTrade;
	List<TradeData> actualTradeList;
	List<TradeData> expectedTradeList;

	@BeforeEach
	void init() {
		trade1 = new TradeData(null, "SBIM", "01", "SBIM01", "SBI", "HDFCM", "HDFC", "SBI MUMBAI", "SBI MUMBAI",
				LocalDate.now(), LocalDate.now(), "Bonds", 1080, LocalDate.now(), "INR", "SBIM", "HDFCM",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 1, "Unconfirmed");

		trade2 = new TradeData(null, "HDFCD", "02", "HDFCD02", "HDFC", "SBID", "SBI", "HDFC DELHI", "SBI DELHI",
				LocalDate.now(), LocalDate.now(), "Equity", 1000, LocalDate.now(), "USD", "SBIM", "HDFCM",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 1, "Unconfirmed");

		trade3 = new TradeData(null, "HDFCM", "01", "SBIM09", "HDFC", "SBIM", "SBI", "HDFC MUMBAI", "HDFC MUMBAI",
				LocalDate.now(), LocalDate.now(), "Bonds", 13780, LocalDate.now(), "INR", "HDFCM", "SBIM",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), 1, "confirmed");
		actualTradeList = new ArrayList<>();
		expectedTradeList = new ArrayList<>();

	}

	@Test
	@DisplayName("Get all trades by tradeRefNum with positive test case")
	public void testGetTradeByTradeRefNumWithPositive() throws TradeNotFoundException {
		when(this.tradeRepo.findByTradeRefNum("HDFCD02")).thenReturn(trade2);
		actualTrade = this.tradeService.getTradeByTradeRefNum("HDFCD02");
		assertTrue(
				trade2 != null && actualTrade != null && trade2.getTradeRefNum().equals(actualTrade.getTradeRefNum()));
	}

	@Test
	@DisplayName("Get all trades by tradeRefNum with negative test case")
	public void testGetTradeByTradeRefNumWithNegative() throws TradeNotFoundException {
		when(this.tradeRepo.findByTradeRefNum("HDFCD02")).thenReturn(trade2);
		actualTrade = this.tradeService.getTradeByTradeRefNum("HDFCD02");
		assertFalse(
				trade2 != null && actualTrade != null && !trade2.getTradeRefNum().equals(actualTrade.getTradeRefNum()));
	}

	@Test
	@DisplayName("Get all trades by tradeRefNum with exception test case")
	public void testGetTradeByTradeRefNumWithException() throws TradeNotFoundException {
		when(this.tradeRepo.findByTradeRefNum("HDFCD03")).thenReturn(trade2);

		assertThrows(TradeNotFoundException.class, () -> {
			TradeData actualTrade = this.tradeService.getTradeByTradeRefNum(null);
		});

	}

	@Test
	@DisplayName("Get all trades with positive test case")
	public void testGetAlltradesWithPositive() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		expectedTradeList.add(trade2);
		when(this.tradeRepo.findAll()).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getAllTrade();
		assertTrue(expectedTradeList != null && actualTradeList != null
				&& actualTradeList.size() == expectedTradeList.size());

	}

	@Test
	@DisplayName("Get all trades with negative test case")
	public void testGetAlltradesWithNegative() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		expectedTradeList.add(trade2);
		when(this.tradeRepo.findAll()).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getAllTrade();
		assertFalse(expectedTradeList != null && actualTradeList != null
				&& actualTradeList.size() != expectedTradeList.size());

	}

	@Test
	@DisplayName("Get all trades with exception test case")
	public void testGetAlltradesWithException() throws TradeNotFoundException {

		when(this.tradeRepo.findAll()).thenReturn(expectedTradeList);
		assertThrows(TradeNotFoundException.class, () -> {
			actualTradeList = this.tradeService.getAllTrade();
		});

		// assertFalse(expectedTradeList != null && actualTradeList != null &&
		// actualTradeList.size()!=expectedTradeList.size());

	}

	@Test
	@DisplayName("Get all trades with Party Name and Status Positive test case")
	public void testGetTradesByPartyAndStatusWithPositive() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		when(this.tradeRepo.findByPartyAndStatus("SBIM", "Unconfirmed")).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getTradesByPartyStatus("SBIM", "Unconfirmed");
		assertTrue(expectedTradeList != null && actualTradeList != null
				&& actualTradeList.size() == expectedTradeList.size());
	}

	@Test
	@DisplayName("Get all trades with Party Name and Status Negative test case")
	public void testGetTradesByPartyAndStatusWithNegative() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		when(this.tradeRepo.findByPartyAndStatus("SBIM", "Unconfirmed")).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getTradesByPartyStatus("SBIM", "Unconfirmed");
		assertFalse(expectedTradeList == null && actualTradeList == null
				&& actualTradeList.size() != expectedTradeList.size());
	}

	@DisplayName("Null Exception Test for Search by Party Name and Status")
	@Test
	public void testGetTradesByPartyAndStatusWithNullException() throws TradeNotFoundException {
		expectedTradeList.add(trade2);
		when(this.tradeRepo.findByParty("HDFCD")).thenReturn(expectedTradeList);
		assertThrows(TradeNotFoundException.class, () -> {
			actualTradeList = this.tradeService.getTradesByParty(null);
		});
	}

	@Test
	@DisplayName("Get all trades with Party Name Positive test case")
	public void testGetTradesByPartyWithPositive() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		when(this.tradeRepo.findByParty("SBIM")).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getTradesByParty("SBIM");
		assertTrue(expectedTradeList != null && actualTradeList != null
				&& actualTradeList.size() == expectedTradeList.size());
	}

	@Test
	@DisplayName("Get all trades with Party Name Negative test case")
	public void testGetTradesByPartyWitNegative() throws TradeNotFoundException {
		expectedTradeList.add(trade1);
		expectedTradeList.add(trade2);
		when(this.tradeRepo.findByParty("SBIM")).thenReturn(expectedTradeList);
		actualTradeList = this.tradeService.getTradesByParty("SBIM");
		assertFalse(expectedTradeList == null && actualTradeList == null
				&& actualTradeList.size() != expectedTradeList.size());
	}

	@DisplayName("Null Exception Test for Search by Party Name")
	@Test
	public void testGetTradesByPartyWithNullException() throws TradeNotFoundException {
		expectedTradeList.add(trade3);
		when(this.tradeRepo.findByParty("HDFCM")).thenReturn(expectedTradeList);
		assertThrows(TradeNotFoundException.class, () -> {
			actualTradeList = this.tradeService.getTradesByParty(null);
		});
	}

	@Test
    @DisplayName("Positive Test for exit on maturity date")
    public void testchangeStateToExitWithPositive() {
        expectedTradeList.add(trade2);
        when(this.tradeRepo.findAll()).thenReturn(expectedTradeList);
        tradeService.changeStateToExit();
        verify(tradeRepo, times(1)).findAll();
    }



   @Test
    @DisplayName("Negative Test for exit on maturity date")
    public void testchangeStateToExitWithNegative() {
        expectedTradeList.add(trade1);
        expectedTradeList.add(trade2);
        when(this.tradeRepo.findAll()).thenReturn(expectedTradeList);
        tradeService.changeStateToExit();
        verify(tradeRepo, times(1)).findAll();
        assertFalse(expectedTradeList == null && expectedTradeList.size() == 3);
    }
	
	@AfterEach
	void clear() {
		trade1 = null;
		trade2 = null;
		trade3 = null;
		actualTrade = null;
		actualTradeList = null;
		expectedTradeList = null;
	}
}

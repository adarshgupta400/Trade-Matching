package com.osttra.trade_matching.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.osttra.trade_matching.model.TradeData;

public interface TradeDataService {

	ResponseEntity<List<TradeData>> fetchAllTrades( String party);
	TradeData createTrade(TradeData td);
	TradeData updateTrade(String tradeRefNum, TradeData tradeData);
//	Optional<TradeData> fetchTradeByS_no(int id);
	TradeData cancelTrade(String tradeRefNumber);
	TradeData fetchTradeByTradeRefNumber(String tradeRefNumber);
	ResponseEntity<List<TradeData>> findByStatus(String status);	 
//	List<TradeData> findByParty(String party);



}

//List<Students> fetchStudent();
//Students fetchStudentById(int stuId);
//Students createStudent(Students studetail);
//Students updateStudent(Integer stuId, Students studetail);
//void deleteStudent(Integer stuId);


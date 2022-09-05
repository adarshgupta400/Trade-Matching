package com.capstone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.TradeDataModel;
import com.capstone.repo.TradeRepo;





@Service
public class TradeServiceImpl implements TradeService {
	
@Autowired
private TradeRepo tradeRepository; 

@Override
public ResponseEntity<?> insertTrade(TradeDataModel newTrade) {
	
	return ResponseEntity.of(Optional.of(tradeRepository.save(newTrade)));
}
	
}

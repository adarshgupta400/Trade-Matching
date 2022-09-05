package com.capstone.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.models.TradeDataModel;

@Service
public interface TradeService {
	ResponseEntity<?> insertTrade(TradeDataModel td);

}

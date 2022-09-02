package com.osttra.trade_matching.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.osttra.trade_matching.controller.MatchController;
import com.osttra.trade_matching.model.TradeData;
import com.osttra.trade_matching.repository.TradeRepository;

import io.swagger.models.HttpMethod;

@Service
public class ServiceImplement implements TradeDataService {

	@Autowired
	TradeRepository repo;
	
	@Autowired
	MatchInterfaceService matchService;
	
	@Autowired
	MatchController matchController;


	@Override
	public ResponseEntity<List<TradeData>> fetchAllTrades(String party) {
		try {
			List<TradeData> trades = new ArrayList<TradeData>();
			if(party == null) {
				repo.findAll().forEach(trades::add);
			} else {
				repo.findByPartyContaining(party).forEach(trades :: add);
				if (trades.isEmpty()) {
				      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			
		    return new ResponseEntity<>(trades, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//@GetMapping("/tutorials")
	//public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
	//  try {
//	    List<Tutorial> tutorials = new ArrayList<Tutorial>();
//	    if (title == null)
//	      tutorialRepository.findAll().forEach(tutorials::add);
//	    else
//	      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//	    if (tutorials.isEmpty()) {
//	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    }
//	    return new ResponseEntity<>(tutorials, HttpStatus.OK);
	//  } catch (Exception e) {
//	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	//  }
	//}
	@Override
	public ResponseEntity<?> createTrade(TradeData tradeData) {
		// Post
		tradeData.setTradeRefNum(tradeData.getParty(), tradeData.getTradeId());
		repo.save(tradeData);
		//Calling Matcher
		String PartyA_tradeRefNum = tradeData.getTradeRefNum();
		//		matchService.getMatches(PartyA_tradeRefNum);

		ResponseEntity<?> ob = matchController.getMatches(PartyA_tradeRefNum);

		if(ob.getBody() == null) {
			System.out.println("New Trade Created with TradeRefNum "+PartyA_tradeRefNum);
			System.out.println("No Matching Trades Found");
			return new ResponseEntity<>(tradeData, HttpStatus.CREATED);
		}
		else return new ResponseEntity<>( ob, HttpStatus.FOUND);
	}
//	@Override
//	public Optional<TradeData> fetchTradeByS_no(int id) {
//		// TODO Auto-generated method stub
//		return repo.findById(id);
//	}

	@Override
	public TradeData cancelTrade(String tradeRefNumber) {
		// Updating a trade Status
		TradeData td = repo.findById(tradeRefNumber);
		td.setVersion(td.getVersion()+1);
		td.setStatus("Cancel");
		return repo.save(td);
		
	}

	@Override
	public TradeData updateTrade(String tradeRefNum, TradeData tradeData) {
		// update any existing trade.
		TradeData existing = repo.findById(tradeRefNum);
		tradeData.setVersion(existing.getVersion() +1);
		copyNonNullProperties(tradeData, existing);
		//BeanUtils.copyProperties( tradeData, existing);
		return repo.save(tradeData);
	}
	private void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));	
	}
	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
	
	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
//	@ResponseBody
//	public ResponseEntity<?> updateUser(@RequestBody User user) {
//
//	   User existing = userRepository.read(user.getId());
//	   copyNonNullProperties(user, existing);
//	   userRepository.save(existing);
//
//	   // ...
//	}
//
//	public static void copyNonNullProperties(Object src, Object target) {
//	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
//	}
//
//	public static String[] getNullPropertyNames (Object source) {
//	    final BeanWrapper src = new BeanWrapperImpl(source);
//	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//	    Set<String> emptyNames = new HashSet<String>();
//	    for(java.beans.PropertyDescriptor pd : pds) {
//	        Object srcValue = src.getPropertyValue(pd.getName());
//	        if (srcValue == null) emptyNames.add(pd.getName());
//	    }
//	    String[] result = new String[emptyNames.size()];
//	    return emptyNames.toArray(result);
//	}

	@Override
	public TradeData fetchTradeByTradeRefNumber(String tradeRefNumber) {
		return repo.findOne(tradeRefNumber);
	}

	@Override
	public ResponseEntity<List<TradeData>> findByStatus(String status) {
		
		 try {
			 List<TradeData> trades = new ArrayList<TradeData>();
			 if(status != null)
			 	repo.findByStatus(status).forEach(trades :: add);
			 if (trades.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  }
			 return new ResponseEntity<>(trades, HttpStatus.OK);
		 } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

}
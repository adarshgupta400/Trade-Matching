package com.osttra.trade_matching.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.osttra.trade_matching.model.TradeData;
import com.osttra.trade_matching.repository.TradeRepository;

@Service
public class ServiceImplement implements TradeDataService {

	@Autowired
	TradeRepository repo;


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
	public TradeData createTrade(TradeData td) {
		// Post
		return (TradeData)repo.save(td);
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
		TradeData td = repo.findById(tradeRefNum);
		tradeData.setVersion(tradeData.getVersion() +1);
		BeanUtils.copyProperties( tradeData,td);
		return repo.save(tradeData);
	}

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

//	@Override
//	public List<TradeData> findByParty(String party) {
//		return repo.findByParty(party);
//	}


}
//
//@GetMapping("/tutorials")
//public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
//  try {
//    List<Tutorial> tutorials = new ArrayList<Tutorial>();
//    if (title == null)
//      tutorialRepository.findAll().forEach(tutorials::add);
//    else
//      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//    if (tutorials.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    return new ResponseEntity<>(tutorials, HttpStatus.OK);
//  } catch (Exception e) {
//    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//}
//@GetMapping("/tutorials/{id}")
//public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
//  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//  if (tutorialData.isPresent()) {
//    return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
//  } else {
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//  }
//}
//@PostMapping("/tutorials")
//public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
//  try {
//    Tutorial _tutorial = tutorialRepository
//        .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
//    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
//  } catch (Exception e) {
//    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//}
//@PutMapping("/tutorials/{id}")
//public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//  if (tutorialData.isPresent()) {
//    Tutorial _tutorial = tutorialData.get();
//    _tutorial.setTitle(tutorial.getTitle());
//    _tutorial.setDescription(tutorial.getDescription());
//    _tutorial.setPublished(tutorial.isPublished());
//    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//  } else {
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//  }
//}

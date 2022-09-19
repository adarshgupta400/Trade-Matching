package com.osttra.tradeMatching.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.models.TradeDataInput;
import com.osttra.tradeMatching.service.TradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/trade")
@Api(value = "Trade Controller")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	// Inserting a trade into the DB.
	@ApiOperation(value = "Inserting a trade and saving in DB  after Validating.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull Retrived Trade List"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you where trying to reach is forbidden") })

	@PostMapping("/inserttrade")
	public ResponseEntity<?> insertTrade(@RequestBody @Valid TradeDataInput tradeData) throws NoTradeValidation,
		TradeNotFoundException{
		 return tradeService.insertTrade(tradeData);
		//return tradeService.insertTrade(tradeData);
	}

	@GetMapping("/{tradeRefNum}")
	public ResponseEntity<TradeData> getTradeByTradeRefNum(@PathVariable("tradeRefNum") String tradeRefNum)
			throws TradeNotFoundException {

		return ResponseEntity.ok(tradeService.getTradeByTradeRefNum(tradeRefNum));
	}

	@ApiOperation(value = Constants.getAllTradesMessage)
	@GetMapping("/details")
	public List<TradeData> getAllTrade() throws TradeNotFoundException {
		return tradeService.getAllTrade();
	}

	@ApiOperation(value = Constants.getAllPartiesThroughPartyAndStatus)
	@GetMapping("/details/{party}/{status}")
	public List<TradeData> getTradesByPartyStatus(@PathVariable(required = true) String party,
			@RequestParam(required = false) String status) throws TradeNotFoundException {
		if (status != null)
			return tradeService.getTradesByPartyStatus(party, status);

		else
			return tradeService.getTradesByParty(party);

	}

	@ApiOperation(value = Constants.cancelAnExistingTrade)
	@PatchMapping("/canceltrade/{tradeRefNum}")
	public ResponseEntity<?> cancelTrade(@PathVariable String tradeRefNum) 
			throws TradeNotFoundException, NoTradeValidation {
		return tradeService.cancelTrade(tradeRefNum);
	}

	@ApiOperation(value=Constants.updateAnExistingTrade)
	@PatchMapping("/updatetrade/{tradeRefNum}")
	public ResponseEntity<?> updatetrade(@PathVariable String tradeRefNum ,@RequestBody TradeDataInput updatesInTrade)
			throws NoTradeValidation,TradeNotFoundException{
		return tradeService.updateTrade(tradeRefNum , updatesInTrade);
	}

}

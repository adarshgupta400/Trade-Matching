package com.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.models.TradeDataModel;
import com.capstone.service.TradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/")
@Api(value = "Trade Controller")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	// Inserting a trade into the DB.
	@ApiOperation(value = "Inserting a trade and saving in DB  after Validating.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull Retrived Trade List"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you where trying to reach is forbidden") })

	@PostMapping("/trade/")
	public ResponseEntity<?> insertTrade(@RequestBody TradeDataModel td) {
		return tradeService.insertTrade(td);
	}
}

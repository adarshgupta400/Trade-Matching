package com.osttra.trade_matching.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osttra.trade_matching.model.TradeData;
import com.osttra.trade_matching.services.TradeDataService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/osttra")
public class TradeDataController {
	@Autowired
	private TradeDataService service;
	
	@ApiOperation(value = "Get all Trades or Trades a given party.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@GetMapping("/trades")
	public ResponseEntity<List<TradeData>> getAllTrades(@RequestParam(required = false) String party) {
	  return service.fetchAllTrades(party);
	}
	
//	@GetMapping("/trades/{id}")
//	public Optional<TradeData> getTradeByS_no(@PathVariable int id) {
//		return  service.fetchTradeByS_no(id); 
//	}
	
	@ApiOperation(value = "Get all Trades by status")
	@GetMapping("/trades/getTradesByStatus/{status}")
	public ResponseEntity<List<TradeData>> getTradesByStatus(@PathVariable String status) {
		return service.findByStatus(status); 
	}
	
//	@ApiOperation(value = "Get all Trades of a given party")
//	@GetMapping("/trades/getTradesByParty/{party}")
//	public List<TradeData> getTradesByParty(@PathVariable String party) {
//		return service.findByParty(party); 
//	}
	
	@ApiOperation(value = "Get all Trades by a given TradeRefNumber")
	@GetMapping("/trades/{tradeRefNumber}")
	public TradeData getTradeByTradeRefNumber(@PathVariable String tradeRefNumber) {
		return  service.fetchTradeByTradeRefNumber(tradeRefNumber); 
	}
	
	@ApiOperation(value = "Create a new Trade.")
	@PostMapping("/trades")
	public TradeData createTrade (@RequestBody TradeData tradeData) {
		return (TradeData) service.createTrade(tradeData);
	}
	
	@ApiOperation(value = "Cancel an existing trade.")
	@PutMapping("/trades/cancelTrade")
	public TradeData cancelTrade (@RequestBody String tradeRefNumber) {
		return  service.cancelTrade(tradeRefNumber);
 	}
	
	@ApiOperation(value = "Update an existing trade.")
	@PutMapping("/trades/{tradeRefNum}")
	public TradeData updateTradeByTradeRefNum(@PathVariable String tradeRefNum, @RequestBody TradeData tradeData) {
		return (TradeData) service.updateTrade(tradeRefNum, tradeData); 
	}
	
	
}
/*
@Autowired
	StudentService service;
	
	@ApiOperation(value = "View a list of all Students")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/students")
	public List<Students> getStudents() {
		return service.fetchStudent(); 
	}
		
	@GetMapping("/students/{id}")
	public Students getStudentById(@PathVariable int id) {
		return (Students) service.fetchStudentById(id); 
	}
	
	@PostMapping("/students")
	public Students createStudent(@RequestBody Students stu) {
		return (Students) service.createStudent(stu);
	}
	
	@PutMapping("/students/{id}")
	public Students updateStudentById(@PathVariable int id, @RequestBody Students studetail) {
		return (Students) service.updateStudent(id, studetail); 
	}
	
	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
		return "deleted";
	}
*/
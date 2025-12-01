package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TradeEntity;
import com.example.demo.service.TradeService;

@RestController
@RequestMapping("/trades")
public class TradeController {

	TradeService tradeService;
	
	TradeController(TradeService tradeService){
		this.tradeService = tradeService;
	}
	
	@PostMapping
	public ResponseEntity<?> saveNewTrade(@RequestBody TradeEntity req) {
		TradeEntity response = tradeService.saveNewTrade(req);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getTrade(@RequestParam(required=false) String type, @RequestParam(required=false) String userId){
		List<TradeEntity> response = null;
		if(null != type || null != userId) {
			response = tradeService.getTrade(type, userId);
		} else {
			response = tradeService.getAllTrade();
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTradeById(@PathVariable Long id){
		TradeEntity response = tradeService.getTradeById(id);
		if(null != response) {
			return ResponseEntity.ok(response);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		
	}
}

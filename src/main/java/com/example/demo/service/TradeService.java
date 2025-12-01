package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TradeEntity;

public interface TradeService {

	public TradeEntity saveNewTrade(TradeEntity request);
	public List<TradeEntity> getTrade(String type, String userId);
	public List<TradeEntity> getAllTrade();
	public TradeEntity getTradeById(Long id);
}

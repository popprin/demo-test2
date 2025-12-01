package com.example.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TradeEntity;
import com.example.demo.repository.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {
	
	TradeRepository tradeRepository;
	
	TradeServiceImpl(TradeRepository tradeRepository){
		this.tradeRepository = tradeRepository;
	}
	
	@Override
	public TradeEntity saveNewTrade(TradeEntity request) {
		TradeEntity response = new TradeEntity();
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		response = tradeRepository.save(request);
		
		return response;
	}

	@Override
	public List<TradeEntity> getTrade(String type, String userId) {
		List<TradeEntity> response = new ArrayList<>();
		if(null != type && null != userId) {
			response = tradeRepository.findByTypeAndUserId(type, userId);
		} else if (null != type) {
			response = tradeRepository.findByType(type);
		} else if (null != userId) {
			response = tradeRepository.findByUserId(userId);
		}
		return response;
	}

	@Override
	public List<TradeEntity> getAllTrade() {
		List<TradeEntity> response = new ArrayList<>();
		response = tradeRepository.findAll();
		return response;
	}

	@Override
	public TradeEntity getTradeById(Long id) {
		Optional<TradeEntity> opt = tradeRepository.findById(id);
		TradeEntity response = null;
		if(opt.isPresent()) {
			response = opt.get();
		}
		return response;
	}

}

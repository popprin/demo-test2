package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TradeEntity;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

	public List<TradeEntity> findByTypeAndUserId(String type, String userId);
	public List<TradeEntity> findByType(String type);
	public List<TradeEntity> findByUserId(String userId);
}

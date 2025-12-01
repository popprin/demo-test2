package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.TradeEntity;
import com.example.demo.repository.TradeRepository;
import com.example.demo.service.TradeServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class Demo2ApplicationTests {

	@Mock
	private TradeRepository tradeRepository;
	
	@InjectMocks
	private TradeServiceImpl tradeService;
	
	private TradeEntity trade1;
	private TradeEntity trade2;
	
	@BeforeEach
	void setupTrade() {
		MockitoAnnotations.openMocks(this);
		trade1 = new TradeEntity(1L,"buy", "123", "ABX", 30, new BigDecimal(134));
		trade2 = new TradeEntity(1L,"sell", "456", "BIT", 10, new BigDecimal(20000));
	}
	
	@Test
	void testSaveNewTrade() {
		trade1.setId(null);
		TradeEntity save = new TradeEntity(1L, "buy", "123", "ABX", 30, new BigDecimal(134));
		
		Mockito.when(tradeRepository.save(trade1)).thenReturn(save);
		
		TradeEntity result = tradeService.saveNewTrade(trade1);
		
		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("buy", result.getType());
		verify(tradeRepository, times(1)).save(trade1);
		
	}

	@Test
	void testGetTradeByTypeAndUserId() {
		List<TradeEntity> mockList = Arrays.asList(trade1, trade2);
		
		Mockito.when(tradeRepository.findByTypeAndUserId("buy", "123")).thenReturn(mockList);
		List<TradeEntity> result = tradeService.getTrade("buy", "123");
		
		assertNotNull(result);
		assertEquals("buy", result.get(0).getType());
		verify(tradeRepository, times(1)).findByTypeAndUserId("buy", "123");
	}
	
	@Test
	void testGetTradeByTypeAndUserIdNotFound() {
		
		Mockito.when(tradeRepository.findByTypeAndUserId("e", "123")).thenReturn(Arrays.asList());
		List<TradeEntity> result = tradeService.getTrade("e", "123");
		
		assertEquals(0, result.size());
		verify(tradeRepository, times(1)).findByTypeAndUserId("e", "123");
	}
	
	@Test
	void testGetAllTrade() {
		List<TradeEntity> mockList = Arrays.asList(trade1, trade2);
		Mockito.when(tradeRepository.findAll()).thenReturn(mockList);
		List<TradeEntity> result = tradeService.getAllTrade();
		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("buy", result.get(0).getType());
		verify(tradeRepository, times(1)).findAll();
	}
}

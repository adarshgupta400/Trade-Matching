package com.osttra.tradeMatching.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osttra.tradeMatching.models.TradeData;

@Repository
public interface TradeRepo extends JpaRepository<TradeData, Long>{
	TradeData findByTradeRefNum(String tradeRefNum);

	@Query("SELECT t FROM TradeData t WHERE t.tradeRefNum = ?1")
	TradeData findOne(String tradeRefNumber);
    	
    List<TradeData> findByPartyContaining(String party);
	List<TradeData> findByStatus(String status);	 
	List<TradeData> findByParty(String party);
	int countByStatus(String status);
	boolean existsByParty(String party);


	List<TradeData> findByPartyAndStatus(String counterParty, String status);

	long count();

}

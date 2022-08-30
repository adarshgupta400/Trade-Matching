package com.osttra.trade_matching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osttra.trade_matching.model.TradeData;

@Repository
public interface TradeRepository extends JpaRepository<TradeData, Integer>{
	
	 @Query("SELECT t FROM TradeData t WHERE t.tradeRefNum = ?1")
	TradeData findById(String tradeRefNumber);
//    @Query("SELECT t FROM TradeData t WHERE t.party = ?1 and t.tradeRefNum = ?2")
//    TradeData findUserByPArtyAndTradeRefNum(String party, String tradeRefNum);
	
//    @Query("SELECT t FROM TradeData t WHERE t.s_no = ?1")
//	  Optional<TradeData> findById(int id);

    @Query("SELECT t FROM TradeData t WHERE t.tradeRefNum = ?1")
	TradeData findOne(String tradeRefNumber);
    	
    List<TradeData> findByPartyContaining(String party);
	List<TradeData> findByStatus(String status);	 
	List<TradeData> findByParty(String party);
	int countByStatus(String status);
	boolean existsByParty(String party);

}
//Now we can use JpaRepository’s methods: save(), findOne(), findById(), 
//findAll(), count(), delete(), deleteById()… without implementing these methods.

//We also define custom finder methods:
//– findByParty(String s): returns all trade with given part
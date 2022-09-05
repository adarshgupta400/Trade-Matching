package com.capstone.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.models.MatchingModel;

@Repository
public interface MatchRepository extends JpaRepository<MatchingModel, Integer>{

//	ResponseEntity<?> findAllByA_tradeRefNum(String partyA_tradeRefNum);
	
	@Query ("SELECT t FROM MatchingModel t WHERE t.a_tradeRefNum = ?1")
	List<MatchingModel> findAllByA_tradeRefNum(String partyA_tradeRefNum);

//	TradeData findById(String partyA_tradeRefNum);

}

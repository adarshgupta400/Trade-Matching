package com.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.models.TradeDataModel;

@Repository
public interface TradeRepo extends JpaRepository<TradeDataModel, Long> {

}

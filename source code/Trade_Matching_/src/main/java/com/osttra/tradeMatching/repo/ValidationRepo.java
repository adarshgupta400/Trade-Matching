package com.osttra.tradeMatching.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osttra.tradeMatching.models.PartyValidation;

@Repository
public interface ValidationRepo extends JpaRepository<PartyValidation, Long>{

	PartyValidation findByPartyAndPartyInstitutionAndPartyFullName(String party,String partyInstitution,String PartyFullName);
	PartyValidation findByParty(String party);
}

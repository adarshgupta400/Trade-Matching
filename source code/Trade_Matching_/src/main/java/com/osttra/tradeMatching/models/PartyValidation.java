package com.osttra.tradeMatching.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.osttra.tradeMatching.constant.Constants;


@Entity
@Table(name=Constants.tableNamePartyValidation)
public class PartyValidation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length = 20)
	@Size(min=2,max=20)
	private String partyInstitution;
	
	@Column(nullable=false, length = 20,unique=true)
	@Size(min=2,max=20)
	private String party;
	
	@Column(nullable=false, length = 150, unique=true)
	@Size(min=2,max=150, message=Constants.counterPartyFullNameMessage)
	private String partyFullName;

	
	public PartyValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartyValidation(Long id, @Size(min = 2, max = 20) String partyInstitution,
			@Size(min = 2, max = 20) String party, @Size(min = 2, max = 150) String partyFullName) {
		super();
		this.id = id;
		this.partyInstitution = partyInstitution;
		this.party = party;
		this.partyFullName = partyFullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartyInstitution() {
		return partyInstitution;
	}

	public void setPartyInstitution(String partyInstitution) {
		this.partyInstitution = partyInstitution;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPartyFullName() {
		return partyFullName;
	}

	public void setPartyFullName(String partyFullName) {
		this.partyFullName = partyFullName;
	}
	
}

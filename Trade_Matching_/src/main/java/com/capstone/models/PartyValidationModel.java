package com.capstone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class PartyValidationModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, columnDefinition="varchar(20)")
	@Size(min=2,max=20)
	private String partyInstitution;
	
	@Column(nullable=false, columnDefinition="varchar(20)",unique=true)
	@Size(min=2,max=20)
	private String party;
	
	@Column(nullable=false, columnDefinition="varchar(150)", unique=true)
	@Size(min=2,max=150, message="Party Full Name should be more than 2 and less than 150 characters")
	private String partyFullName;

	
	public PartyValidationModel(Long id, @Size(min = 2, max = 20) String partyInstitution,
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

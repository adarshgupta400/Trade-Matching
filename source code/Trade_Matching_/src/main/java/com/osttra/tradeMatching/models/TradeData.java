package com.osttra.tradeMatching.models;


import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.osttra.tradeMatching.constant.Constants;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name=Constants.tableNameTradeData)
public class TradeData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.partyMessage)
	private String party;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.tradeIdMessage)
	private String tradeId;

	@Column(nullable = false, length = 20, unique = true)
	@Size(max = 20)
	@NotBlank
	private String tradeRefNum = party + tradeId;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.partyInstitutionMessage)
	private String partyInstitution;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.counterPartyMessage)
	private String counterParty;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.CouterPartyInstitutionMessage)
	private String counterPartyInstitution;

	@Column(nullable = false, length = 200)
	@Size(max = 200)
	@NotBlank(message = Constants.partyFullNameMessage)
	private String partyFullname;

	@Column(nullable = false, length = 200)
	@Size(max = 20)
	@NotBlank(message = Constants.counterPartyFullNameMessage)
	private String counterPartyFullname;

//	@Temporal(value=TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate tradeDate;

	// @Temporal(value=TemporalType.DATE)
	private LocalDate effectiveDate;

	@Column(nullable = false, length = 40)
	@NotBlank
	@Size(max = 40)
	@NotBlank(message = Constants.instrumentIdMessage)
	private String InstrumentId;

	@Column
	@NotNull(message = Constants.notionalAmountMessage)
	@Positive
	private double notionalAmount;

	// @Temporal(value=TemporalType.DATE)
	private LocalDate maturityDate;

	@Column(nullable = false, length = 3)
	@NotBlank(message = Constants.currencyMessage)
	@Size(max = 3)
	private String currency;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message =Constants.sellerMessage)
	private String seller;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = Constants.buyerMessage)
	private String buyer;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date versionTimestamp;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date confirmationTimestamp;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date creationTimestamp;

	@Column(columnDefinition = Constants.columnDefinitionVersion, insertable = false, nullable = false)
	@ApiModelProperty(example = Constants.versionExample)
	private int version;

	@Column(nullable = false, insertable = false, columnDefinition = Constants.columnDefinitionStatus)
	@ApiModelProperty(example = Constants.statusExample)
	private String status;

	public TradeData() {
		super();

	}

	public TradeData(Long id, @Size(max = 20) @NotBlank String party, @Size(max = 20) @NotBlank String tradeId,
			@Size(max = 20) @NotBlank String tradeRefNum, @Size(max = 20) @NotBlank String partyInstitution,
			@Size(max = 20) @NotBlank String counterParty, @Size(max = 20) @NotBlank String counterPartyInstitution,
			@Size(max = 200) @NotBlank String partyFullname, @Size(max = 20) @NotBlank String counterPartyFullname,
			LocalDate tradeDate, LocalDate effectiveDate, @NotBlank @Size(max = 40) @NotBlank String instrumentId,
			@NotNull @Positive double notionalAmount, LocalDate maturityDate, @NotBlank @Size(max = 3) String currency,
			@Size(max = 20) @NotBlank String seller, @Size(max = 20) @NotBlank String buyer,
			java.util.Date versionTimestamp, java.util.Date confirmationTimestamp, java.util.Date creationTimestamp,
			int version, String status) {
		super();
		this.id = id;
		this.party = party;
		this.tradeId = tradeId;
		this.tradeRefNum = tradeRefNum;
		this.partyInstitution = partyInstitution;
		this.counterParty = counterParty;
		this.counterPartyInstitution = counterPartyInstitution;
		this.partyFullname = partyFullname;
		this.counterPartyFullname = counterPartyFullname;
		this.tradeDate = tradeDate;
		this.effectiveDate = effectiveDate;
		InstrumentId = instrumentId;
		this.notionalAmount = notionalAmount;
		this.maturityDate = maturityDate;
		this.currency = currency;
		this.seller = seller;
		this.buyer = buyer;
		this.versionTimestamp = versionTimestamp;
		this.confirmationTimestamp = confirmationTimestamp;
		this.creationTimestamp = creationTimestamp;
		this.version = version;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeRefNum() {
		return tradeRefNum;
	}

	public void setTradeRefNum(String party, String tradeId) {
		this.tradeRefNum = party + tradeId;
	}

	public String getPartyInstitution() {
		return partyInstitution;
	}

	public void setPartyInstitution(String partyInstitution) {
		this.partyInstitution = partyInstitution;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getCounterPartyInstitution() {
		return counterPartyInstitution;
	}

	public void setCounterPartyInstitution(String counterPartyInstitution) {
		this.counterPartyInstitution = counterPartyInstitution;
	}

	public String getPartyFullname() {
		return partyFullname;
	}

	public void setPartyFullname(String partyFullname) {
		this.partyFullname = partyFullname;
	}

	public String getCounterPartyFullname() {
		return counterPartyFullname;
	}

	public void setCounterPartyFullname(String counterPartyFullname) {
		this.counterPartyFullname = counterPartyFullname;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getInstrumentId() {
		return InstrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		InstrumentId = instrumentId;
	}

	public double getNotionalAmount() {
		return notionalAmount;
	}

	public void setNotionalAmount(double notionalAmount) {
		this.notionalAmount = notionalAmount;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public java.util.Date getVersionTimestamp() {
		return versionTimestamp;
	}

	public void setVersionTimestamp(java.util.Date versionTimestamp) {
		this.versionTimestamp = versionTimestamp;
	}

	public java.util.Date getConfirmationTimestamp() {
		return confirmationTimestamp;
	}

	public void setConfirmationTimestamp(java.util.Date confirmationTimestamp) {
		this.confirmationTimestamp = confirmationTimestamp;
	}

	public java.util.Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(java.util.Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

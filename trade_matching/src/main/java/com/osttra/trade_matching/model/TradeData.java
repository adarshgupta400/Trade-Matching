package com.osttra.trade_matching.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="trade_data")
public class TradeData {
	//POJO Class- Entity Trade data
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_no",unique=true)
	private int s_no;
	
	
	@Column(name = "party", length = 20)
	@Size(max=20)
	@NotNull
	private String party;
	
	@Column(name = "tradeId", length = 20)
	@Size(max=20)
	@NotNull
	private String tradeId;
	

	@Column(name = "tradeRefNum",unique=true, length= 40)
	@Size(max = 40)
	private String tradeRefNum = party + tradeId;
	
	
	@Column(name = "partyFullName", length = 200)
	@Size(max=200)
	private String partyFullName;
	
	@Column(name = "partyInstitution", length = 20)
	@Size(max=20)
	@NotNull
	private String partyInstitution;
	
	@Column(name = "counterParty", length = 20)
	@Size(max=20)
	@NotNull
	private String counterParty;
	
	@Column(name = "counterpartyFullName", length = 200)
	@Size(max=200)
	private String counterpartyFullName;
	
	@Column(name = "counterPartyInstitution", length = 20)
	@Size(max=20)
	@NotNull
	private String counterPartyInstitution;
	
	@Column(name = "tradeDate")
	@Temporal(TemporalType.DATE) 
	private java.util.Date tradeDate;
	
	@Column(name = "effectiveDate")
	@NotNull
	private java.util.Date effetiveDate;
	
	@Column(name = "maturityDate")
	@NotNull
	private java.util.Date maturityDate;
	
	@Column(name = "instrumentId", length = 40)
	@Size(max=40)
	@NotNull
	private String instrumentId;
	
	@Column(name = "notionalAmount")
	@NotNull
	private double notionalAmount; 
	
	@Column(name = "currency", length = 3)
	@Size(max=3)
	@NotNull
	private String currency; 
	
	@Column(name = "seller")
	@Size(max=20)
	@NotNull
	private String seller;
	
	@Column(name = "buyer")
	@Size(max=20)
	@NotNull
	private String buyer;
	
	//Not neccessary properties.
	
	@CreationTimestamp
	@Column(name = "creationTimeStamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private java.util.Date CreationTimeStamp;
	
	@Column(name = "VersionTimeStamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private java.util.Date VersionTimeStamp;
	
	
	@Column(name = "ConfirmationTimeStamp")
	private java.sql.Timestamp ConfirmationTimeStamp;
	
	@ApiModelProperty(example = "1")
	@Column(name = "version", insertable = false, columnDefinition = "INT DEFAULT '1'")
	@NotNull
//	@Value("${some.key:0}")
	private int version;
	
	@ApiModelProperty(example = "Unconfirm")
	@Column(name = "status", length = 50,insertable = false, columnDefinition = "VARCHAR(50) DEFAULT 'Unconfirm'")
//	@Value("${some.key:Unconfirmed}")
	@NotNull
	private String status;
	
	//Constructors ------------------------------------->
	public TradeData() {	
	}

	public TradeData(int s_no, @Size(max = 20) @NotNull String party, @Size(max = 20) @NotNull String tradeId,
			@Size(max = 40) String tradeRefNum, @Size(max = 200) String partyFullName,
			@Size(max = 20) @NotNull String partyInstitution, @Size(max = 20) @NotNull String counterParty,
			@Size(max = 200) String counterpartyFullName, @Size(max = 20) @NotNull String counterPartyInstitution,
			Date tradeDate, Date effetiveDate, Date maturityDate, @Size(max = 40) @NotNull String instrumentId,
			@NotNull double notionalAmount, @Size(max = 3) @NotNull String currency,
			@Size(max = 20) @NotNull String seller, @Size(max = 20) @NotNull String buyer, Date creationTimeStamp,
			Date versionTimeStamp, Timestamp confirmationTimeStamp, @NotNull int version, @NotNull String status) {
		super();
		this.s_no = s_no;
		this.party = party;
		this.tradeId = tradeId;
		this.tradeRefNum = tradeRefNum;
		this.partyFullName = partyFullName;
		this.partyInstitution = partyInstitution;
		this.counterParty = counterParty;
		this.counterpartyFullName = counterpartyFullName;
		this.counterPartyInstitution = counterPartyInstitution;
		this.tradeDate = tradeDate;
		this.effetiveDate = effetiveDate;
		this.maturityDate = maturityDate;
		this.instrumentId = instrumentId;
		this.notionalAmount = notionalAmount;
		this.currency = currency;
		this.seller = seller;
		this.buyer = buyer;
		CreationTimeStamp = creationTimeStamp;
		VersionTimeStamp = versionTimeStamp;
		ConfirmationTimeStamp = confirmationTimeStamp;
		this.version = version;
		this.status = status;
	}
	
	//Getter n Setter
	
	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
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

	public void setTradeRefNum(String tradeRefNum) {
		this.tradeRefNum = tradeRefNum;
	}

	public String getPartyFullName() {
		return partyFullName;
	}

	public void setPartyFullName(String partyFullName) {
		this.partyFullName = partyFullName;
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

	public String getCounterpartyFullName() {
		return counterpartyFullName;
	}

	public void setCounterpartyFullName(String counterpartyFullName) {
		this.counterpartyFullName = counterpartyFullName;
	}

	public String getCounterPartyInstitution() {
		return counterPartyInstitution;
	}

	public void setCounterPartyInstitution(String counterPartyInstitution) {
		this.counterPartyInstitution = counterPartyInstitution;
	}

	public java.util.Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(java.util.Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public java.util.Date getEffetiveDate() {
		return effetiveDate;
	}

	public void setEffetiveDate(java.util.Date effetiveDate) {
		this.effetiveDate = effetiveDate;
	}

	public java.util.Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(java.util.Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public double getNotionalAmount() {
		return notionalAmount;
	}

	public void setNotionalAmount(double notionalAmount) {
		this.notionalAmount = notionalAmount;
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

	public java.util.Date getCreationTimeStamp() {
		return CreationTimeStamp;
	}

	public void setCreationTimeStamp(java.util.Date creationTimeStamp) {
		CreationTimeStamp = creationTimeStamp;
	}

	public java.util.Date getVersionTimeStamp() {
		return VersionTimeStamp;
	}

	public void setVersionTimeStamp(java.util.Date versionTimeStamp) {
		VersionTimeStamp = versionTimeStamp;
	}

	public java.sql.Timestamp getConfirmationTimeStamp() {
		return ConfirmationTimeStamp;
	}

	public void setConfirmationTimeStamp(java.sql.Timestamp confirmationTimeStamp) {
		ConfirmationTimeStamp = confirmationTimeStamp;
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

	@Override
	public String toString() {
		return "TradeData [s_no=" + s_no + ", party=" + party + ", tradeId=" + tradeId + ", tradeRefNum=" + tradeRefNum
				+ ", partyFullName=" + partyFullName + ", partyInstitution=" + partyInstitution + ", counterParty="
				+ counterParty + ", counterpartyFullName=" + counterpartyFullName + ", counterPartyInstitution="
				+ counterPartyInstitution + ", tradeDate=" + tradeDate + ", effetiveDate=" + effetiveDate
				+ ", maturityDate=" + maturityDate + ", instrumentId=" + instrumentId + ", notionalAmount="
				+ notionalAmount + ", currency=" + currency + ", seller=" + seller + ", buyer=" + buyer
				+ ", CreationTimeStamp=" + CreationTimeStamp + ", VersionTimeStamp=" + VersionTimeStamp
				+ ", ConfirmationTimeStamp=" + ConfirmationTimeStamp + ", version=" + version + ", status=" + status
				+ "]";
	}	
	
}

//{
//    "s_no": 3,
//    "party": "SbiGurgaon",
//    "tradeId": "002",
//    "tradeRefNum": "SbiGurgaon002",
//    "partyFullName": "Sbi Gurgaon",
//    "partyInstitution": "Sbi",
//    "counterParty": "HdfcDelhi",
//    "counterpartyFullName": "Hdfc Delhi",
//    "counterPartyInstitution": "Hdfc",
//    "tradeDate": 1661106600000,
//    "effetiveDate": 1661106600000,
//    "maturityDate": 1661106600000,
//    "instrumentId": "stock",
//    "notionalAmount": 788888.0,
//    "currency": "INR",
//    "seller": "HdfcDelhi",
//    "buyer": "SbiGurgaon",
//    "version": 0,
//    "status": "Confirm",
//    "creationTimeStamp": 1661106600000,
//    "versionTimeStamp": 1661106600000,
//    "confirmationTimeStamp": 1661106600000
//}

package com.osttra.trade_matching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="matching_score")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_no",unique=true)
	private  int matchNo;
	
	@ApiModelProperty(example = "HdfcMgroad007")
	@Column(name = "a_tradeRefNum", length = 40)
	@Size(max=40)
	private String a_tradeRefNum;
	
	@ApiModelProperty(example = "SbiGurgaon003")
	@Column(name = "b_tradeRefNum",  length = 40)
	@Size(max=40)
	private  String b_tradeRefNum;
	
	@ApiModelProperty(example = "88")
	@Column(name = "matching_score") //,  columnDefinition = "INT DEFAULT '0'"
	private  int matchingScore;
	
	@ApiModelProperty(example = "Unconfirmed")
	@Column(name = "status_after_match")
	//, insertable = false, columnDefinition = "VARCHAR(50) DEFAULT 'Unconfirm'"
	private  String statusAfterMatch;

	public int getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public String getA_tradeRefNum() {
		return a_tradeRefNum;
	}

	public void setA_tradeRefNum(String a_tradeRefNum) {
		this.a_tradeRefNum = a_tradeRefNum;
	}

	public String getB_tradeRefNum() {
		return b_tradeRefNum;
	}

	public void setB_tradeRefNum(String b_tradeRefNum) {
		this.b_tradeRefNum = b_tradeRefNum;
	}

	public int getMatchingScore() {
		return matchingScore;
	}

	public void setMatchingScore(int matchingScore) {
		this.matchingScore = matchingScore;
	}

	public String getStatusAfterMatch() {
		return statusAfterMatch;
	}

	public void setStatusAfterMatch(String statusAfterMatch) {
		this.statusAfterMatch = statusAfterMatch;
	}

	


}

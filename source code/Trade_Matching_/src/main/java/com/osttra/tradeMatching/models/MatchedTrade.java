package com.osttra.tradeMatching.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="matched_trades_scores")
@Data
public class MatchedTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_no",unique=true)
    private  long matchNo;
    
    @ApiModelProperty(example = "Party-A-tradeRefNumber")
    @Column(name = "a_tradeRefNum", length = 40)
    @Size(max=40)
    private String a_tradeRefNum;  
    
    @ApiModelProperty(example = "Party-B-tradeRefNumber")
    @Column(name = "b_tradeRefNum",  length = 40)
    @Size(max=40)
    private  String b_tradeRefNum;
    
    @ApiModelProperty(example = "88")
    @Column(name = "matching_score") 
    private  int matchingScore;
    
    @ApiModelProperty(example = "Unconfirmed")
    @Column(name = "status_after_match", columnDefinition = "VARCHAR(20) DEFAULT 'Unconfirm'")
    private  String statusAfterMatch;

    public MatchedTrade() {
    	super();
    }

	public MatchedTrade(long matchNo, @Size(max = 40) String a_tradeRefNum, @Size(max = 40) String b_tradeRefNum,
			int matchingScore, String statusAfterMatch) {
		super();
		this.matchNo = matchNo;
		this.a_tradeRefNum = a_tradeRefNum;
		this.b_tradeRefNum = b_tradeRefNum;
		this.matchingScore = matchingScore;
		this.statusAfterMatch = statusAfterMatch;
	}



	@Override
	public String toString() {
		return "MatchedTrade [matchNo=" + matchNo + ", a_tradeRefNum=" + a_tradeRefNum + ", b_tradeRefNum="
				+ b_tradeRefNum + ", matchingScore=" + matchingScore + ", statusAfterMatch=" + statusAfterMatch + "]";
	}

	public long getMatchNo() {
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
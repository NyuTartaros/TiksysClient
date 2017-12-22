package com.nyu.tiksysclient.entity;

import java.sql.Timestamp;

public class RemainTicketRecord {
	
	private Timestamp time;
	private Integer remainTicketNum;
	
	public RemainTicketRecord() {
		
	}
	
	public RemainTicketRecord(Timestamp timestamp, Integer remainTicketNum) {
		this.time = timestamp;
		this.remainTicketNum = remainTicketNum;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public Integer getRemainTicketNum() {
		return remainTicketNum;
	}
	
	public void setTime(Timestamp time){
		this.time = time;
	}
	
	public void setRemainTicketNum(Integer remainTicketNum){
		this.remainTicketNum = remainTicketNum;
	}

}
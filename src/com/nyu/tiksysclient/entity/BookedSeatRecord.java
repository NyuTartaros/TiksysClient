package com.nyu.tiksysclient.entity;

import java.sql.Timestamp;

public class BookedSeatRecord {
	
	private Timestamp time;
	private String name;
	private String phoneNo;
	private boolean haveMeal;
	
	public BookedSeatRecord(){
		
	}
	
	public BookedSeatRecord(Timestamp timestamp, String name, String phoneNo, boolean haveMeal){
		this.time = timestamp;
		this.name = name;
		this.phoneNo = phoneNo;
		this.haveMeal = haveMeal;
	}
	
	public Timestamp getTime(){
		return time;
	}
	
	public void setTime(Timestamp time){
		this.time = time;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPhoneNo(){
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	
	public boolean getHavaMeal(){
		return haveMeal;
	}
	
	public void setHaveMeal(boolean haveMeal){
		this.haveMeal = haveMeal;
	}

}

package com.nyu.tiksysclient.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.nyu.tiksysclient.entity.BookedSeatRecord;
import com.nyu.tiksysclient.entity.RemainTicketRecord;

@WebService(targetNamespace = "http://services.tiksys.nyu.com/")  
public interface TicketService {

	@WebMethod
	public boolean book(@WebParam(name="time") Long time, 
			@WebParam(name="name") String name, 
			@WebParam(name="phoneNo") String phoneNo);
	
	@WebMethod
	public String query(@WebParam(name="date") Long date);
	
	@WebMethod
	public String queryBookedSeat(@WebParam(name="name") String name, 
			@WebParam(name="phoneNo") String phoneNo);
	
	@WebMethod
	public boolean cancelMeal(@WebParam(name="time") Long time, 
			@WebParam(name="name") String name, 
			@WebParam(name="phoneNo") String phoneNo);
	
}
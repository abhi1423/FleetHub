package com.bankManagement.BankManagement.exceptions;


public class VehicleAlreadyExistsException extends RuntimeException{
	public VehicleAlreadyExistsException()
	{
		super("Vehicle already exists");
	}
	
	public VehicleAlreadyExistsException(String msg)
	{
		super(msg);
	}

}

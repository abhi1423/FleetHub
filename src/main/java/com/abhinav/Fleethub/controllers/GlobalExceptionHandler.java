package com.abhinav.Fleethub.controllers;


import com.abhinav.Fleethub.exceptionDTOs.UserAlreadyExistsPhoneDTO;
import com.abhinav.Fleethub.exceptionDTOs.UserNotFoundDTO;
import com.abhinav.Fleethub.exceptionDTOs.VehicleAlreadyExistsDTO;
import com.abhinav.Fleethub.exceptionDTOs.VehicleNotFoundDTO;
import com.abhinav.Fleethub.exceptions.UserAlreadyExistsException;
import com.abhinav.Fleethub.exceptions.UserNotFoundException;
import com.abhinav.Fleethub.exceptions.VehicleAlreadyExistsException;
import com.abhinav.Fleethub.exceptions.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserNotFoundDTO> userNotFoundException(UserNotFoundException exp)
	{
		UserNotFoundDTO dto = UserNotFoundDTO.builder().msg("User not found").status(HttpStatus.NOT_FOUND).userFound(false).build();
		ResponseEntity<UserNotFoundDTO> response=new ResponseEntity<UserNotFoundDTO>(dto,HttpStatus.NOT_FOUND);
		return response;
	}
	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<VehicleNotFoundDTO> vehicleNotFoundException(VehicleNotFoundException exp)
	{
		VehicleNotFoundDTO dto = VehicleNotFoundDTO.builder().msg("Vehicle not found").status(HttpStatus.NOT_FOUND).vehicleFound(false).build();
		ResponseEntity<VehicleNotFoundDTO> response=new ResponseEntity<VehicleNotFoundDTO>(dto,HttpStatus.NOT_FOUND);
		return response;
	}
	
	@ExceptionHandler(VehicleAlreadyExistsException.class)
	public ResponseEntity<VehicleAlreadyExistsDTO> vehicleAlreadyInDB(VehicleAlreadyExistsException exp)
	{
		VehicleAlreadyExistsDTO dto = VehicleAlreadyExistsDTO.builder().msg("Vehicle already exists in DB").status(HttpStatus.UNPROCESSABLE_ENTITY).msg("Vehicle already exists in Database with this registration").build();
		ResponseEntity<VehicleAlreadyExistsDTO> response=new ResponseEntity<VehicleAlreadyExistsDTO>(dto,HttpStatus.UNPROCESSABLE_ENTITY);
		return response;
	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<UserAlreadyExistsPhoneDTO> userAlreadyInDB(UserAlreadyExistsException exp)
	{
		UserAlreadyExistsPhoneDTO dto = UserAlreadyExistsPhoneDTO.builder().msg("User already exists in DB").status(HttpStatus.UNPROCESSABLE_ENTITY).msg("Vehicle already exists in Database with this registration").build();
		ResponseEntity<UserAlreadyExistsPhoneDTO> response=new ResponseEntity<UserAlreadyExistsPhoneDTO>(dto,HttpStatus.UNPROCESSABLE_ENTITY);
		return response;
	}
	



}

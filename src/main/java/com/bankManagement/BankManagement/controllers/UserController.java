package com.bankManagement.BankManagement.controllers;


import com.bankManagement.BankManagement.DTOs.EndTrip;
import com.bankManagement.BankManagement.DTOs.RequestContainingConsumerInfo;
import com.bankManagement.BankManagement.DTOs.ResponseFomTransporter;
import com.bankManagement.BankManagement.model.RequestToTransporter;
import com.bankManagement.BankManagement.model.Transporter;
import com.bankManagement.BankManagement.model.Vehicle;
import com.bankManagement.BankManagement.services.TransporterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transporter")
public class UserController {

	
	@Autowired
    TransporterService service;
	@PostMapping("/add")
	public void addTransporter(@RequestBody @Valid Transporter transporter)
	{
		service.RegisterTransporter(transporter);
	}
	
	@PostMapping("/add/vehicle/{username}")
	public void addVehicle(@RequestBody @Valid Vehicle vehicle, @PathVariable String username)
	{
		service.addVehicle(vehicle, username);
	}
	
	@GetMapping("/get/{id}")
	public Transporter getTransporter(@PathVariable String id)
	{
		return service.findbyusername(id);
	}
	@GetMapping("/get/vehicle/{id}")
	public Vehicle getByNumber(@PathVariable String id)
	{
		return service.findVehicleByNumber(id);
	}

	@GetMapping("/get/vehilceFromCity/{city}/{load}")
	public List<Vehicle> getVehicleFromCity(@PathVariable String city,@PathVariable long load)
	{
		return service.getVehiclesFromCity(city,load);
	}
	
	@GetMapping("/getOwner/{vehicleId}")
	public Transporter getUsingVehicleNumber(@PathVariable String vehicleId)
	{
		return service.getOwnerUsingVehicleNumber(vehicleId);
	}
	
	@PostMapping("/bookMyVehicle/{vehicleNumber}")
	public Vehicle addRequestForVehcile(@PathVariable String vehicleNumber,@RequestBody RequestContainingConsumerInfo consumerInfo)
	{
		return service.addRequestForVehcile(vehicleNumber,consumerInfo);
	}
	
	@PutMapping("update/availabilityOfVehicle/{transporterUserName}/{vehicleNumber}")
	public Vehicle updateAvailabilityofVehicle(@PathVariable String transporterUserName,@PathVariable String vehicleNumber)
	{
		return service.updateAvailabilityofVehicle(transporterUserName,vehicleNumber);
		
	}
	@GetMapping("/get/loadRequests/{transporterUserName}")
	public List<RequestToTransporter> getAllReuestByConsumers(@PathVariable String transporterUserName)
	{
		return service.getRequestForTransporter(transporterUserName);
	}
	
	
	@PutMapping("/requests/acceptance/{requestId}/{status}")
	public ResponseEntity<ResponseFomTransporter> requestUpdateProcess(@PathVariable long requestId, @PathVariable boolean status)
	{
		return service.requestUpdateProcess(requestId,status);
		
	}
	
	@PutMapping("/change/cityofVehicle/{transporter}/{vehicleNo}/{city}")
	public ResponseEntity<Vehicle> updateSourceofVehcile(@PathVariable String transporter,@PathVariable String vehicleNo,@PathVariable String city)
	{
		return service.updateSourceofVehcile(transporter,vehicleNo,city);
	}
	
	
	@PutMapping("/endTrip/{requestNumber}")
	public ResponseEntity<EndTrip> endTrip(@PathVariable long requestNumber)
	{
		return service.endTrip(requestNumber);
	}
	
	
	


}

package com.abhinav.Fleethub.controllers;


import com.abhinav.Fleethub.DTOs.*;
import com.abhinav.Fleethub.Entities.RequestToTransporter;
import com.abhinav.Fleethub.Entities.Transporter;
import com.abhinav.Fleethub.Entities.Vehicle;
import com.abhinav.Fleethub.services.TransporterService;
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
	public void addTransporter(@RequestBody @Valid TransporterDTO transporterDTO) {

		service.RegisterTransporter(transporterDTO);
	}
	
	@PostMapping("/add/vehicle/{username}")
	public void addVehicle(@RequestBody @Valid VehicleDTO vehicleDTO, @PathVariable String username) {

		service.addVehicle(vehicleDTO, username);
	}

    @GetMapping("/get/{id}")
    public Transporter getTransporter(@PathVariable String id) {

        return service.findbyusername(id);
    }

    @GetMapping("/get/vehicle/{id}")
    public Vehicle getByNumber(@PathVariable String id) {

        return service.findVehicleByNumber(id);
    }

    @GetMapping("/get/vehicleFromCity/{city}/{load}")
    public List<VehicleAndTransporterDetails> getVehicleFromCity(@PathVariable String city, @PathVariable long load) {

         return  service.getVehiclesFromCity(city, load);

    }
	
	@GetMapping("/getOwner/{vehicleId}")
	public Transporter getUsingVehicleNumber(@PathVariable String vehicleId) {

		return service.getOwnerUsingVehicleNumber(vehicleId);
	}
	
	@PostMapping("/bookMyVehicle/{vehicleNumber}")
	public VehicleAndTransporterDetails addRequestForVehicle(@PathVariable String vehicleNumber,@RequestBody RequestContainingConsumerInfo consumerInfo) {

		return service.addRequestForVehcile(vehicleNumber,consumerInfo);
	}
	
	@PutMapping("update/availabilityOfVehicle/{transporterUserName}/{vehicleNumber}")
	public Vehicle updateAvailabilityofVehicle(@PathVariable String transporterUserName,@PathVariable String vehicleNumber) {

		return service.updateAvailabilityofVehicle(transporterUserName,vehicleNumber);
		
	}
	@GetMapping("/get/loadRequests/{transporterUserName}")
	public List<RequestToTransporter> getAllRequestByConsumers(@PathVariable String transporterUserName) {

		return service.getRequestForTransporter(transporterUserName);
	}
	
	
	@PutMapping("/requests/acceptance/{requestId}/{status}")
	public ResponseEntity<ResponseFomTransporter> requestUpdateProcess(@PathVariable long requestId, @PathVariable boolean status) {

		return service.requestUpdateProcess(requestId,status);
		
	}
	
	@PutMapping("/change/cityOfVehicle/{transporter}/{vehicleNo}/{city}")
	public ResponseEntity<Vehicle> updateSourceOfVehicle(@PathVariable String transporter,@PathVariable String vehicleNo,@PathVariable String city) {

		return service.updateSourceofVehcile(transporter,vehicleNo,city);
	}
	
	
	@PutMapping("/endTrip/{requestNumber}")
	public ResponseEntity<EndTrip> endTrip(@PathVariable long requestNumber) {

        return service.endTrip(requestNumber);
	}
	
	
	


}

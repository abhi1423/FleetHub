package com.abhinav.Fleethub.services;


import com.abhinav.Fleethub.DTOs.EndTrip;
import com.abhinav.Fleethub.DTOs.RequestContainingConsumerInfo;
import com.abhinav.Fleethub.DTOs.ResponseFomTransporter;
import com.abhinav.Fleethub.DTOs.TransporterDTO;
import com.abhinav.Fleethub.Repository.RequestRepository;
import com.abhinav.Fleethub.Repository.TransporterRepository;
import com.abhinav.Fleethub.Repository.VehicleRepository;
import com.abhinav.Fleethub.exceptions.UserNotFoundException;
import com.abhinav.Fleethub.exceptions.VehicleAlreadyExistsException;
import com.abhinav.Fleethub.exceptions.VehicleNotFoundException;
import com.abhinav.Fleethub.Entities.RequestToTransporter;
import com.abhinav.Fleethub.Entities.Transporter;
import com.abhinav.Fleethub.Entities.TripDetails;
import com.abhinav.Fleethub.Entities.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransporterService
{
	//Add vehicle
	//Remove vehicle
	//update vehicle only which belongs to owner
	// List of available truck
	// update contact
	
	@Autowired
    TransporterRepository transporterRepository;
	
	@Autowired
    RequestRepository requestRepository;
	
	@Autowired
    VehicleRepository vehicleRepository;
	
	@Autowired
	RestTemplate restTemplate;

	public void addVehicle(Vehicle vehicle, String username)
	{
		
		Transporter transporter = transporterRepository.findByUsername(username)
		        .orElseThrow(() -> new UserNotFoundException("Transporter not found"));
		    
		    System.out.println("Transporter details: " + transporter.getUsername());
		    Optional<Vehicle> existingVehicleOpt = vehicleRepository.findByCarrierNumber(vehicle.getCarrierNumber());
		    if (existingVehicleOpt.isPresent()) {
		        throw new VehicleAlreadyExistsException("Vehicle with carrierNumber " + vehicle.getCarrierNumber() + " already exists");
		    }
		    TripDetails trip=new TripDetails();
		    trip.setSrc(transporter.getCity());
		    trip.setVehicle(vehicle);
		    vehicle.setTripDetails(trip);
		    vehicle.setTransporter(transporter);

		    List<Vehicle> listVehicles = transporter.getVehicles();
		    if (listVehicles == null) {
		        listVehicles = new ArrayList<>();
		    }
		    else
		    {
		    	boolean anymatch=listVehicles.stream().anyMatch(v->v.getCarrierNumber().equals(vehicle.getCarrierNumber()));
		    	if(anymatch)
		    	{
		    		throw new VehicleAlreadyExistsException("Duplicate vehcile");
		    	}
		    }
		    listVehicles.add(vehicle);
		    transporter.setVehicles(listVehicles);
		    transporterRepository.save(transporter);
		    
	}
	public void RegisterTransporter(TransporterDTO transporterDTO)
	{
		try {
            List<Vehicle> vehicles = transporterDTO.getVehicleDTOList().stream().map(
                    vehicleDTO -> {
                        return Vehicle.builder()
                                .carrierNumber(vehicleDTO.getCarrierNumber())
                                .carrierCategory(vehicleDTO.getCarrierCategory())
                                .fuelType(vehicleDTO.getFuelType())
                                .IsAvailable(vehicleDTO.isAvailable())
                                .model(vehicleDTO.getModel())
                                .numberOfAxcels(vehicleDTO.getNumberOfAxcels())
                                .capacityloadInTonsMin(vehicleDTO.getCapacityloadInTonsMin())
                                .capacityloadInTonsMax(vehicleDTO.getCapacityloadInTonsMax())
                                .build();
                    }
            ).toList();
            Transporter transporter = Transporter.builder()
                    .password(transporterDTO.getPassword())
                    .username(transporterDTO.getUsername())
                    .pincode(transporterDTO.getPincode())
                    .city(transporterDTO.getCity())
                    .phoneNumber(transporterDTO.getPhoneNumber())
                    .vehicles(vehicles)
                    .build();

			transporterRepository.save(transporter);
		}
		catch(Exception e)
		{
			System.out.println("Unkown exception occured");
		}
		
	}
    
	
	public void RemoveVehicle(String carrierNumber,long id)
	{
		Transporter transporter= transporterRepository.findById(id).orElse(null);
		if(transporter==null)
		{
			throw new UserNotFoundException("Transporter not found");
		}
		
		List<Vehicle> listVehicles=transporter.getVehicles();
		if(listVehicles!=null && listVehicles.size()>=0)
		{
			Vehicle removeVehicle=listVehicles.stream().filter(a->a.getCarrierNumber().equals(carrierNumber)).findFirst().get();
			if(removeVehicle!=null)
			{
				vehicleRepository.delete(removeVehicle);
			}
			else
			{
				throw new VehicleNotFoundException("Not found");
			}
			
		}
	}
	public Transporter findbyusername(String username)
	{
		return transporterRepository.findByUsername(username).get();
	}
	
	public Vehicle findVehicleByNumber(String id)
	{
		Vehicle vehicle=vehicleRepository.findByCarrierNumber(id).orElse(null);
		return vehicle;
		
	}
	
	public List<Vehicle> getVehiclesFromCity(String city,long load)
	{
		return vehicleRepository.getVehiclesFromCity(city,load);
	}
	public Transporter getOwnerUsingVehicleNumber(String vehicleId) {
		Vehicle vehicle=vehicleRepository.findByCarrierNumber(vehicleId).orElse(null);
		if(vehicle==null)
			throw new VehicleNotFoundException("Vehicle not found");
		Transporter tranporter=vehicle.getTransporter();
		if(tranporter==null)
		{
			throw new UserNotFoundException("Transporter not found");
		}
		return tranporter;
	}
	public Vehicle addRequestForVehcile(String vehicleNumber, RequestContainingConsumerInfo consumerInfo)
	{
		Vehicle vehicle=vehicleRepository.findByCarrierNumber(vehicleNumber).orElse(null);
		if(vehicle==null)
			throw new VehicleNotFoundException("Vehicle not found");
		Transporter transporter=vehicle.getTransporter();
		if(transporter==null)
		{
			throw new UserNotFoundException("Transporter not found");
		}
		
		RequestToTransporter reuqest = RequestToTransporter.builder().username(consumerInfo.getUsername())
				.loads(consumerInfo.getLoads()).
				source(consumerInfo.getSource()).
				destination(consumerInfo.getDestination())
				.vehicleNumber(vehicleNumber).transporter(transporter).build();
		
		List<RequestToTransporter> list =transporter.getRequests();
		if(transporter.getRequests()==null)
		{
			list =new ArrayList<>();
			list.add(reuqest);
		}
		else
		{
			list.add(reuqest);
		}
		transporter.setRequests(list);
		transporterRepository.save(transporter);
		return vehicle;
		
	}
	public List<RequestToTransporter> getRequestForTransporter(String transporterId) {
		
		return requestRepository.findByTransporterId(transporterId);
	}
	public Vehicle updateAvailabilityofVehicle(String transporterUserName, String vehicleNumber) 
	{
		Transporter transporter=transporterRepository.findByUsername(transporterUserName).orElse(null);
		if(transporter==null)
		{
			throw new UserNotFoundException("Transporter not found");
		}
		
		Vehicle vehicle = transporter.getVehicles().stream().filter(v->v.getCarrierNumber().equals(vehicleNumber)).findFirst().orElse(null);
		if(vehicle==null)
			throw new VehicleNotFoundException("Vehicle not found");		
		
		boolean availablity = vehicle.isIsAvailable();
		vehicle.setIsAvailable(!availablity);
		vehicleRepository.save(vehicle);
		return vehicle;
	}
	
	@Transactional
	public ResponseEntity<ResponseFomTransporter> requestUpdateProcess(long requestId, boolean status)
	{
		RequestToTransporter req=requestRepository.findById(requestId).orElse(null);
		if(req==null || !status)
		{
			ResponseFomTransporter response = ResponseFomTransporter.builder()
					                          .response(false).transporter(req.getTransporter().getUsername())
					                          .username(req.getUsername()).build(); 
			return new ResponseEntity<ResponseFomTransporter>(response,HttpStatus.BAD_REQUEST);
		}
		
		Vehicle vehicle = vehicleRepository.findByCarrierNumber(req.getVehicleNumber()).orElse(null);
		TripDetails trip = vehicle.getTripDetails();
		vehicle.setIsAvailable(false);
		trip.setSrc(req.getSource());
		trip.setDest(req.getDestination());

		String URL="http://localhost:8086/consumer/get/responseFromTransporter";
		ResponseEntity<ResponseFomTransporter> put_response= new ResponseEntity<ResponseFomTransporter>(HttpStatus.BAD_GATEWAY);
		try {
			ResponseFomTransporter responseFromTransporter= ResponseFomTransporter.builder().transporter(req.getTransporter().getUsername())
					.username(req.getUsername()).response(true).build();
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	    	HttpEntity<ResponseFomTransporter> request = new HttpEntity<>(responseFromTransporter, headers);
			put_response = restTemplate.exchange(URL,HttpMethod.PUT,request,ResponseFomTransporter.class);
			vehicleRepository.save(vehicle);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return put_response;
	}
	
	public ResponseEntity<Vehicle> updateSourceofVehcile(String transporterName, String vehicleNo, String city) {
		Transporter transporter=transporterRepository.findByUsername(transporterName).orElse(null);
		if(transporter==null)
		{
			throw new UserNotFoundException("Transporter not found");
		}
		
		Vehicle vehicle = transporter.getVehicles().stream().filter(v->v.getCarrierNumber().equals(vehicleNo)).findFirst().orElse(null);
		if(vehicle==null)
			throw new VehicleNotFoundException("Vehicle not found");
		
		if(vehicle.isIsAvailable())
		{
			vehicle.getTripDetails().setSrc(city);
			vehicleRepository.save(vehicle);
			ResponseEntity<Vehicle> response=new ResponseEntity<Vehicle>(vehicle,HttpStatus.CREATED);
			return response;
		}
		return new ResponseEntity<Vehicle>(vehicle,HttpStatus.BAD_REQUEST);
	}

    public ResponseEntity<EndTrip> endTrip(long reuqestId)
    {
    	RequestToTransporter req=requestRepository.findById(reuqestId).orElse(null);
    	EndTrip endTrip =  EndTrip.builder().remarks("Trip closed successfully").closed(true)
    			           .username(req.getUsername()).build();
    	String URL="http://localhost:8086/consumer/closeTrip";
		ResponseEntity<EndTrip> put_response= new ResponseEntity<EndTrip>(HttpStatus.BAD_GATEWAY);
		try {

	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	    	HttpEntity<EndTrip> request = new HttpEntity<>(endTrip, headers);
			put_response = restTemplate.exchange(URL,HttpMethod.PUT,request,EndTrip.class);
			Vehicle vehicle = vehicleRepository.findByCarrierNumber(req.getVehicleNumber()).orElse(null);
			vehicle.setIsAvailable(true);
			TripDetails trip=vehicle.getTripDetails();
			trip.setSrc(req.getDestination());
			trip.setDest(null);
			vehicle.setTripDetails(trip);
			vehicleRepository.save(vehicle);			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return put_response;
    }
}

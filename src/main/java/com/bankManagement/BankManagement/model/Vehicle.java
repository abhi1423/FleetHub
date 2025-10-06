package com.bankManagement.BankManagement.model;


import com.bankManagement.BankManagement.model.enums.VehicleCategory;
import com.bankManagement.BankManagement.model.enums.VehicleFuelType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Vehicles")

public class Vehicle {
	@Id
	@Column(unique=true)
	String carrierNumber;
	@Enumerated(EnumType.STRING)
    VehicleCategory carrierCategory; //LCVs,MCVs,HCVs light commercial vehicle
	@Column
	@Enumerated(EnumType.STRING)
    VehicleFuelType fuelType;
	@Column
	boolean IsAvailable=true;
	@Column
	String model;
	@Column
	int numberOfAxcels;
	@Column
	long capacityloadInTonsMin;
	@Column
	long capacityloadInTonsMax;// vehicle weight
	@ManyToOne
	@JsonBackReference("Tranporter-Vehicle")
	@JoinColumn(name="Owner")
	Transporter transporter;
	@JsonManagedReference("vehicle-trip")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "trip_id", referencedColumnName = "id")
	TripDetails tripDetails;
	
}

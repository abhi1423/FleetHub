package com.abhinav.Fleethub.Entities;


import com.abhinav.Fleethub.model.enums.VehicleCategory;
import com.abhinav.Fleethub.model.enums.VehicleFuelType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Vehicles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

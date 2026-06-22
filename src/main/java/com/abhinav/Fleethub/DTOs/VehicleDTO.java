package com.abhinav.Fleethub.DTOs;

import com.abhinav.Fleethub.model.enums.VehicleCategory;
import com.abhinav.Fleethub.model.enums.VehicleFuelType;
import lombok.Data;

@Data
public class VehicleDTO {

    private String carrierNumber;
    private VehicleCategory carrierCategory;
    private VehicleFuelType fuelType;
    private boolean isAvailable;
    private String model;
    private int numberOfAxcels;
    private long capacityloadInTonsMin;
    private long capacityloadInTonsMax;
}
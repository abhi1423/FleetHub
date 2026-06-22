package com.abhinav.Fleethub.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleAndTransporterDetails {

    private String carrierNumber;
    private String carrierCategory;
    private String fuelType;
    private boolean IsAvailable;
    private String model;
    private int numberOfAxcels;
    private long capacityloadInTonsMin;
    private long capacityloadInTonsMax;
    private TransporterInformation transporterInformation;

}
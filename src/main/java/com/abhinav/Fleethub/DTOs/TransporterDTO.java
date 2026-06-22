package com.abhinav.Fleethub.DTOs;

import java.util.List;

import com.abhinav.Fleethub.Entities.RequestToTransporter;
import com.abhinav.Fleethub.Entities.Vehicle;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransporterDTO {

    private String username;

    private String password;

    @Pattern(regexp="^\\+?[0-9]{10,15}$",message="Invalid Phone number")
    private String phoneNumber;

    private String city;

    @Pattern(regexp="^[1-9][0-9]{5}$",message="Invalid location")
    private String pincode;

    private List<VehicleDTO> vehicleDTOList;
}
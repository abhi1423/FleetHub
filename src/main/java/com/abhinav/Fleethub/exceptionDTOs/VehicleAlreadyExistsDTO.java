package com.abhinav.Fleethub.exceptionDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleAlreadyExistsDTO {
	String msg;
	HttpStatus status;
	boolean vehicleAlredyExists;

}

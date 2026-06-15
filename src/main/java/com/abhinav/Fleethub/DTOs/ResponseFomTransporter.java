package com.abhinav.Fleethub.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFomTransporter 
{
	String transporter;
	String username;
	boolean response;
}
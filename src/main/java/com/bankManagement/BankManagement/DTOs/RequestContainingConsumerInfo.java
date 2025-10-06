package com.bankManagement.BankManagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestContainingConsumerInfo
{
	String username;
	long loads;
	String source;
	String destination;

}

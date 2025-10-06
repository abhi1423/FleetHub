package com.bankManagement.BankManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="requestes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestToTransporter 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@Column
	String username;
	@Column
	long loads;
	@Column
	String vehicleNumber;
	@Column
	String source;
	@Column
	String destination;
	@JsonBackReference("Tranporter-requests")
	@ManyToOne
	@JoinColumn(name="transporter_id")
	Transporter transporter;
}

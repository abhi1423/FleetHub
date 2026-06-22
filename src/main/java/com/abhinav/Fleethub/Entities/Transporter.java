package com.abhinav.Fleethub.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="transporters")
public class Transporter
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;
	@Column
	String password;
	@Column
	String username;
	@Column(unique=true)
	@Pattern(regexp="^\\+?[0-9]{10,15}$",message="Invalid Phone number")
	String phoneNumber;
	@Column
	String city;
	@Column
	@Pattern(regexp="^[1-9][0-9]{5}$",message="Invalid location")
	String pincode;
	@JsonManagedReference("Tranporter-Vehicle")
	@OneToMany(mappedBy="transporter",cascade=CascadeType.ALL)
	List<Vehicle> vehicles;
	@JsonManagedReference("Tranporter-requests")
	@OneToMany(mappedBy="transporter",cascade=CascadeType.ALL)
	List<RequestToTransporter> requests;

}

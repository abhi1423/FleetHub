package com.abhinav.Fleethub.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trip")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDetails {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String src;
	@Column
	String dest;

	@JsonBackReference("vehicle-trip")
	@OneToOne(mappedBy="tripDetails")
	Vehicle vehicle;
	

}

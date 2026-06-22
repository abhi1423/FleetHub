package com.abhinav.Fleethub.Repository;

import com.abhinav.Fleethub.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,String>{

	Optional<Vehicle> findByCarrierNumber(String carrierNumber);

	Object findVehicleByCarrierNumber(String carrierNumber);
	
	@Query(value="select v.* from vehicles v join trip t on v.trip_id=t.id " +
            "where t.src=:city " +
            "and :load between v.capacityload_in_tons_min" +
            " and v.capacityload_in_tons_max" +
            " and v.is_available=true",nativeQuery=true)
	 List<Vehicle> getVehiclesFromCity(@Param("city")String city,@Param("load")long load);
}

package com.abhinav.Fleethub.Repository;


import com.abhinav.Fleethub.Entities.RequestToTransporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestToTransporter,Long>
{

	List<RequestToTransporter> findByTransporterId(String transporterId);

	Optional<RequestToTransporter> findById(long requestId);
}

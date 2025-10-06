package com.bankManagement.BankManagement.Repository;


import com.bankManagement.BankManagement.model.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter,Long>{

	Optional<Transporter> findByUsername(String username);

	Optional<Transporter> findById(String transporterId);


}

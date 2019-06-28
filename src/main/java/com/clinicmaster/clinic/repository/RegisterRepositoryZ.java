package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.RegisterZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepositoryZ extends JpaRepository<RegisterZ,String> {

}

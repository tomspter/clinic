package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.PatientZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepositoryZ extends JpaRepository<PatientZ,String> {
}

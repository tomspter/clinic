package com.clinicmaster.clinic.repository;


import com.clinicmaster.clinic.domain.DoctorVisitTimeZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DoctorVisitTimeRepositoryZ extends JpaRepository<DoctorVisitTimeZ,String>, JpaSpecificationExecutor<DoctorVisitTimeZ> {

     Optional<DoctorVisitTimeZ> findByDoctorIdAndVisitTime(Integer doctorId, Date visitTime);
}

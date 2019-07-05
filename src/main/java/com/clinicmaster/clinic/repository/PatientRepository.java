package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Doctor;
import com.clinicmaster.clinic.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface PatientRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "select name from patient_login where id = :id", nativeQuery = true)
     String findName(@Param("id") int id);
}

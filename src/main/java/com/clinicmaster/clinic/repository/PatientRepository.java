package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.PatientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<PatientLogin, Long> {
    @Query(value = "select name from patient_login where id = :id", nativeQuery = true)
     String findName(@Param("id") String id);

    PatientLogin findByNameAndPasswd(String name, String passwd);


}

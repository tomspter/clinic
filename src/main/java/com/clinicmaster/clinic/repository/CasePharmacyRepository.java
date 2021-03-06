package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.CasePharmacy;
import com.clinicmaster.clinic.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CasePharmacyRepository extends JpaRepository<CasePharmacy,String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update CasePharmacy dv set dv.moneyStatus = 1 where dv.patientId = ?1")
    int updateOnStatus(String patientId);

    @Query(value = "select id from case_pharmacy where patient_id = :patientId", nativeQuery = true)
    int[] findIdByPatientId(@Param("patientId")String patientId);

    List<CasePharmacy> findAllByPatientId(String patientId);

    List<CasePharmacy> findByMoneyStatus(Integer moneyStatus);
}

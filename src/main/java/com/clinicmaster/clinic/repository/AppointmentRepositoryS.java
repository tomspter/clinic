package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.AppointmentS;
import com.clinicmaster.clinic.domain.CasePharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepositoryS extends JpaRepository<AppointmentS, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update AppointmentS dv set dv.moneyStatus = 1 where dv.patientId = ?1")
    int updateStatus(int registerId);
}

package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.DoctorVisittime;
import com.clinicmaster.clinic.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Register r set r.registerStatus = 1 where r.id = ?1")
    int updateOnStatus(int registerId);

    @Query(value = "select visittime_id from register where id = :registerId", nativeQuery = true)
    int findVisittimeId(@Param("registerId") int registerId);


    @Query(value = "select patient_id from register where visittime_id = :visittimeId", nativeQuery = true)
    int[] findPatientId(@Param("visittimeId") int visittimeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Register r set r.registerStatus = 0 where r.id = ?1")
    int updateOffStatus(int registerId);
}

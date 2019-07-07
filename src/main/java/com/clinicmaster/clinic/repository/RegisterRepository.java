package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Register;
import com.microsoft.schemas.office.office.STInsetMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Register r set r.registerStatus = 1 where r.id = ?1")
    int updateOnStatus(String registerId);

    @Query(value = "select visit_time_id from register where id = :registerId", nativeQuery = true)
    String findVisittimeId(@Param("registerId") String registerId);


    @Query(value = "select patient_id from register where visit_time_id = :visitTimeId", nativeQuery = true)
    String[] findPatientId(@Param("visitTimeId") String visitTimeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Register r set r.registerStatus = 0 where r.id = ?1")
    int updateOffStatus(String registerId);
}

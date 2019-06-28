package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.DoctorVisittime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import java.util.List;

public interface DoctorVisitTimeRepository extends JpaRepository<DoctorVisittime, Long> {

    @Query(value="select dv.id, dv.doctor_id, d.name, dv.visit_time, dv.status, dv.total_amount, dv.amount from doctor_visittime dv, " +
            "doctor d where dv.doctor_id=:doctorId and d.id = :doctorId",nativeQuery = true)
    List<DoctorVisittime> findAllByDoctorId(@Param("doctorId") int doctorId);

    @Query(value="select amount from doctor_visittime where id = :doctorId",nativeQuery = true)
    int findAmountById(@Param("doctorId") int doctorId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DoctorVisittime dv set dv.status = 1,dv.totalAmount = totalAmount+1, dv.amount = amount-1 where dv.id = ?1")
    int updateOnStatus(int visittimeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DoctorVisittime set status = 0 where id = ?1")
    int updateOnStatusF(int visittimeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DoctorVisittime dv set dv.totalAmount = totalAmount-1, dv.amount = amount+1 where dv.id = ?1")
    int updateOffStatus(int visittimeId);

}

package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.DoctorVisitTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface DoctorVisitTimeRepository extends JpaRepository<DoctorVisitTime, String> {

    @Query(value="select dv.id, dv.doctor_id, d.name, dv.visit_time, dv.status, dv.total_amount, dv.amount from doctor_visit_time dv, " +
            "doctor d where dv.doctor_id=:doctorId and d.id = :doctorId",nativeQuery = true)
    List<DoctorVisitTime> findAllByDoctorId(@Param("doctorId") int doctorId);

    @Query(value="select amount from doctor_visit_time where id = :visittimeId",nativeQuery = true)
    int findAmountById(@Param("visittimeId") String visittimeId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DoctorVisitTime dv set dv.totalAmount = totalAmount+1, dv.amount = amount-1 where dv.id = ?1")
    int updateOnStatus(String visittimeId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DoctorVisitTime dv set dv.totalAmount = totalAmount-1, dv.amount = amount+1 where dv.id = ?1")
    int updateOffStatus(String visittimeId);

    @Query(value="select doctor_id from doctor_visit_time where id = :visittimeId",nativeQuery = true)
    int findByIdS(@Param("visittimeId") String visittimeId);

    Optional<DoctorVisitTime> findByDoctorIdAndVisitTime(Integer doctorId, Timestamp visitTime);
}

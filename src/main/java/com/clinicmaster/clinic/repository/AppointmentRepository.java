package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String> {

    Optional<Appointment> findByPatientId(String patientId);

    void deleteByPatientId(String patientId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Appointment r set r.status = 0 where r.id = ?1")
    int updateStatus(String appointmentId);

    @Query(value = "select visittime_id from appointment where id = :appointmentId", nativeQuery = true)
    String findVisittimeId(@Param("appointmentId") String appointmentId);
}

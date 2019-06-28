package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String> {

    Optional<Appointment> findByPatientId(String patientId);

    void deleteByPatientId(String patientId);
}

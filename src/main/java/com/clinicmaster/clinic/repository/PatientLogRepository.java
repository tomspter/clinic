package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.PatientLog;
import com.clinicmaster.clinic.domain.PatientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientLogRepository extends JpaRepository<PatientLog, Long> {
}

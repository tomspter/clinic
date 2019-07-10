package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.CaseTransfusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseTransfusionRepository extends JpaRepository<CaseTransfusion,String> {

    List<CaseTransfusion> findAllByPatientId(String patientId);

}

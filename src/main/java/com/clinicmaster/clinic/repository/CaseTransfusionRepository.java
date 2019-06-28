package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.CaseTransfusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseTransfusionRepository extends JpaRepository<CaseTransfusion,String> {
}

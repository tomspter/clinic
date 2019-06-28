package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.CasePharmacyZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasePharmacyRepositoryZ extends JpaRepository<CasePharmacyZ,String> {

    List<CasePharmacyZ> findByMonryStatus(Integer moneyStatus);
}

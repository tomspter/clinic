package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.MedicineGet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineGetRepository extends JpaRepository<MedicineGet,String> {

    Optional<MedicineGet> findByCaseId(String caseId);
}

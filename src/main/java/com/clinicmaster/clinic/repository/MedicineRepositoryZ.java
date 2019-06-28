package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.MedicineZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicineRepositoryZ extends JpaRepository<MedicineZ,Integer> {
}

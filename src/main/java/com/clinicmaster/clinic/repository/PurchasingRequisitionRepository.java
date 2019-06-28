package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.PurchasingRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasingRequisitionRepository extends JpaRepository<PurchasingRequisition,Integer> {

}

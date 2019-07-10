package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.PurchasingRequisition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasingRequisitionRepository extends JpaRepository<PurchasingRequisition,String> {

    Page<PurchasingRequisition> findAllByStaffId(Integer staffId, Pageable pageable);
}

package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Transactional
    @Modifying
    @Query("update Medicine set restNum = restNum-?2  where id = ?1")
    int updateOntotalNum(int medicineId, int medicineNum);

    Medicine findById(int id);
}

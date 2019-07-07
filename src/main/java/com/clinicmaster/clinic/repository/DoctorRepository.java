package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByDepartmentId(int department_id);

    @Transactional
    @Modifying
    @Query("update Doctor set totalAmount = totalAmount+1  where id = ?1")
    int updateTotalamount(int doctorId);

    @Transactional
    @Modifying
    @Query("update Doctor set totalAmount = totalAmount-1  where id = ?1")
    int updateOffAmount(int doctorId);

}

package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.ClinicStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicStaffRepository extends JpaRepository<ClinicStaff,Integer> {
}

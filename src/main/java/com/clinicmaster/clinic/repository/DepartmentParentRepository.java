package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.ClinicInfo;
import com.clinicmaster.clinic.domain.Department;
import com.clinicmaster.clinic.domain.Department;
import com.clinicmaster.clinic.domain.DepartmentParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentParentRepository extends JpaRepository<DepartmentParent, Long> {
}

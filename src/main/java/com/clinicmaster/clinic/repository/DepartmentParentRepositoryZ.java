package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.DepartmentParentZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentParentRepositoryZ extends JpaRepository<DepartmentParentZ,Integer> {
}

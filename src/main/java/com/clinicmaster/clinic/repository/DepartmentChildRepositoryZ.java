package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.DepartmentChildZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentChildRepositoryZ extends JpaRepository<DepartmentChildZ,Integer> {

    Optional<DepartmentChildZ>  findByDoctorId(Integer doctorId);
}

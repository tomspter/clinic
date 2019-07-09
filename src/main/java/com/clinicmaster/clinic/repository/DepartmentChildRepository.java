package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Department;
import com.clinicmaster.clinic.domain.DepartmentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;

public interface DepartmentChildRepository extends JpaRepository<DepartmentChild, Integer> {
    @Query(value="select * from department_child d where d.parent_id = :parentId",nativeQuery=true)
    List<DepartmentChild> findByParentId(@Param("parentId") int parentId);

    Optional<DepartmentChild> findByDoctorId(Integer doctorId);
}

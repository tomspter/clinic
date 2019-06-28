package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.Department;
import com.clinicmaster.clinic.domain.DepartmentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

public interface DepartmentChildRepository extends JpaRepository<DepartmentChild, Long> {
    @Query(value="select * from department_child d where d.parent_id = :parentId",nativeQuery=true)
    List<DepartmentChild> findByParentId(@Param("parentId") int parentId);
}

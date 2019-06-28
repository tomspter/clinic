package com.clinicmaster.clinic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DepartmentParentZ {

    @Id
    private Integer id;

    private String parentName;

    private Integer clinicId;

    private Integer childId;
}

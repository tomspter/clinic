package com.clinicmaster.clinic.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DepartmentChildZ {

    @Id
    private Integer id;

    private Integer parentId;

    private String childName;

    private Integer doctorId;

    private Integer count;
}

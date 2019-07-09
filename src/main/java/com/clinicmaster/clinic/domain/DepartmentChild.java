package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class DepartmentChild {
    @Id
    private int id;
    private int parentId;
    private String childName;
    private int doctorId;
    private int count;
}

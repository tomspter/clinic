package com.clinicmaster.clinic.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PatientZ {

    @Id
    private String id;

    private String name;

}

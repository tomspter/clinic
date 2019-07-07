package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PatientLogin {
    @Id
    private String id;
    private String name;
    private String passwd;
    private String securitycodeLocal;
    private String securitycodePhone;
    private String memoryTime;
}

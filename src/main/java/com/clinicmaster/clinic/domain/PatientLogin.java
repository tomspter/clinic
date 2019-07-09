package com.clinicmaster.clinic.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientLogin {
    @Id
    private String id;
    private String name;
    private String passwd;
    private String securitycodeLocal;
    private String securitycodePhone;
    private String memoryTime;
}

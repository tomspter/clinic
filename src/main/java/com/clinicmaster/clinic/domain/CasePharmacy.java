package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CasePharmacy {
    @Id
    private String id;
    private String patientId;
    private int doctorId;
    private int medicineId;
    private int medicineNum;
    private BigDecimal totalNum;
    private int moneyStatus;
}

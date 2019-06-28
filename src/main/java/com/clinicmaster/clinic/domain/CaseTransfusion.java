package com.clinicmaster.clinic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class CaseTransfusion {

    @Id
    private String id;

    private String patientId;

    private Integer doctorId;

    private Integer medicineId;

    private Integer medicineNum;

    private BigDecimal totalNum;

    private Integer monryStatus;
}

package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MedicinePayMent {
    private int medcineId;
    private String medicineName;
    private int medicineMum;
    private BigDecimal money;
}
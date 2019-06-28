package com.clinicmaster.clinic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class MedicineZ {

    @Id
    private Integer id;

    private String name;

    private Integer totalNum;

    private Integer restNum;

    private BigDecimal money;
}

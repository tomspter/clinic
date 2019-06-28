package com.clinicmaster.clinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
public class PurchasingRequisition {

    @Id
    private Integer id;

    private Integer medicineId;

    private Integer num;

    private Timestamp time;

    private Integer staffId;

    private Integer status;
}

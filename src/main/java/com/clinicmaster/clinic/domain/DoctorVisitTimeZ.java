package com.clinicmaster.clinic.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;


@Data
@Entity
public class DoctorVisitTimeZ {

    @Id
    private String id;

    private Integer doctorId;

    private Timestamp visitTime;

    private Integer status;

    private Integer totalAmount;

    private Integer amount;

}

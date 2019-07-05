package com.clinicmaster.clinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
public class RegisterZ {

    @Id
    private String uuid;

    private String patientId;

    private Integer departmentchildId;

    private Timestamp registerTime;

    private Integer registerStatus;

    private Timestamp insertTime;
}

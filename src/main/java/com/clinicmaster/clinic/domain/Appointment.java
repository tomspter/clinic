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
public class Appointment {

    @Id
    private String id;

    private String patientId;

    private Timestamp visittimeId;

    private Integer status;
}

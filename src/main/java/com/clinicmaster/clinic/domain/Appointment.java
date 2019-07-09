package com.clinicmaster.clinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    private String id;

    private String patientId;

    private String visittimeId;

    private Integer status;
}

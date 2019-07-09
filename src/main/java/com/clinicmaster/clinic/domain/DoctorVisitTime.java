package com.clinicmaster.clinic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorVisitTime {

    @Id
    private String id;
    private int doctorId;
    @JsonFormat(pattern="yyyy-MM-dd hh:MM:ss",timezone="GMT+8")
    private Timestamp visitTime;
    private int status;
    private int totalAmount;
    private int amount;
}
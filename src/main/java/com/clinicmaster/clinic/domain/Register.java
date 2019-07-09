package com.clinicmaster.clinic.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    @Id
    private String id;
    private String patientId;
    private int departmentchildId;
    private Timestamp registerTime;
    private int registerStatus;

}

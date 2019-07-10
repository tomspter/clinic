package com.clinicmaster.clinic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineGet {

    @Id
    private String id;

    private String caseId;

    private int status;

    private Timestamp getTime;

    private Integer doctorId;
}

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
public class UserLog {

    @Id
    private Integer id;

    private Integer userId;

    private Timestamp loginTime;
}

package com.clinicmaster.clinic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ClinicStaff {

    @Id
    private Integer id;

    private String name;

    private Integer sex;

    private String birth;

    private String tel;

    private String photo;

    private String title;
}

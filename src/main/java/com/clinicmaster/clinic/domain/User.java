package com.clinicmaster.clinic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private Integer id;

    private String userName;

    private String passWord;

    private String category;
}

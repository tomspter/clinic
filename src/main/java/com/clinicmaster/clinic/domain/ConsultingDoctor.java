package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsultingDoctor {
    private int doctorId;
    private String name;
    private List<RegisterConsulting> registerConsultings;
}

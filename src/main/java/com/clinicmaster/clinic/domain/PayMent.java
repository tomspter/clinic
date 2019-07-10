package com.clinicmaster.clinic.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class PayMent {

    private BigDecimal totalMoney;
    private List<MedicinePayMent> payMents;
}
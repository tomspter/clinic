package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.*;
import com.clinicmaster.clinic.repository.CasePharmacyRepository;
import com.clinicmaster.clinic.repository.MedicineRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicineController {
    @Autowired
    private CasePharmacyRepository casePharmacyRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @ApiOperation("患者支付费用")
    @PostMapping("/patient/patientPayment")
    public UnifyReponse patientPayment(@RequestParam("patient_id")int patientId){
        try {
            PayMent payMent = new PayMent();
            List<MedicinePayMent> list = new ArrayList<>();
            int totalMoney = 0;
            List<CasePharmacy> casePharmacies = casePharmacyRepository.findAllByPatientId(patientId);
            for(CasePharmacy casePharmacy : casePharmacies){
                MedicinePayMent medicinePayMent = new MedicinePayMent();
                int medicineId = casePharmacy.getMedicineId();
                int medicineNum = casePharmacy.getMedicineNum();
                medicineRepository.updateOntotalNum(medicineId, medicineNum);
                medicinePayMent.setMedcineId(medicineId);
                medicinePayMent.setMedicineMum(medicineNum);
                Medicine medicine = medicineRepository.findById(medicineId);
                medicinePayMent.setMedicineName(medicine.getName());
                medicinePayMent.setMoney(medicine.getMoney()*medicineNum);
                totalMoney += medicinePayMent.getMoney();
                list.add(medicinePayMent);
            }
            payMent.setPayMents(list);
            payMent.setTotalMoney(totalMoney);
            UnifyReponse<List<Doctor>> response = new UnifyReponse<>();
            int[] resultId = casePharmacyRepository.findIdByPatientId(patientId);
            for(int id : resultId ){
                casePharmacyRepository.updateOnStatus(patientId);
            }
            response = new UnifyReponse(1, "success", payMent);
            return response;
        }catch (Exception e){
            return new UnifyReponse(0, "fail");
        }
    }
}

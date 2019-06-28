package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.CasePharmacy;
import com.clinicmaster.clinic.domain.Doctor;
import com.clinicmaster.clinic.repository.CasePharmacyRepository;
import com.clinicmaster.clinic.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicineController {
    @Autowired
    private CasePharmacyRepository casePharmacyRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @PostMapping("/patientPayment")
    public UnifyReponse patientPayment(@RequestParam("patient_id")int patientId){
        int[] resultId = casePharmacyRepository.findIdByPatientId(patientId);
        for(int id : resultId ){
            casePharmacyRepository.updateOnStatus(patientId);
        }
        List<CasePharmacy> casePharmacies = casePharmacyRepository.findAllByPatientId(patientId);
        for(CasePharmacy casePharmacy : casePharmacies){
            int medicineId = casePharmacy.getMedicineId();
            int medicineNum = casePharmacy.getMedicineNum();
            medicineRepository.updateOntotalNum(medicineId, medicineNum);
        }
        UnifyReponse<List<Doctor>> response = new UnifyReponse<>();
        if(casePharmacies != null) {
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }
}

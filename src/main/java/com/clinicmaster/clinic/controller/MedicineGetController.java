package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.CasePharmacy;
import com.clinicmaster.clinic.domain.CaseTransfusion;
import com.clinicmaster.clinic.domain.MedicineGet;
import com.clinicmaster.clinic.repository.CasePharmacyRepository;
import com.clinicmaster.clinic.repository.CaseTransfusionRepository;
import com.clinicmaster.clinic.repository.MedicineGetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@Slf4j
public class MedicineGetController {

    @Autowired
    private MedicineGetRepository medicineGetRepository;

    @Autowired
    private CasePharmacyRepository casePharmacyRepository;

    @Autowired
    private CaseTransfusionRepository caseTransfusionRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/getCaseForPatient")
    public UnifyReponse getCaseForPatient(@RequestParam("patient_id") String patientId) {

        try {

            for (CasePharmacy casePharmacy : casePharmacyRepository.findAllByPatientId(patientId)) {
                String caseId = casePharmacy.getId();
                Optional<MedicineGet> medicineGetOptional = medicineGetRepository.findByCaseId(caseId);
                if (medicineGetOptional.isPresent()) {
                    MedicineGet medicineGet=medicineGetOptional.get();
                    medicineGet.setGetTime(new Timestamp(System.currentTimeMillis()));
                    medicineGet.setStatus(1);
                    medicineGetRepository.saveAndFlush(medicineGet);
                }
            }

            for (CaseTransfusion transactional : caseTransfusionRepository.findAllByPatientId(patientId)) {
                String tranId = transactional.getId();
                Optional<MedicineGet> medicineGetOptional = medicineGetRepository.findByCaseId(tranId);
                if (medicineGetOptional.isPresent()) {
                    MedicineGet medicineGet=medicineGetOptional.get();
                    medicineGet.setGetTime(new Timestamp(System.currentTimeMillis()));
                    medicineGet.setStatus(1);
                    medicineGetRepository.saveAndFlush(medicineGet);
                }
            }

        } catch (Exception e){
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");
    }


    @PostMapping("/transfusionBed")
    public UnifyReponse transfusionBed(){
        return null;
    }
}

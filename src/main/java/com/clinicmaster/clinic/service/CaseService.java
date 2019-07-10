package com.clinicmaster.clinic.service;

import com.clinicmaster.clinic.domain.CasePharmacy;
import com.clinicmaster.clinic.domain.CaseTransfusion;
import com.clinicmaster.clinic.domain.Medicine;
import com.clinicmaster.clinic.domain.MedicineGet;
import com.clinicmaster.clinic.repository.CasePharmacyRepository;
import com.clinicmaster.clinic.repository.CaseTransfusionRepository;
import com.clinicmaster.clinic.repository.MedicineGetRepository;
import com.clinicmaster.clinic.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaseService {

    @Autowired
    private CasePharmacyRepository casePharmacyRepository;

    @Autowired
    private CaseTransfusionRepository caseTransfusionRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineGetRepository medicineGetRepository;


    public void writeCase(String patientId, Integer medicineId, Integer medicineNum, String type, HttpServletRequest httpServletRequest) {
        if ("pharmacy".equals(type)) {

            Optional<Medicine> medicine = medicineRepository.findById(medicineId);
            if (medicine.isPresent()) {

                Medicine medicineZGet = medicine.get();
                BigDecimal medicineMoney = medicineZGet.getMoney();
                medicineZGet.setRestNum(medicineZGet.getRestNum() - 1);

                medicineRepository.saveAndFlush(medicineZGet);

                CasePharmacy casePharmacy = new CasePharmacy();
                casePharmacy.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                //TODO
//                casePharmacy.setDoctorId((Integer) httpServletRequest.getSession().getAttribute("uid"));
                casePharmacy.setDoctorId(1);
                casePharmacy.setMedicineId(medicineId);
                casePharmacy.setMedicineNum(medicineNum);
                casePharmacy.setPatientId(patientId);
                casePharmacy.setTotalNum(medicineMoney.multiply(BigDecimal.valueOf(medicineNum)));
                casePharmacy.setMoneyStatus(0);

                casePharmacyRepository.saveAndFlush(casePharmacy);

                MedicineGet medicineGet=MedicineGet
                        .builder()
                        .caseId(casePharmacy.getId())
                        .doctorId(casePharmacy.getDoctorId())
                        .id(UUID.randomUUID().toString().replace("-", "").toLowerCase())
                        .status(0)
                        .build();

                medicineGetRepository.saveAndFlush(medicineGet);
            }
        }

        if ("transfusion".equals(type)) {
            Optional<Medicine> medicine2 = medicineRepository.findById(medicineId);
            if (medicine2.isPresent()) {
                BigDecimal medicineMoney = medicine2.get().getMoney();

                CaseTransfusion caseTransfusion = new CaseTransfusion();
                caseTransfusion.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
//                    caseTransfusion.setDoctorId((Integer) httpServletRequest.getSession().getAttribute("uid"));
                caseTransfusion.setDoctorId(1);
                caseTransfusion.setMedicineId(medicineId);
                caseTransfusion.setMedicineNum(medicineNum);
                caseTransfusion.setPatientId(patientId);
                caseTransfusion.setTotalNum(medicineMoney.multiply(BigDecimal.valueOf(medicineNum)));
                caseTransfusion.setMoneyStatus(0);


                //TODO BEDID


                caseTransfusionRepository.saveAndFlush(caseTransfusion);


                MedicineGet medicineGet=MedicineGet
                        .builder()
                        .caseId(caseTransfusion.getId())
                        .doctorId(caseTransfusion.getDoctorId())
                        .id(UUID.randomUUID().toString().replace("-", "").toLowerCase())
                        .status(0)
                        .build();

                medicineGetRepository.saveAndFlush(medicineGet);
            }


        }


    }


}

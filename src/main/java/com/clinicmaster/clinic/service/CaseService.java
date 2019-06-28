package com.clinicmaster.clinic.service;

import com.clinicmaster.clinic.domain.CasePharmacyZ;
import com.clinicmaster.clinic.domain.CaseTransfusion;
import com.clinicmaster.clinic.domain.MedicineZ;
import com.clinicmaster.clinic.repository.CasePharmacyRepositoryZ;
import com.clinicmaster.clinic.repository.CaseTransfusionRepository;
import com.clinicmaster.clinic.repository.MedicineRepositoryZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaseService {

    @Autowired
    private CasePharmacyRepositoryZ casePharmacyRepositoryZ;

    @Autowired
    private CaseTransfusionRepository caseTransfusionRepository;

    @Autowired
    private MedicineRepositoryZ medicineRepositoryZ;



    public void writeCase(String patientId, Integer medicineId, Integer medicineNum, String type, HttpServletRequest httpServletRequest) {
        if ("Pharmacy".equals(type)) {

            Optional<MedicineZ> medicine = medicineRepositoryZ.findById(medicineId);
            if (medicine.isPresent()) {

                MedicineZ medicineZGet =medicine.get();
                BigDecimal medicineMoney = medicineZGet.getMoney();
                medicineZGet.setRestNum(medicineZGet.getRestNum()-1);

                medicineRepositoryZ.saveAndFlush(medicineZGet);

                CasePharmacyZ casePharmacyZ = new CasePharmacyZ();
                casePharmacyZ.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                casePharmacyZ.setDoctorId((Integer) httpServletRequest.getSession().getAttribute("uid"));
                casePharmacyZ.setMedicineId(medicineId);
                casePharmacyZ.setMedicineNum(medicineNum);
                casePharmacyZ.setPatientId(patientId);
                casePharmacyZ.setTotalNum(medicineMoney.multiply(BigDecimal.valueOf(medicineNum)));
                casePharmacyZ.setMonryStatus(0);

                casePharmacyRepositoryZ.saveAndFlush(casePharmacyZ);
            }
            if ("transfusion".equals(type)) {
                Optional<MedicineZ> medicine2 = medicineRepositoryZ.findById(medicineId);
                if (medicine2.isPresent()) {
                    BigDecimal medicineMoney = medicine2.get().getMoney();

                    CaseTransfusion caseTransfusion = new CaseTransfusion();
                    caseTransfusion.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                    caseTransfusion.setDoctorId((Integer) httpServletRequest.getSession().getAttribute("uid"));
                    caseTransfusion.setMedicineId(medicineId);
                    caseTransfusion.setMedicineNum(medicineNum);
                    caseTransfusion.setPatientId(patientId);
                    caseTransfusion.setTotalNum(medicineMoney.multiply(BigDecimal.valueOf(medicineNum)));
                    caseTransfusion.setMonryStatus(0);

                    caseTransfusionRepository.saveAndFlush(caseTransfusion);
                }


            }


        }

    }

}

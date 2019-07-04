package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.CasePharmacyZ;
import com.clinicmaster.clinic.repository.CasePharmacyRepositoryZ;
import com.clinicmaster.clinic.repository.CaseTransfusionRepository;
import com.clinicmaster.clinic.repository.MedicineRepositoryZ;
import com.clinicmaster.clinic.service.CaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class CaseController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private CasePharmacyRepositoryZ casePharmacyRepositoryZ;

    @Autowired
    private CaseTransfusionRepository caseTransfusionRepository;

    @Autowired
    private MedicineRepositoryZ medicineRepositoryZ;


    @ApiOperation("写入取药病历")
    @PostMapping("/clinic/writeCasePharmacy")
    public UnifyReponse writeCase(@RequestParam("patient_id") String patientId,
                                  @RequestParam("medicine_id") Integer medicineId,
                                  @RequestParam("medicine_num")Integer medicineNum,
                                  @RequestParam("type") String type,
                                  HttpServletRequest httpServletRequest){


        try {
            caseService.writeCase(patientId,medicineId,medicineNum,type,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");

    }

    @ApiOperation("查找取药病历")
    @PostMapping("/clinic/getCaseForPharmacy")
    public UnifyReponse getCaseForPharmacy(){

        List<CasePharmacyZ> casePharmacies = null;

        try {
            casePharmacies= casePharmacyRepositoryZ.findByMonryStatus(1);
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(0,"error");
        }

        return new UnifyReponse(1,"success",casePharmacies);
    }
}

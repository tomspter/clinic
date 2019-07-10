package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.CasePharmacy;
import com.clinicmaster.clinic.domain.MedicineGet;
import com.clinicmaster.clinic.repository.CasePharmacyRepository;
import com.clinicmaster.clinic.repository.CaseTransfusionRepository;
import com.clinicmaster.clinic.repository.MedicineGetRepository;
import com.clinicmaster.clinic.repository.MedicineRepository;
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
    private CasePharmacyRepository casePharmacyRepository;

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

        List<CasePharmacy> casePharmacies ;

        try {
            casePharmacies= casePharmacyRepository.findByMoneyStatus(1);
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(0,"error");
        }

        return new UnifyReponse(1,"success",casePharmacies);
    }
}

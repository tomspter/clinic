package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.PurchasingRequisition;
import com.clinicmaster.clinic.repository.PurchasingRequisitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@Slf4j
public class PurchasingRequisitionController {

    @Autowired
    private PurchasingRequisitionRepository purchasingRequisitionRepository;

    @PostMapping("/clinic/writePurchasing")
    public UnifyReponse writePurchasing(@RequestParam("medicine_id") Integer medicineId,
                                        @RequestParam("num") Integer num,
                                        HttpServletRequest httpServletRequest){

        try {
            PurchasingRequisition purchasingRequisition=PurchasingRequisition
                    .builder()
                    .medicineId(medicineId)
                    .num(num)
                    .staffId((Integer) httpServletRequest.getSession().getAttribute("uid"))
                    .time(new Timestamp(System.currentTimeMillis()))
                    .build();
            purchasingRequisitionRepository.saveAndFlush(purchasingRequisition);
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }


        return new UnifyReponse(1, "success");
    }


    @GetMapping("clinic/getPurchasing")
    public UnifyReponse getPurchasing(@RequestParam(value = "page",defaultValue = "1") int page,
                                      @RequestParam(value = "sort",defaultValue = "asc") String sort,
                                      @RequestParam(value = "rows",defaultValue = "10" )int rows){
        Pageable pageable= PageRequest.of(page,rows, Sort.Direction.fromString(sort));
        Page<PurchasingRequisition> purchasingRequisitions=purchasingRequisitionRepository.findAll(pageable);
        return new UnifyReponse(1,"success",purchasingRequisitions);

    }

    @PostMapping("/modifyPurchasing")
    public UnifyReponse modifyPurchasing(@RequestParam("id") int id){

        Optional<PurchasingRequisition> purchasingRequisition=purchasingRequisitionRepository.findById(id);
        if (purchasingRequisition.isPresent()) {
            PurchasingRequisition p=purchasingRequisition.get();
            p.setStatus(1);
            purchasingRequisitionRepository.saveAndFlush(p);
        }
        return new UnifyReponse(1, "success");

    }



}

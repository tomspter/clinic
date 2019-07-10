package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.PurchasingRequisition;
import com.clinicmaster.clinic.repository.PurchasingRequisitionRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class PurchasingRequisitionController {

    @Autowired
    private PurchasingRequisitionRepository purchasingRequisitionRepository;

    @ApiOperation("写入采购申请")
    @PostMapping("/clinic/writePurchasing")
    public UnifyReponse writePurchasing(@RequestParam("medicine_id") Integer medicineId,
                                        @RequestParam("num") Integer num,
                                        HttpServletRequest httpServletRequest) {

        try {

            PurchasingRequisition purchasingRequisition = PurchasingRequisition
                    .builder()
                    .id(UUID.randomUUID().toString().replace("-", "").toLowerCase())
                    .medicineId(medicineId)
                    .num(num)
                    .staffId((Integer) httpServletRequest.getSession().getAttribute("uid"))
//                    .staffId(1)
                    .time(new Timestamp(System.currentTimeMillis()))
                    .status(0)
                    .build();

            purchasingRequisitionRepository.saveAndFlush(purchasingRequisition);
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }


        return new UnifyReponse(1, "success");
    }


    @ApiOperation("查询采购申请")
    @PostMapping("clinic/getPurchasing")
    public UnifyReponse getPurchasing(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "sort", defaultValue = "time") String sort,
                                      @RequestParam(value = "rows", defaultValue = "10") int rows,
                                      @RequestParam("staff_id") Integer staffId) {
        try {
            Pageable pageable = PageRequest.of(page - 1, rows, Sort.Direction.DESC, sort);
            Page<PurchasingRequisition> purchasingRequisitions = purchasingRequisitionRepository.findAllByStaffId(staffId, pageable);
            return new UnifyReponse(1, "success", purchasingRequisitions);
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }
    }

    @ApiOperation("审批采购申请")
    @PostMapping("/clinic/approvePurchasing")
    public UnifyReponse approvePurchasing(@RequestParam("id") String id) {

        try {
            Optional<PurchasingRequisition> purchasingRequisition = purchasingRequisitionRepository.findById(id);
            if (purchasingRequisition.isPresent()) {
                PurchasingRequisition p = purchasingRequisition.get();
                p.setStatus(1);
                purchasingRequisitionRepository.saveAndFlush(p);
            }
            return new UnifyReponse(1, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

    }

    @ApiOperation("修改采购申请")
    @PostMapping("/clinic/modifyPurchasing")
    public UnifyReponse modifyPurchasing (@RequestParam("id") String id,
                                          @RequestParam("medicine_id") int medicineId,
                                          @RequestParam("num") int num)
    {

        try {
            Optional<PurchasingRequisition> purchasingRequisition = purchasingRequisitionRepository.findById(id);
            if (purchasingRequisition.isPresent()&&medicineId!=0&&num!=0) {
                PurchasingRequisition p = purchasingRequisition.get();
                p.setMedicineId(medicineId);
                p.setNum(num);
                purchasingRequisitionRepository.saveAndFlush(p);
            }
            return new UnifyReponse(1, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }
    }
}

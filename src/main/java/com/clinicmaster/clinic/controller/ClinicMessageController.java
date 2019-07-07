package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.ClinicInfo;
import com.clinicmaster.clinic.repository.ClinicMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClinicMessageController {
    @Autowired
    private ClinicMessageRepository clinicMessageRepository;

    @PostMapping("/patient/findClinicInfo")     //获取医院详细信息
    public UnifyReponse findClinicInfo(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                                                @RequestParam("sort")String sort){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, sort);
        List<ClinicInfo> list = clinicMessageRepository.findAll();
        UnifyReponse<List<ClinicInfo>> response = new UnifyReponse<>();
        if(list != null) {
            response = new UnifyReponse(1, "success", list);
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }
    @PostMapping("/patient/findClinic")     //查询医院基本信息
    public UnifyReponse findClinic(@RequestParam("name")String name){
        ClinicInfo returnData = clinicMessageRepository.findClinicByName(name);
        UnifyReponse<ClinicInfo> response = new UnifyReponse<>();
        if(returnData != null) {
            response = new UnifyReponse(1, "success", returnData);
        }else{
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }
}


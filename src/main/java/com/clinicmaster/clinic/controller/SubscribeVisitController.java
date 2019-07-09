package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DoctorVisitTime;
import com.clinicmaster.clinic.repository.DoctorRepository;
import com.clinicmaster.clinic.repository.DoctorVisitTimeRepository;
import com.clinicmaster.clinic.repository.RegisterRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscribeVisitController {
    @Autowired
    private DoctorVisitTimeRepository doctorVisitTimeRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @ApiOperation("预约就诊")
    @PostMapping("/patient/subscribeVisitTime")     //预约就诊
    public UnifyReponse subscribeVisitTime(@RequestParam("visittime_id")String visittimeId, @RequestParam("register_id")String registerId){
        int result = doctorVisitTimeRepository.findAmountById(visittimeId);     //查询余量
        UnifyReponse<List<DoctorVisitTime>> response = new UnifyReponse<>();
        int dottorId = doctorVisitTimeRepository.findByIdS(visittimeId);
        if(result>0){
            doctorVisitTimeRepository.updateOnStatus(visittimeId);      //doctorvisttime表中status、totalamount、amount的更改
            doctorRepository.updateTotalamount(dottorId);        //doctor表中的totalamount更改
            registerRepository.updateOnStatus(registerId);      //register表中的status更改
            response = new UnifyReponse(1, "success");
        }else{
            doctorVisitTimeRepository.updateOnStatusF(visittimeId);      //doctorvisttime表中status的更改
            response = new UnifyReponse(0, "error");
        }
        return response;
    }

    @ApiOperation("取消预约就诊")
    @PostMapping("/patient/unSubscribeVisitTime")
    public UnifyReponse unSubscribeVisitTime(@RequestParam("register_id")String registerId){
        String visittimeId =registerRepository.findVisittimeId(registerId);        //根据预约的uuid查询visittimeid
        UnifyReponse<List<DoctorVisitTime>> response = new UnifyReponse<>();
        int doctorId = doctorVisitTimeRepository.findByIdS(visittimeId);
        if(visittimeId != null){
            doctorVisitTimeRepository.updateOffStatus(visittimeId);     //doctorvisittime表中status、totalamount、amount更改
            doctorRepository.updateOffAmount(doctorId);      //doctor中totalamount更改
            registerRepository.updateOffStatus(registerId);      //register中status更改
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "error");
        }
        return response;
    }
}

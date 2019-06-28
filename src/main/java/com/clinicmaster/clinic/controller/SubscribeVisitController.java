package com.clinicmaster.clinic.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DoctorVisittime;
import com.clinicmaster.clinic.domain.Register;
import com.clinicmaster.clinic.repository.DoctorRepository;
import com.clinicmaster.clinic.repository.DoctorVisitTimeRepository;
import com.clinicmaster.clinic.repository.RegisterRepository;
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

    @PostMapping("/subscribeVisitTime")     //预约就诊
    public UnifyReponse subscribeVisitTime(@RequestParam("visittime_id")int visittimeId, @RequestParam("register_id")int registerId){
        int result = doctorVisitTimeRepository.findAmountById(visittimeId);     //查询余量
        UnifyReponse<List<DoctorVisittime>> response = new UnifyReponse<>();
        if(result>0){
            doctorVisitTimeRepository.updateOnStatus(visittimeId);      //doctorvisttime表中status、totalamount、amount的更改
            doctorRepository.updateTotalamount(visittimeId);        //doctor表中的totalamount更改
            registerRepository.updateOnStatus(registerId);      //register表中的status更改
            response = new UnifyReponse(1, "success");
        }else{
            doctorVisitTimeRepository.updateOnStatusF(visittimeId);      //doctorvisttime表中status的更改
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }
    @PostMapping("/unSubscribeVisitTime")
    public UnifyReponse unSubscribeVisitTime(@RequestParam("register_id")int registerId){
        int visittimeId =registerRepository.findVisittimeId(registerId);        //根据预约的uuid查询visittimeid
        UnifyReponse<List<DoctorVisittime>> response = new UnifyReponse<>();
        if(visittimeId>0){
            doctorVisitTimeRepository.updateOffStatus(visittimeId);     //doctorvisittime表中status、totalamount、amount更改
            doctorRepository.updateOffAmount(visittimeId);      //doctor中totalamount更改
            registerRepository.updateOffStatus(registerId);      //register中status更改
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }
}

package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.AppointmentS;
import com.clinicmaster.clinic.domain.DoctorVisitTime;
import com.clinicmaster.clinic.repository.AppointmentRepositoryS;
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
    @Autowired
    private AppointmentRepositoryS appointmentRepositoryS;

    @ApiOperation("预约就诊")
    @PostMapping("/patient/subscribeVisitTime")     //预约就诊
    public UnifyReponse subscribeVisitTime(@RequestParam("visittime_id")String visittimeId, @RequestParam("register_id")String registerId){
        int result = doctorVisitTimeRepository.findAmountById(visittimeId);     //查询余量
        UnifyReponse<List<DoctorVisitTime>> response = new UnifyReponse<>();
        int dottorId = doctorVisitTimeRepository.findById(visittimeId);
        if(result>0){
            doctorVisitTimeRepository.updateOnStatus(visittimeId);      //doctorvisttime表中totalamount、amount的更改
            doctorRepository.updateTotalamount(dottorId);        //doctor表中的totalamount更改
            AppointmentS appointmentS = new AppointmentS();
            appointmentS.setPatientId(registerId);
            appointmentS.setStatus(1);
            appointmentS.setVisittimeId(visittimeId);
            appointmentRepositoryS.save(appointmentS);
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }

    @ApiOperation("取消预约就诊")
    @PostMapping("/patient/unSubscribeVisitTime")
    public UnifyReponse unSubscribeVisitTime(@RequestParam("register_id")String registerId){
        String visittimeId =registerRepository.findVisittimeId(registerId);        //根据预约的uuid查询visittimeid
        UnifyReponse<List<DoctorVisitTime>> response = new UnifyReponse<>();
        int doctorId = doctorVisitTimeRepository.findById(visittimeId);
        if(visittimeId != null){
            doctorVisitTimeRepository.updateOffStatus(visittimeId);     //doctorvisittime表中、totalamount、amount更改
            doctorRepository.updateOffAmount(doctorId);      //doctor中totalamount更改
//            appointmentRepositoryS.updateStatus(registerId);
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }
}

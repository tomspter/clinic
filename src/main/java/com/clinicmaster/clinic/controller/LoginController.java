package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DoctorVisitTime;
import com.clinicmaster.clinic.domain.PatientLog;
import com.clinicmaster.clinic.domain.PatientLogin;
import com.clinicmaster.clinic.repository.PatientLogRepository;
import com.clinicmaster.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientLogRepository patientLogRepository;
    @PostMapping("/patient/login")
    public UnifyReponse login(@RequestParam("username")String username,@RequestParam("passwd")String password
                              ){
        PatientLogin result = patientRepository.findByNameAndPasswd(username, password);
        UnifyReponse<List<DoctorVisitTime>> response = new UnifyReponse<>();
        if(result != null) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ctime = dateFormat.format(now);
            String patientId = result.getId();
            PatientLog patientLog = new PatientLog();
            patientLog.setPatient_id(patientId);
            patientLog.setLogin_time(ctime);
            patientLogRepository.saveAndFlush(patientLog);
            response = new UnifyReponse(1, "success");
        }else{
            response = new UnifyReponse(0, "fail");
        }
        return response;
    }
//
//    public UnifyReponse register(){
//
//    }
}

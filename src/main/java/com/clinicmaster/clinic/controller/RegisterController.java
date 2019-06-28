package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DepartmentChildZ;
import com.clinicmaster.clinic.domain.PatientZ;
import com.clinicmaster.clinic.domain.RegisterZ;
import com.clinicmaster.clinic.repository.AppointmentRepository;
import com.clinicmaster.clinic.repository.DepartmentChildRepositoryZ;
import com.clinicmaster.clinic.repository.PatientRepositoryZ;
import com.clinicmaster.clinic.repository.RegisterRepositoryZ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private RegisterRepositoryZ registerRepositoryZ;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DepartmentChildRepositoryZ departmentChildRepositoryZ;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepositoryZ patientRepositoryZ;


    @PostMapping("/clinic/register")
    public UnifyReponse doRegister(@RequestParam("patient_id") String patientId,
                                   @RequestParam("doctor_id") Integer doctorId){

        try {
            Optional<RegisterZ> register = registerRepositoryZ.findById(patientId);
            if (register.isPresent()) {
                RegisterZ MRegisterZ = register.get();
                Integer status = MRegisterZ.getRegisterStatus();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(MRegisterZ.getRegisterTime().getTime());
                int registerDay = calendar.get(Calendar.DAY_OF_MONTH);

                Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
                int nowDay = calendar2.get(Calendar.DAY_OF_MONTH);

                if (status.equals(0) && registerDay == nowDay) {

                    MRegisterZ.setRegisterStatus(1);
                    registerRepositoryZ.saveAndFlush(MRegisterZ);

                    stringRedisTemplate.opsForList().rightPush("patient", patientId);

                    Optional<DepartmentChildZ> departmentChild = departmentChildRepositoryZ.findByDoctorId(doctorId);
                    if (departmentChild.isPresent()) {
                        DepartmentChildZ dC = departmentChild.get();
                        dC.setCount(dC.getCount() + 1);
                        departmentChildRepositoryZ.saveAndFlush(dC);
                    }

                   appointmentRepository.deleteByPatientId(patientId);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(1,"error");
        }

        return new UnifyReponse(0,"success");
    }


    @PostMapping("/clinic/findRegisterQueue")
    public UnifyReponse findRegisterQueue(){

//        Long count=stringRedisTemplate.opsForList().size("patient");

            List<PatientZ> patientZS = new ArrayList<>();

        try {
//        Map<String,Object> result=new HashMap();
            List<String> patientTwo = stringRedisTemplate.opsForList().range("patient", 0, -1);
            if (patientTwo != null) {
                for (int i = 0; i < patientTwo.size(); i++) {
                    Optional<PatientZ> patient = patientRepositoryZ.findById(patientTwo.get(i));
                    if (patient.isPresent()) {
                        patientZS.add(patient.get());
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new UnifyReponse(1,"error");
        }
        return new UnifyReponse(1,"success", patientZS);
    }



}

package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DoctorVisitTimeZ;
import com.clinicmaster.clinic.repository.DoctorVisitTimeRepositoryZ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class DoAppointmentController {

    @Autowired
    private DoctorVisitTimeRepositoryZ doctorVisitTimeRepositoryZ;


    @PostMapping("/clinic/putAppointment")
    public UnifyReponse putAppointment(@RequestParam("visit_time") Timestamp visitTime,
                                       @RequestParam("doctor_id") Integer doctorId) {

        try {
            DoctorVisitTimeZ doctorVisittimeZ = new DoctorVisitTimeZ();
            doctorVisittimeZ.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            doctorVisittimeZ.setVisitTime(visitTime);
            doctorVisittimeZ.setDoctorId(doctorId);
            doctorVisittimeZ.setAmount(0);
            doctorVisittimeZ.setStatus(1);
            doctorVisitTimeRepositoryZ.saveAndFlush(doctorVisittimeZ);
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");

        }
        return new UnifyReponse(1, "success");
    }


    @PostMapping("/clinic/modifyAppointment")
    public UnifyReponse modifyAppointment(@RequestParam("visit_time") Timestamp visitTime,
                                          @RequestParam("doctor_id") Integer doctorId,
                                          @RequestParam("id") String id) {

        try {
            Optional<DoctorVisitTimeZ> doctorVisitTime = doctorVisitTimeRepositoryZ.findById(id);

            if (doctorVisitTime.isPresent()) {
                DoctorVisitTimeZ doctorVisitTimeZM = doctorVisitTime.get();
                doctorVisitTimeZM.setVisitTime(visitTime);
                doctorVisitTimeZM.setDoctorId(doctorId);
                doctorVisitTimeRepositoryZ.saveAndFlush(doctorVisitTimeZM);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");
    }

    @PostMapping("/clinic/deleteAppointment")
    public UnifyReponse modifyAppointment(@RequestParam("id") String id) {

        try {

            doctorVisitTimeRepositoryZ.deleteById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");
    }
}

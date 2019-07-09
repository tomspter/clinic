package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.DoctorVisitTime;
import com.clinicmaster.clinic.repository.DoctorVisitTimeRepository;
import io.swagger.annotations.ApiOperation;
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
    private DoctorVisitTimeRepository doctorVisitTimeRepository;


    @ApiOperation("发布出诊时间")
    @PostMapping("/clinic/putAppointment")
    public UnifyReponse putAppointment(@RequestParam("visit_time") Timestamp visitTime,
                                       @RequestParam("doctor_id") Integer doctorId,
                                       @RequestParam("total_amount") Integer totalAmount) {

        try {

            if (doctorVisitTimeRepository.findByDoctorIdAndVisitTime(doctorId, visitTime).isPresent()) {
                return new UnifyReponse(0, "error");
            } else {
                DoctorVisitTime doctorVisittime = DoctorVisitTime.builder()
                        .id(UUID.randomUUID().toString().replace("-", "").toLowerCase())
                        .visitTime(visitTime)
                        .doctorId(doctorId)
                        .amount(0)
                        .status(1)
                        .totalAmount(totalAmount)
                        .build();
                doctorVisitTimeRepository.saveAndFlush(doctorVisittime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");

        }
        return new UnifyReponse(1, "success");
    }

    @ApiOperation("修改出诊时间")
    @PostMapping("/clinic/modifyAppointment")
    public UnifyReponse modifyAppointment(@RequestParam("visit_time") Timestamp visitTime,
                                          @RequestParam("doctor_id") Integer doctorId,
                                          @RequestParam("total_amount") Integer totalAmount,
                                          @RequestParam("id") String id) {

        try {
            Optional<DoctorVisitTime> doctorVisitTime = doctorVisitTimeRepository.findById(id);

            if (doctorVisitTime.isPresent()) {
                DoctorVisitTime doctorVisittime = DoctorVisitTime.builder()
                        .id(id)
                        .visitTime(visitTime)
                        .doctorId(doctorId)
                        .totalAmount(totalAmount)
                        .build();
                doctorVisitTimeRepository.saveAndFlush(doctorVisittime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");
    }

    @ApiOperation("根据id删除预约")
    @PostMapping("/clinic/deleteAppointment")
    public UnifyReponse modifyAppointment(@RequestParam("id") String id) {

        try {

            doctorVisitTimeRepository.deleteById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success");
    }

}

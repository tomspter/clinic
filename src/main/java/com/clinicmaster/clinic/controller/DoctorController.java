package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.Doctor;
import com.clinicmaster.clinic.domain.DoctorVisittime;
import com.clinicmaster.clinic.repository.DoctorRepository;
import com.clinicmaster.clinic.repository.DoctorVisitTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorVisitTimeRepository doctorVisitTimeRepository;
    @PostMapping("/findDoctor")
    public UnifyReponse findDoctor(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                                   @RequestParam("sort")String sort, @RequestParam("department_id") int departmentId){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, sort);
        List<Doctor> doctors = doctorRepository.findAllByDepartmentId(departmentId);
        UnifyReponse<List<Doctor>> response = new UnifyReponse<>();
        if(doctors != null) {
            response = new UnifyReponse(1, "success", doctors);
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }
    @PostMapping("/findDoctorVisitTime")
    public UnifyReponse findDoctorVisitTime(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                                            @RequestParam("sort")String sort, @RequestParam("doctor_id") int doctorId){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, sort);
        List<DoctorVisittime> doctorsVisitTime = doctorVisitTimeRepository.findAllByDoctorId(doctorId);
        UnifyReponse<List<DoctorVisittime>> response = new UnifyReponse<>();
        if(doctorsVisitTime != null) {
            response = new UnifyReponse(1, "success", doctorsVisitTime);
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }
}

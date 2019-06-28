package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.*;
import com.clinicmaster.clinic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentParentRepository departmentParentRepository;
    @Autowired
    private DepartmentChildRepository departmentChildRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorVisitTimeRepository doctorVisitTimeRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @PostMapping("/findDepartment")     //获取科室信息
    public UnifyReponse findDepartment(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                                       @RequestParam("sort")String sort){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, sort);
        List<DepartmentParent> listParent = departmentParentRepository.findAll();
        int length = listParent.size();
        List<DepartmentChild> listChild = departmentChildRepository.findAll();
        List<Department> departments = new ArrayList<>();

        for (DepartmentParent departmentParent : listParent){
            Department department = new Department();
            department.setParentName(departmentParent.getParentName());
            int parentId = departmentParent.getId();
            department.setDepartmentChildren(departmentChildRepository.findByParentId(parentId));
            System.out.println(parentId);
            departments.add(department);
        }
        UnifyReponse<List<Department>> response = new UnifyReponse<>();
        if(departments != null) {
            response = new UnifyReponse(1, "success", departments);
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }

    @PostMapping("/showConsulting")     //查看诊室状态
    public UnifyReponse showConsulting(@RequestParam("departmentchild_id")int departmentchildId){
        UnifyReponse<List<Department>> response = new UnifyReponse<>();
        List<ConsultingDoctor> consultingDoctors = new ArrayList<>();       //最终返回的数据
        List<Doctor> doctors = doctorRepository.findAllByDepartmentId(departmentchildId);       //通过入参查找doctors
        for(Doctor doctor : doctors){       //对每一个doctor处理
            ConsultingDoctor consultingDoctor = new ConsultingDoctor();     //包含每一个doctor及其下面预约的病人数据的实体类
            int doctorId = doctor.getId();
            consultingDoctor.setDoctorId(doctorId);     //设置doctorId
            consultingDoctor.setName(doctor.getName());     //设置name
            List<DoctorVisittime> doctorVisittimes = doctorVisitTimeRepository.findAllByDoctorId(doctorId);     //通过doctorId查找所有预约情况
            List<RegisterConsulting> registerConsultings = new ArrayList<>();       //包含所有病人的list
            for(DoctorVisittime doctorVisittime : doctorVisittimes){        //对每一个预约进行处理
                int visittimeId = doctorVisittime.getId();
                int[] patientId = registerRepository.findPatientId(visittimeId);        //通过预约id查找所有预约的病人的id
                for(int id : patientId){        //对每一个病人进行处理
                    RegisterConsulting registerConsulting = new RegisterConsulting();       //包含单个病人信息的实体类
                    String patientName = patientRepository.findName(id);
                    registerConsulting.setPatientName(patientName);
                    registerConsulting.setRegisterId(id);
                    registerConsultings.add(registerConsulting);        //忘集合里添加
                }
            }
            consultingDoctor.setRegisterConsultings(registerConsultings);
            consultingDoctors.add(consultingDoctor);
        }

        if(consultingDoctors != null) {
            response = new UnifyReponse(1, "success", consultingDoctors);
        }else{
            response = new UnifyReponse(0, "faile");
        }
        return response;
    }
}

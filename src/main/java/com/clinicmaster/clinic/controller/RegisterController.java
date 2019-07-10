package com.clinicmaster.clinic.controller;

import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.Appointment;
import com.clinicmaster.clinic.domain.DepartmentChild;
import com.clinicmaster.clinic.domain.PatientLogin;
import com.clinicmaster.clinic.domain.Register;
import com.clinicmaster.clinic.keda.WebTTs;
import com.clinicmaster.clinic.repository.AppointmentRepository;
import com.clinicmaster.clinic.repository.DepartmentChildRepository;
import com.clinicmaster.clinic.repository.PatientRepository;
import com.clinicmaster.clinic.repository.RegisterRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DepartmentChildRepository departmentChildRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;


    @ApiOperation("患者登记")
    @PostMapping("/clinic/register")
    public UnifyReponse doRegister(@RequestParam("appointment_id") String appointmentId,
                                   @RequestParam("department_id") Integer departmentId
    ) {

        //TODO 校验
        /*
        1.查找是否有预约（appointment）
        2.预约状态变为2
        3.写入redis
        4.生成挂号单(register)
         */
        try {
            //1
            Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
//            log.info("some{}"+appointmentOptional.get().getVisittimeId());

            if (appointmentOptional.isPresent()) {

                //2
                Appointment appointment = appointmentOptional.get();
                appointment.setStatus(2);
                appointmentRepository.saveAndFlush(appointment);

                //4
                String patientId = appointment.getPatientId();

                Register register = Register.builder()
                        .id(UUID.randomUUID().toString().replace("-", "").toLowerCase())
                        .patientId(patientId)
                        .departmentchildId(departmentId)
                        .registerTime(new Timestamp(System.currentTimeMillis()))
                        .registerStatus(1)
                        .build();

                registerRepository.saveAndFlush(register);

                //3
                String key = "patient" + departmentId;
                stringRedisTemplate.opsForList().rightPush(key, patientId);
//                Optional<Register> register = registerRepository.findById(patientId);
//                if (!register.isPresent()) {
//                    Register MRegisterZ = register.get();
//                    Integer status = MRegisterZ.getRegisterStatus();
//
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTimeInMillis(MRegisterZ.getRegisterTime().getTime());
//                    int registerDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//                    Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
//                    int nowDay = calendar2.get(Calendar.DAY_OF_MONTH);
//
//                    if (status.equals(0) && registerDay == nowDay) {
//
//                        MRegisterZ.setRegisterStatus(1);
//                        registerRepository.saveAndFlush(MRegisterZ);
//
//                        stringRedisTemplate.opsForList().rightPush("patient", patientId);
//
//                        Optional<DepartmentChild> departmentChild = departmentChildRepository.findByDoctorId(doctorId);
//                        if (departmentChild.isPresent()) {
//                            DepartmentChild dC = departmentChild.get();
//                            dC.setCount(dC.getCount() + 1);
//                            departmentChildRepository.saveAndFlush(dC);
//                        }
//
//                        appointmentRepository.deleteByPatientId(patientId);
//
//                    } else {
//
//                        return new UnifyReponse(0, "error");
//
//                    }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }
        return new UnifyReponse(1, "success");
    }


    @ApiOperation("查找登记队列")
    @PostMapping("/clinic/findRegisterQueue")
    public UnifyReponse findRegisterQueue(@RequestParam("department_id") int dmId) {

        String key = "patient" + dmId;

        Long count = stringRedisTemplate.opsForList().size(key);
        log.info("count is {}", count);

        List<String> patientName = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();

        try {

            List<String> patientAll = stringRedisTemplate.opsForList().range(key, 0, -1);

            log.info("patientAll{}", patientAll);

            if (patientAll != null) {
                for (String s : patientAll) {

                    Optional<PatientLogin> patient = patientRepository.findById(s);
                    if (patient.isPresent()) {

                        String resultName;

                        String name = patient.get().getName();
                        int nameLength = name.toCharArray().length;

                        switch (nameLength) {
                            case 2:
                                resultName = name.replaceFirst(name.substring(1), "*");
                                break;
                            case 3:
                                resultName = name.replaceFirst(name.substring(1, nameLength - 1), "*");
                                break;
                            case 4:
                                resultName = name.replaceFirst(name.substring(1, nameLength - 1), "*");
                                break;
                            default:
                                resultName = name;
                        }

                        patientName.add(resultName);
                    }
                }
            }

            result.put("count", count);
            result.put("patient", patientName);
        } catch (Exception e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }
        return new UnifyReponse(1, "success", result);
    }


    @PostMapping("/callPatient")
    public UnifyReponse callPatient(@RequestParam("department_id") Integer departmentId) throws IOException {

        /*
        1.获取redis里第一个患者的名字
        2.转换音频文件
        3.保存音频文件###
        4.返回音频路径###
         */

        String key = "patient" + departmentId;
        String patientId = stringRedisTemplate.opsForList().index(key, 0);
        Optional<PatientLogin> patientLoginOptional = patientRepository.findById(patientId);
        if (patientLoginOptional.isPresent()) {
            String name = patientLoginOptional.get().getName();

            log.info("name:{}", name);

            //TODO 音频转换

//            WebTTs.kedaTTS(name);
        }
        return new UnifyReponse(1, "success");
    }
}

package com.clinicmaster.clinic.controller;

import com.aliyuncs.exceptions.ClientException;
import com.clinicmaster.clinic.constant.UnifyReponse;
import com.clinicmaster.clinic.domain.User;
import com.clinicmaster.clinic.domain.UserLog;
import com.clinicmaster.clinic.repository.UserLogRepository;
import com.clinicmaster.clinic.repository.UserRepository;
import com.clinicmaster.clinic.utils.SmsUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @PostMapping("/clinic/login")
    public UnifyReponse userLogin(@RequestParam("user") String userName,
                                  @RequestParam("passwd") String passwd,
                                  @RequestParam("category") String category) {

        Optional<User> userOptional = userRepository.findByUserNameAndPassWord(userName, passwd);

        UserLog userLog = UserLog.builder()
                .userId(userOptional.get().getId())
                .loginTime(new Timestamp(System.currentTimeMillis()))
                .build();
        userLogRepository.saveAndFlush(userLog);

        if (userOptional.isPresent()) {
            switch (userOptional.get().getCategory()) {
                case "医生":
                    break;
                case "管理员":
                    break;
                case "药房医生":
                    break;
                case "导诊员":
                    break;
                default:
                    return new UnifyReponse(0, "error");
            }
        }

        return null;
    }

    @ApiOperation("获取短信验证码")
    @ApiImplicitParam(name = "tel", value = "电话号码", required = true, dataType = "String")
    @PostMapping("/smsService")
    public UnifyReponse smsService(@RequestParam("tel") String tel) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        try {
            SmsUtils.sendSmsLogin(tel, code);
        } catch (ClientException e) {
            e.printStackTrace();
            return new UnifyReponse(0, "error");
        }

        return new UnifyReponse(1, "success", code);

    }

}

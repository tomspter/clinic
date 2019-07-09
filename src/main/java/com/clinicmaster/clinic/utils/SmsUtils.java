package com.clinicmaster.clinic.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.sql.Timestamp;

public class SmsUtils {
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAIKG5z5OFgmw6F";
    static final String accessKeySecret = "fUmJTJvnQoiKUJgXvC79EZtKzFx5Po";

    public static SendSmsResponse sendSmsLogin(String telephone, String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "何庄子诊所");
        request.putQueryParameter("TemplateCode", "SMS_169637410");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());

        return null;
    }

    public static SendSmsResponse sendSmsAppointmentSuccess(String name, Timestamp time, Integer num,String telephone) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "何庄子诊所");
        request.putQueryParameter("TemplateCode", "SMS_170115356");
        request.putQueryParameter("TemplateParam", "{\"name\":\""+name+"\",\"time\":\""+time+"\",\"num\":\""+num+"\"}");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());

        return null;
    }

}

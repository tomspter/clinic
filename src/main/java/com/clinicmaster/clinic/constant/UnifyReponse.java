package com.clinicmaster.clinic.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnifyReponse<T> implements Serializable {

    private T data;

    private Integer code;

    private String msg;


    /**
     * 默认返回调用
     */
    public UnifyReponse(){
        this.code = 1;
        this.msg = "success";
        this.data=null;
    }


    /**
     * 失败返回调用
     * @param code
     * @param msg
     */
    public UnifyReponse(Integer code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功返回调用
     * @param code
     * @param msg
     * @param data
     */
    public UnifyReponse(Integer code, String msg, T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}

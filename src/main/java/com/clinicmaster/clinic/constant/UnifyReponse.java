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


    public UnifyReponse(){
        this.code = 1;
        this.msg = "success";
        this.data=null;
    }

    public UnifyReponse(Integer code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public UnifyReponse(Integer code, String msg, T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}

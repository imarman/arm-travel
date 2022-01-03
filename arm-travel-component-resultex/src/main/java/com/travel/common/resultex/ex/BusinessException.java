package com.travel.common.resultex.ex;//package com.kuangstudy.config;


import com.travel.common.resultex.enums.ResultStatusEnum;

public class BusinessException extends RuntimeException {

    private Integer status;
    private String msg;

    public BusinessException(Integer status, String msg) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public BusinessException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMessage());
        this.status = resultStatusEnum.getStatus();
        this.msg = resultStatusEnum.getMessage();
    }

    public BusinessException(String msg) {
        super(msg);
        this.status = 500;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}

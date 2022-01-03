package com.travel.common.resultex.ex;

import com.travel.common.resultex.enums.ResultStatusEnum;

public class OrderException extends RuntimeException {

    private Integer status;
    private String msg;

    public OrderException(Integer status, String msg) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public OrderException(String msg) {
        super(msg);
        this.status = 500;
        this.msg = msg;
    }

    public OrderException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMessage());
        this.status = resultStatusEnum.getStatus();
        this.msg = resultStatusEnum.getMessage();
    }


    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}

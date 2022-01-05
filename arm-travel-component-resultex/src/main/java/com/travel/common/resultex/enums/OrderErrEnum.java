package com.travel.common.resultex.enums;

public enum OrderErrEnum implements IResult {

    ORDER_ERROR_STATUS(602, "订单有误");

    OrderErrEnum(Integer code, String message) {
        this.code = Integer.parseInt(IResult.ORDER_CODE.concat(String.valueOf(code)));
        this.message = message;
    }

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

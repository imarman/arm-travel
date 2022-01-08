package com.travel.common.resultex.enums;

public enum BizErrEnum implements IResult {

    USER_PWR_STATUS(100, "用户密码有误"),
    TOKEN_EMPTY(201, "token为空"),
    TOKEN_ERROR(202, "token有误"),
    TOKEN_NOT_VERIFY(203, "token过期"),
    ;

    BizErrEnum(Integer code, String message) {
        this.code = Integer.parseInt(IResult.BIZ_CODE.concat(String.valueOf(code)));
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

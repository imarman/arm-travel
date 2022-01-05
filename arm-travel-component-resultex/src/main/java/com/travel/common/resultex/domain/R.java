package com.travel.common.resultex.domain;

import com.travel.common.resultex.enums.IResult;
import lombok.Data;

/**
 * @author Arman
 */
@Data
public class R {

    private Integer code;
    private String message;
    private Object data;

    private R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private R(IResult iResult) {
        this.code = iResult.getCode();
        this.message = iResult.getMessage();
    }

    public static R ok() {
        return R.ok(IResult.SUCCESS_MSG);
    }

    public static R ok(String message) {
        return R.ok(message, null);
    }

    public static R ok(String message, Object data) {
        return new R(IResult.SUCCESS_CODE, message, data);
    }

    public static R ok(Object data) {
        return new R(IResult.SUCCESS_CODE, IResult.SUCCESS_MSG, data);
    }

    public static R error(IResult iResult) {
        return new R(iResult);
    }

    public static R error(Integer status, String message) {
        return new R(status, message, null);
    }

    public static R errorMsg(IResult iResult, String message) {
        R r = new R(iResult);
        r.setMessage(message);
        return r;
    }

}
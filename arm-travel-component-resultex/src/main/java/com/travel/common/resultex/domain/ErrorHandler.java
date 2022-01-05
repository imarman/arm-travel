package com.travel.common.resultex.domain;


import com.travel.common.resultex.enums.IResult;
import lombok.Data;

/**
 * @author Arman
 */
@Data
public class ErrorHandler {

    // 状态码
    private Integer code;
    // 返回信息
    private String msg;
    // 返回数据，因为返回数据是不确定类型，所以只能考虑Object或者泛型
    private Object data;

    // 全部约束使用方法区执行和返回，不允许到到外部去new
    private ErrorHandler() {

    }

    /**
     * 方法封装 :
     * - R 大量的大量的入侵
     * - 解决调用方便的问题
     */
    // 错误为什么传递status。成功只有一种，但是错误有N状态
    public static ErrorHandler error(Integer status, String msg) {
        return restResult(null, status, msg);
    }

    public static ErrorHandler error(Integer status, String msg, Object obj) {
        return restResult(obj, status, msg);
    }

    public static ErrorHandler error(IResult iResult) {
        return restResult(null, iResult.getCode(), iResult.getMessage());
    }

    public static ErrorHandler error(IResult iResult, String message) {
        return restResult(null, iResult.getCode(), message);
    }

    public static ErrorHandler error(String message) {
        return restResult(null, 500, message);
    }

    private static ErrorHandler restResult(Object data, Integer status, String msg) {
        ErrorHandler r = new ErrorHandler();
        r.setCode(status);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}

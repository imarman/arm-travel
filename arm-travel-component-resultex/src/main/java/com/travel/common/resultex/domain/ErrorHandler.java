package com.travel.common.resultex.domain;


import com.travel.common.resultex.enums.ResultStatusEnum;
import lombok.Data;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/15 23:20.
 * Update Date Time:
 *
 * @see
 */
@Data
public class ErrorHandler {

    // 状态码
    private Integer status;
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
     *
     * @param obj
     * @return
     */
    public static ErrorHandler success(Object obj) {
        // 200写死，原因很简单：在开发成功只允许只有一种声音，不允许多种
        return restResult(obj, ResultStatusEnum.SUCCESS_STATUS.getStatus(), ResultStatusEnum.SUCCESS_STATUS.getMessage());
    }

    public static ErrorHandler success(Object obj, String msg) {
        return restResult(obj, ResultStatusEnum.SUCCESS_STATUS.getStatus(), ResultStatusEnum.SUCCESS_STATUS.getMessage());
    }

    // 错误为什么传递status。成功只有一种，但是错误有N状态
    public static ErrorHandler error(Integer status, String msg) {
        return restResult(null, status, msg);
    }

    public static ErrorHandler error(Integer status, String msg, Object obj) {
        return restResult(obj, status, msg);
    }

    public static ErrorHandler error(ResultStatusEnum resultStatusEnum) {
        return restResult(null, resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }

    private static ErrorHandler restResult(Object data, Integer status, String msg) {
        ErrorHandler r = new ErrorHandler();
        r.setStatus(status);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}

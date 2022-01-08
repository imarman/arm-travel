package com.travel.common.resultex.enums;

/**
 * @author Arman
 * @date 2022/1/5 21:32
 */
public interface IResult {

    /**
     * 系统编码,返回前缀
     */
    String BIZ_CODE = "30";
    String ORDER_CODE = "40";

    /**
     * 成功的默认信息和 code
     */
    String SUCCESS_MSG = "success";
    Integer SUCCESS_CODE = 9999;

    /**
     * 万不得已用的错误码和消息
     */
    Integer ERROR_CODE = 500;
    String ERROR_MSG = "服务器忙中...";

    Integer getCode();

    String getMessage();

}

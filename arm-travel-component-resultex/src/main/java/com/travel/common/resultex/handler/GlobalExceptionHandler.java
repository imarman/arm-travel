package com.travel.common.resultex.handler;


import com.travel.common.resultex.domain.ErrorHandler;
import com.travel.common.resultex.enums.IResult;
import com.travel.common.resultex.ex.BusinessException;
import com.travel.common.resultex.ex.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 拦截所有程序异常
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorHandler errorHandler(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        // log.error("报错:{}", ex.getMessa);
        log.error("Exception 异常地址:{}, 异常:{}", request.getRequestURL().toString(), ex.getMessage());
        return ErrorHandler.error(IResult.ERROR_CODE, IResult.ERROR_MSG);
    }

    /**
     * OrderException异常
     */
    @ExceptionHandler(value = OrderException.class)
    @ResponseBody
    public ErrorHandler orderExceptionHandler(HttpServletRequest request, OrderException ex) {
        ex.printStackTrace();
        log.error("异常地址:{}, 异常:{}", request.getRequestURL().toString(), ex.getMessage());
        if (null != ex.getIResult()) {
            return ErrorHandler.error(ex.getIResult(), ex.getMessage());
        }
        return ErrorHandler.error(ex.getMessage());
    }

    /**
     * BusinessException异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorHandler businessExceptionHandler(HttpServletRequest request, BusinessException ex) {
        // 打印堆栈，以供调试
        ex.printStackTrace();
        log.error("异常地址:{}, 异常:{}", request.getRequestURL().toString(), ex.getMessage());
        ex.printStackTrace();
        if (null != ex.getIResult()) {
            return ErrorHandler.error(ex.getIResult(), ex.getMessage());
        }
        return ErrorHandler.error(ex.getMessage());
    }

}

package com.travel.common.resultex.handler;


import com.travel.common.resultex.domain.ErrorHandler;
import com.travel.common.resultex.ex.BusinessException;
import com.travel.common.resultex.ex.OrderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionControllerHandler {


    /**
     * 拦截所有程序异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorHandler errorHandler(HttpServletRequest request, Exception ex) {
        return ErrorHandler.error(500, "服务器忙中...", request.getRequestURL().toString());
    }

    /**
     * OrderException异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = OrderException.class)
    @ResponseBody
    public ErrorHandler errorHandlerOrex(HttpServletRequest request, OrderException ex) {
        return ErrorHandler.error(ex.getStatus(), ex.getMsg(), request.getRequestURL().toString());
    }

    /**
     * BussinessException异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorHandler errorHandlerBex(HttpServletRequest request, BusinessException ex) {
        return ErrorHandler.error(ex.getStatus(), ex.getMsg(), request.getRequestURL().toString());
    }

}
